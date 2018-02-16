package com.maishealth.maishealth.usuario.dominio;

public class RemedioDados {
    private int id;
    private String nome;
    private long idRemedio;

    public RemedioDados(int id, String nome, long idRemedio) {
        this.id = id;
        this.nome = nome;
        this.idRemedio = idRemedio;
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

    public long getIdRemedio() {
        return idRemedio;
    }

    public void setIdRemedio(long idRemedio) {
        this.idRemedio = idRemedio;
    }
}
