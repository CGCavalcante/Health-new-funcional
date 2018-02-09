package com.maishealth.maishealth.usuario.dominio;

public class DadosConsMed {
    private int id;
    private long idCons;
    private String nomePac;
    private String data;
    private String turno;

    public DadosConsMed(int id, long idCons, String nomePac, String data, String turno) {
        this.id = id;
        this.idCons = idCons;
        this.nomePac = nomePac;
        this.data = data;
        this.turno = turno;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getIdCons() {
        return idCons;
    }

    public void setIdCons(long idCons) {
        this.idCons = idCons;
    }

    public String getNomePac() {
        return nomePac;
    }

    public void setNomePac(String nomePac) {
        this.nomePac = nomePac;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }
}
