package com.maishealth.maishealth.usuario.dominio;

public class Recomendacao {
    private long idmed;
    private double nota;

    public Recomendacao(long idmed, double nota) {
        this.idmed=idmed;
        this.nota=nota;
    }

    public long getIdmed() {
        return idmed;
    }

    public void setIdmed(long idmed) {
        this.idmed=idmed;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota=nota;
    }
}
