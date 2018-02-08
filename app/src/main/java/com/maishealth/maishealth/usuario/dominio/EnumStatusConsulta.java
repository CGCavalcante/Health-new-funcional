package com.maishealth.maishealth.usuario.dominio;


public enum EnumStatusConsulta {

    EMANDAMENTO("Em Andamento"),
    CANCELADA ("Cancelada"),
    CONCLUIDA("Concluida"),
    DISPONIVEL("Disponivel");

    private final String valor;

    EnumStatusConsulta(String valor){
        this.valor = valor;
    }

    public String getValor(){
        return valor;
    }

    public static String[] enumStatusConsulta(){
        EnumStatusConsulta[] listaStatusConsulta = EnumStatusConsulta.values();
        String[] lista = new String[listaStatusConsulta.length];
        for (int i =0; i<listaStatusConsulta.length; i++){
            lista[i] = listaStatusConsulta[i].getValor();
        }
        return lista;
    }


}
