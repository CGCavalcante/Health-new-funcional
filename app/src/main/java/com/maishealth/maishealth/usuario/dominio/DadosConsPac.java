package com.maishealth.maishealth.usuario.dominio;

public class DadosConsPac {
    private int id;
    private long idCons;
    private String nomeMed;
    private String especMed;
    private String data;
    private String turno;

    public DadosConsPac(int id, long idCons, String nomeMed, String especMed, String data, String turno) {
        this.id = id;
        this.idCons = idCons;
        this.nomeMed = nomeMed;
        this.especMed = especMed;
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

    public String getNomeMed() {
        return nomeMed;
    }

    public void setNomeMed(String nomeMed) {
        this.nomeMed = nomeMed;
    }

    public String getEspecMed() {
        return especMed;
    }

    public void setEspecMed(String especMed) {
        this.especMed = especMed;
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
