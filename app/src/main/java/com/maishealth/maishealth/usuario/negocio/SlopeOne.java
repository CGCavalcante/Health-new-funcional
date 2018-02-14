package com.maishealth.maishealth.usuario.negocio;


import android.content.Context;

import com.maishealth.maishealth.usuario.dominio.Pessoa;

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
    private Context context;
    /*private AvaliacaoDao buscaAvaliacao;
    private ReadPessoa buscaPessoa;
    private ReadLivro buscaLivro;*/

    private Map<Integer, Map<Integer, Double>> matrizDiferenca;
    private Map<Integer, Map<Integer, Integer>> matrizFrequencia;
    private Map<Integer, Map<Integer, Double>> data = new HashMap<Integer, Map<Integer, Double>>();

    /*private static List<Livro> todosLivros = new ArrayList<>();
    private static List<Pessoa> todosUsuarios = new ArrayList<>();
    private static List<Avaliacao> produtosClassificados = new ArrayList<>();
    private static List<Livro> produtosRecomendadosOrdenados;

    public SlopeOne(Context context) {
        buscaLivro = new ReadLivro(context);
        buscaPessoa = new ReadPessoa(context);
        buscaAvaliacao = new AvaliacaoDao(context);
    }*/

    public static Set<Integer> getRecomendacao(Map<Integer, Double> notasUsuario, Pessoa usuario) {
        return ordenarCompare(notasUsuario, usuario);
    }

    /*public void leituraDados() {
        ** InÃ­cio - Simulando a leitura dos dados do sistema para o cÃ¡lculo das recomendaÃ§Ãµes *
        todosLivros = buscaLivro.getListaLivro();
        todosUsuarios = buscaPessoa.getListaPessoas();
        **CriaÃ§Ã£o da lista de notas dadas pelos usuÃ¡rios aos produtos*
        for (Pessoa usuario : todosUsuarios) {
            HashMap<Integer, Double> notasUsuario = new HashMap<Integer, Double>();
            **Lista de Objetos do DomÃ­nio RecomendacaoLivro*
            produtosClassificados = buscaAvaliacao.getListaAvaliacaoPessoa(usuario.getId());
            for (Avaliacao recomendacaoProduto : produtosClassificados) {
                notasUsuario.put(recomendacaoProduto.getIdLivro(), recomendacaoProduto.getAvaliacao());
            }
            data.put(usuario.getId(), notasUsuario);
        }
    }*/

    public static Set<Integer> ordenarCompare(Map<Integer, Double> map, Pessoa usuario) {
        Comparador comparador = new Comparador(map);
        Map<Integer, Double> sorted_map = new TreeMap<Integer, Double>(comparador);
        sorted_map.putAll(map);
        return sorted_map.keySet();
    }

    public List listaRecomendacao(Pessoa pessoa) {
        return calculaRecomendacoes(data, pessoa);
    }

    public void criarMatrizDiferenca(Map<Integer, Map<Integer, Double>> data) {
        matrizDiferenca = new HashMap<Integer, Map<Integer, Double>>();
        matrizFrequencia = new HashMap<Integer, Map<Integer, Integer>>();
        /** first iterate through users*/
        for (Map<Integer, Double> user : data.values()) {
            /** then iterate through user data*/
            for (Map.Entry<Integer, Double> entry : user.entrySet()) {
                if (!matrizDiferenca.containsKey(entry.getKey())) {
                    matrizDiferenca.put(entry.getKey(), new HashMap<Integer, Double>());
                    matrizFrequencia.put(entry.getKey(), new HashMap<Integer, Integer>());
                }
                for (Map.Entry<Integer, Double> entry2 : user.entrySet()) {
                    int oldcount = 0;
                    if (matrizFrequencia.get(entry.getKey()).containsKey(entry2.getKey()))
                        oldcount = matrizFrequencia.get(entry.getKey()).get(entry2.getKey()).intValue();
                    Double olddiff = 0.0;
                    if (matrizDiferenca.get(entry.getKey()).containsKey(entry2.getKey()))
                        olddiff = matrizDiferenca.get(entry.getKey()).get(entry2.getKey()).doubleValue();
                    Double observeddiff = entry.getValue() - entry2.getValue();
                    matrizFrequencia.get(entry.getKey()).put(entry2.getKey(), oldcount + 1);
                    matrizDiferenca.get(entry.getKey()).put(entry2.getKey(), olddiff + observeddiff);
                }
            }
        }
        for (Integer j : matrizDiferenca.keySet()) {
            for (Integer i : matrizDiferenca.get(j).keySet()) {
                Double oldvalue = matrizDiferenca.get(j).get(i).doubleValue();
                int count = matrizFrequencia.get(j).get(i).intValue();
                matrizDiferenca.get(j).put(i, oldvalue / count);
            }
        }
    }

    public Map<Integer, Double> predict(Map<Integer, Double> notasUsuario) {
        HashMap<Integer, Double> predictions = new HashMap<Integer, Double>();
        HashMap<Integer, Integer> frequencies = new HashMap<Integer, Integer>();
        for (Integer j : matrizDiferenca.keySet()) {
            frequencies.put(j, 0);
            predictions.put(j, 0.0);
        }
        for (Integer j : notasUsuario.keySet()) {
            for (Integer k : matrizDiferenca.keySet()) {
                try {
                    Double novoValor = (matrizDiferenca.get(k).get(j).doubleValue() + notasUsuario.get(j).doubleValue()) * matrizFrequencia.get(k).get(j).intValue();
                    predictions.put(k, predictions.get(k) + novoValor);
                    frequencies.put(k, frequencies.get(k) + matrizFrequencia.get(k).get(j).intValue());
                } catch (NullPointerException e) {
                }
            }
        }
        HashMap<Integer, Double> cleanpredictions = new HashMap<Integer, Double>();
        for (Integer j : predictions.keySet()) {
            if (frequencies.get(j) > 0) {
                cleanpredictions.put(j, predictions.get(j).doubleValue() / frequencies.get(j).intValue());
            }
        }
        for (Integer j : notasUsuario.keySet()) {
            cleanpredictions.put(j, notasUsuario.get(j));
        }
        return cleanpredictions;
    }

    private List<Integer> calculaRecomendacoes(Map<Integer, Map<Integer, Double>> data, Pessoa usuarioLogado) {
        criarMatrizDiferenca(data);

        Set<Integer> listIdOrdenados = getRecomendacao(predict(data.get(usuarioLogado.getId())), usuarioLogado);
        List<Integer> produtosRecomendadosOrdenados = new ArrayList<>();
        for (Integer i : listIdOrdenados) {
            produtosRecomendadosOrdenados.add(i);
        }
        return produtosRecomendadosOrdenados;
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
