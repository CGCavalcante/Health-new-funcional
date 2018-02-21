package com.maishealth.maishealth.usuario.dominio;
/**
 * Classe com sets e gets dos atributos da Paciente
 * objeto Paciente
 */

public class Paciente {
    private long id;
    private long idUsuario;
    private String tipoSangue;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getTipoSangue() {
        return tipoSangue;
    }

    public void setTipoSangue(String tipoSangue) {
        this.tipoSangue = tipoSangue;
    }
}
