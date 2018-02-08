package com.maishealth.maishealth.usuario.dominio;

/**
 * Classe para getters and setters para usar na tabela Data_Horario
 */

public class DataHorario {

    private long id;
    private long idHorarioMedico;
    private String data;
    private long contador;

    public long getContador() {
        return contador;
    }

    public void setContador(long contador) {
        this.contador = contador;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public long getId() {

        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdHorarioMedico() {
        return idHorarioMedico;
    }

    public void setIdHorarioMedico(long idHorarioMedico) {
        this.idHorarioMedico = idHorarioMedico;
    }

}
