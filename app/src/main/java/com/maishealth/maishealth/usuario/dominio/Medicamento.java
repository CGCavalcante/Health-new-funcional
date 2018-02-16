package com.maishealth.maishealth.usuario.dominio;


public class Medicamento {
    private long id;
    private String nomeMedicamento;
    private String fornecedor;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id=id;
    }

    public String getNomeMedicamento() {
        return nomeMedicamento;
    }

    public void setNomeMedicamento(String nomeMedicamento) {
        this.nomeMedicamento=nomeMedicamento;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor=fornecedor;
    }
}
