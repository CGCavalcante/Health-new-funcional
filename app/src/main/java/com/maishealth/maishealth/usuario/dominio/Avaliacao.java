package com.maishealth.maishealth.usuario.dominio;
/**
 * Classe com sets e gets dos atributos da Avaliação
 * objeto Avaliação
 */
public class Avaliacao {
    private long id;
    private long idPaciente;
    private long idMedico;
    private double nota;

    /**
     * Construtor para instanciar.
     */
    public Avaliacao() {
    }

    /**
     * Construtor para buscar no banco.
     */

    public Avaliacao(long id, long idPaciente, long idMedico, int nota) {
        this.id = id;
        this.idPaciente = idPaciente;
        this.idMedico = idMedico;
        this.nota = nota;
    }

    //Construtor para inserir.
    public Avaliacao(long idPaciente, long idMedico, int nota) {
        this.idPaciente = idPaciente;
        this.idMedico = idMedico;
        this.nota = nota;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id=id;
    }

    public long getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(long idPaciente) {
        this.idPaciente=idPaciente;
    }

    public long getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(long idMedico) {
        this.idMedico=idMedico;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota=nota;
    }

}
