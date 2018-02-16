package com.maishealth.maishealth.usuario.dominio;

import java.io.Serializable;

/**
 * Created by Wenderson de Souza on 01/02/2018.
 */

public class DadosMedico implements Serializable {
    private int id;
    private String nome;
    private String especialidade;
    private String avaliacao;
    private long idmedico;

    public DadosMedico(int id, String nome, String especialidade, String avaliacao, long idmedico) {
        this.id = id;
        this.nome = nome;
        this.especialidade = especialidade;
        this.avaliacao = avaliacao;
        this.idmedico = idmedico;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(String avaliacao) {
        this.avaliacao = avaliacao;
    }

    public long getIdmedico() {
        return idmedico;
    }

    public void setIdmedico(long idmedico) {
        this.idmedico = idmedico;
    }
}
