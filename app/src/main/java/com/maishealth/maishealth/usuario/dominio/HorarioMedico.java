package com.maishealth.maishealth.usuario.dominio;

/**
 * Classe para getters and setters para usar na tabela Horario_Medico
 */

public class HorarioMedico {
    private long id;
    private String diaSemana;
    private long vagas;
    private String turno;
    private long idMedico;


    public long getIdHorarioMedico() {
        return id;
    }

    public void setIdHorarioMedico(long id) {
        this.id = id;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    public long getVagas() {
        return vagas;
    }

    public void setVagas(long vagas) {
        this.vagas = vagas;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public long getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(long idMedico) {
        this.idMedico = idMedico;
    }
}
