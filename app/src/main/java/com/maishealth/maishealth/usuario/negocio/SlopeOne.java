package com.maishealth.maishealth.usuario.negocio;


import android.content.Context;

import com.maishealth.maishealth.usuario.dominio.Medico;
import com.maishealth.maishealth.usuario.dominio.Paciente;
import com.maishealth.maishealth.usuario.dominio.Avaliacao;
import com.maishealth.maishealth.usuario.dominio.Recomendacao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;


/**
 * classe SlopeOne ler os dados no banco atraves do ReadPessoa e ReadLivro cria um atributo Context, cria as matrizes de diferenÃ§a e de frequencia.
 */

public class SlopeOne {
    private static List<Paciente> todosPacientes = new ArrayList<>();
    private static List<Medico> todosMedicos = new ArrayList<>();
    private static List<Avaliacao> medicosAvaliados = new ArrayList<>();

    private static List<Medico> medicosRecomendados = new ArrayList<>();
    private ServicosPaciente servicosPaciente;
    private ServicosMedico servicosMedico;

    private ServicosAvaliacao servicosAvaliacao;
    private Map<Long, Map<Long, Double>> dadosAv = new HashMap<>();
    private Map<Long, Map<Long, Double>> matrizDiferenca1;
    private Map<Long, Map<Long, Integer>> matrizFrequencia1;

    public SlopeOne(Context context) {
        servicosAvaliacao = new ServicosAvaliacao(context);
        servicosMedico = new ServicosMedico(context);
        servicosPaciente = new ServicosPaciente(context);

    }

    public void leituraDados1(String espec) {
        todosPacientes = servicosPaciente.getPacientes();
        todosMedicos = servicosMedico.getMedicoByEspec(espec);

        for (Paciente paciente : todosPacientes) {
            HashMap<Long, Double> notasPac = new HashMap<>();
            medicosAvaliados = servicosAvaliacao.getRecomendacaoByPacienteAndEspec(paciente.getId(), todosMedicos);

            for (Avaliacao remomendcaoMedico : medicosAvaliados) {
                long idmed = remomendcaoMedico.getIdMedico();
                double nota = remomendcaoMedico.getNota();
                notasPac.put(remomendcaoMedico.getIdMedico(), remomendcaoMedico.getNota());
            }
            dadosAv.put(paciente.getId(), notasPac);
        }

    }

    public ArrayList<Recomendacao> listaRecomendacao1(Paciente paciente, String espec) {
        leituraDados1(espec);
        return calculaRecomendacoes1(dadosAv, paciente);
    }

    public void criarMatrizDiferenca1(Map<Long, Map<Long, Double>> dadosAv) {
        matrizDiferenca1 = new HashMap<>();
        matrizFrequencia1 = new HashMap<>();

        for (Map<Long, Double> user : dadosAv.values()) {
            for (Map.Entry<Long, Double> entry : user.entrySet()) {
                if (!matrizDiferenca1.containsKey(entry.getKey())) {
                    matrizDiferenca1.put(entry.getKey(), new HashMap<Long, Double>());
                    matrizFrequencia1.put(entry.getKey(), new HashMap<Long, Integer>());
                }
                for (Map.Entry<Long, Double> entry2 : user.entrySet()) {
                    int oldcount = 0;
                    if (matrizFrequencia1.get(entry.getKey()).containsKey(entry2.getKey())) {
                        oldcount = matrizFrequencia1.get(entry.getKey()).get(entry2.getKey()).intValue();
                    }
                    Double olddiff = 0.0;
                    if (matrizDiferenca1.get(entry.getKey()).containsKey(entry2.getKey())) {
                        olddiff = matrizDiferenca1.get(entry.getKey()).get(entry2.getKey()).doubleValue();
                    }
                    Double observeddiff = entry.getValue() - entry2.getValue();
                    matrizFrequencia1.get(entry.getKey()).put(entry2.getKey(), oldcount + 1);
                    matrizDiferenca1.get(entry.getKey()).put(entry2.getKey(), olddiff + observeddiff);
                }
            }
        }
        for (Long j : matrizDiferenca1.keySet()) {
            for (Long i : matrizDiferenca1.get(j).keySet()) {
                Double oldvalue = matrizDiferenca1.get(j).get(i).doubleValue();
                int count = matrizFrequencia1.get(j).get(i).intValue();
                matrizDiferenca1.get(j).put(i, oldvalue / count);
            }
        }
    }

    private Map<Long, Double> predict(Map<Long, Double> notasUsuario) {
        HashMap<Long, Double> predictions = new HashMap<>();
        HashMap<Long, Integer> frequences = new HashMap<>();
        for (Long j : matrizDiferenca1.keySet()) {
            frequences.put(j, 0);
            predictions.put(j, 0.0);
        }
        for (Long j : notasUsuario.keySet()) {
            for (Long k : matrizDiferenca1.keySet()) {
                try {
                    Double novoValor = (matrizDiferenca1.get(k).get(j).doubleValue() + notasUsuario.get(j).doubleValue()) * matrizFrequencia1.get(k).get(j).intValue();
                    predictions.put(k, predictions.get(k) + novoValor);
                    frequences.put(k, frequences.get(k) + matrizFrequencia1.get(k).get(j).intValue());

                } catch (NullPointerException e) {
                }
            }
        }
        HashMap<Long, Double> cleanpredictions = new HashMap<>();
        for (Long j : predictions.keySet()) {
            if (frequences.get(j) > 0) {
                cleanpredictions.put(j, predictions.get(j).doubleValue() / frequences.get(j).intValue());
            }
        }
        for (Long j : notasUsuario.keySet()) {
            cleanpredictions.put(j, notasUsuario.get(j));
        }
        return cleanpredictions;
    }

    private ArrayList<Recomendacao> calculaRecomendacoes1(Map<Long, Map<Long, Double>> dadosAv, Paciente paciente) {
        criarMatrizDiferenca1(dadosAv);

        return ordenar(predict(dadosAv.get(paciente.getId())));
    }

    private ArrayList<Recomendacao> ordenar(Map<Long, Double> notasUsuario) {
        ArrayList<Recomendacao> recom = new ArrayList<>();
        Map<Long, Double> saida = new HashMap<>();

        for (int j = 0; j < notasUsuario.size(); j++) {
            double maior = 0.0;
            long chave = 0;
            for (long i : notasUsuario.keySet()) {
                double novoItem = 0.0;

                if (!saida.containsKey(i)) {
                    novoItem = notasUsuario.get(i);
                }
                if (novoItem > maior) {
                    maior = novoItem;
                    chave = i;
                }
            }
            saida.put(chave, maior);
            Recomendacao recomendacao = new Recomendacao(chave,maior);
            recom.add(recomendacao);
        }
        return recom;
    }

}
