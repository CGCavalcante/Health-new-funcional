package com.maishealth.maishealth.usuario.dominio;
/**
 * Classe com sets e gets dos atributos da Consulta
 * objeto Consulta
 */
public class Consulta {
    private long idPaciente;
    private long idMedico;
    private String status;
    private long id;
    private String data;
    private String turno;

    public long getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(long idPaciente) {
        this.idPaciente = idPaciente;
    }

    public long getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(long idMedico) {
        this.idMedico = idMedico;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getId() { return id;}

    public void setId(long id) { this.id = id; }

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

