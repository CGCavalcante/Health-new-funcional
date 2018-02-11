package com.maishealth.maishealth.usuario.dominio;


public class Recomendacao {
    private long id;
    private long idPaciente;
    private long idMedico;
    private long idConsulta;
    private int nota;
    private String descricao;


    /**
     * Construtor para instanciar.
     */
    public Recomendacao() {
    }

    /**
     * Construtor para buscar no banco.
     */

    public Recomendacao( long id, long idPaciente, long idMedico, int nota) {
        this.id = id;
        this.idPaciente = idPaciente;
        this.idMedico = idMedico;
        this.nota = nota;
    }

    //Construtor para inserir.
    public Recomendacao(long idPaciente, long idMedico, int nota) {
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

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota=nota;
    }

    public long getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(long idConsulta) {
        this.idConsulta=idConsulta;
    }


    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao=descricao;
    }
}
