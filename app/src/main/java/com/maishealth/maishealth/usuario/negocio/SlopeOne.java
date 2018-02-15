package com.maishealth.maishealth.usuario.negocio;


import android.content.Context;

import com.maishealth.maishealth.usuario.dominio.Medico;
import com.maishealth.maishealth.usuario.dominio.Paciente;
import com.maishealth.maishealth.usuario.dominio.Avaliacao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/*import com.projeto.bookfast.bookfast.livro.dominio.Livro;
import com.projeto.bookfast.bookfast.livro.persistencia.ReadLivro;
import com.projeto.bookfast.bookfast.pessoa.dominio.Pessoa;
import com.projeto.bookfast.bookfast.pessoa.persistencia.ReadPessoa;
import com.projeto.bookfast.bookfast.recomendacao.dominio.Avaliacao;
import com.projeto.bookfast.bookfast.recomendacao.persistencia.AvaliacaoDao;*/

/**
 * classe SlopeOne ler os dados no banco atravÃ©s do ReadPessoa e ReadLivro cria um atributo Context, cria as matrizes de diferenÃ§a e de frequencia.
 */

public class SlopeOne {
    private static List<Paciente> todosPacientes = new ArrayList<>();
    private static List<Medico> todosMedicos = new ArrayList<>();
    private static List<Avaliacao> medicosAvaliados = new ArrayList<>();
    /*private AvaliacaoDao buscaAvaliacao;
    private ReadPessoa buscaPessoa;
    private ReadLivro buscaLivro;*/
    private static List<Medico> medicosRecomendados = new ArrayList<>();
    private ServicosPaciente servicosPaciente;
    private ServicosMedico servicosMedico;

    //private Map<Integer, Map<Integer, Double>> matrizDiferenca;
    //private Map<Integer, Map<Integer, Integer>> matrizFrequencia;
    //private Map<Integer, Map<Integer, Double>> data = new HashMap<Integer, Map<Integer, Double>>();
    private ServicosAvaliacao servicosAvaliacao;
    private Map<Long, Map<Long, Double>> dadosAv = new HashMap<>();
    private Map<Long, Map<Long, Double>> matrizDiferenca1;
    private Map<Long, Map<Long, Integer>> matrizFrequencia1;

    /*private static List<Livro> todosLivros = new ArrayList<>();
    private static List<Pessoa> todosUsuarios = new ArrayList<>();
    private static List<Avaliacao> produtosClassificados = new ArrayList<>();
    private static List<Livro> produtosRecomendadosOrdenados;*/

    public SlopeOne(Context context) {
        /*buscaLivro = new ReadLivro(context);
        buscaPessoa = new ReadPessoa(context);
        buscaAvaliacao = new AvaliacaoDao(context);*/
        servicosAvaliacao = new ServicosAvaliacao(context);
        servicosMedico = new ServicosMedico(context);
        servicosPaciente = new ServicosPaciente(context);

    }

    public static Set<Long> getRecomendacao(Map<Long, Double> notasUsuario, Paciente paciente) {
        return ordenarCompare(notasUsuario, paciente);
    }

    /*public static Set<Integer> getRecomendacao(Map<Integer, Double> notasUsuario, Pessoa usuario) {
        return ordenarCompare(notasUsuario, usuario);
    }*/

    public static Set<Long> ordenarCompare(Map<Long, Double> map, Paciente paciente) {
        Comparador comparador = new Comparador(map);
        Map<Long, Double> sorted_map = new TreeMap<>(comparador);
        sorted_map.putAll(map);
        return sorted_map.keySet();
    }

    public void leituraDados1(String espec) {
        todosPacientes = servicosPaciente.getPacientes();
        todosMedicos = servicosMedico.getMedicoByEspec(espec);

        for (Paciente paciente : todosPacientes) {
            HashMap<Long, Double> notasPac = new HashMap<>();
            medicosAvaliados = servicosAvaliacao.getRecomendacaoByPaciente(paciente.getId());

            for (Avaliacao remomendcaoMedico : medicosAvaliados) {
                notasPac.put(remomendcaoMedico.getIdMedico(), remomendcaoMedico.getNota());
            }
            dadosAv.put(paciente.getId(), notasPac);
        }

    }

    public List listaRecomendacao1(Paciente paciente, String espec) {
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

    public Map<Long, Double> predict(Map<Long, Double> notasUsuario) {
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

    private List<Long> calculaRecomendacoes1(Map<Long, Map<Long, Double>> dadosAv, Paciente paciente) {
        criarMatrizDiferenca1(dadosAv);

        Set<Long> listIdOrdenados = getRecomendacao(predict(dadosAv.get(paciente.getId())), paciente);
        List<Long> medicosRecomendadosOrdenados = new ArrayList<>();
        for (Long i : listIdOrdenados) {
            medicosRecomendadosOrdenados.add(i);
        }
        return medicosRecomendadosOrdenados;
    }

    public static class Comparador implements java.util.Comparator {
        private Map m = null; // the original map

        public Comparador(Map map) {
            this.m = map;
        }

        public int compare(Object o1, Object o2) {
            // handle some exceptions here
            Double v1 = (Double) m.get(o1);
            Double v2 = (Double) m.get(o2);
            // make sure the values implement Comparable
            return v1.compareTo(v2);
        }
        // do something similar in equals.
    }

}
