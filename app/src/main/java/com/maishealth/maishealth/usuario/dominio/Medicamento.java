package com.maishealth.maishealth.usuario.dominio;
/**
 * Classe com sets e gets dos atributos da Medicamento
 * objeto Medicamento
 */

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
