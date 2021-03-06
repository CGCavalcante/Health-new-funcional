package com.maishealth.maishealth.usuario.negocio;

import android.text.TextUtils;
import android.util.Patterns;

import com.maishealth.maishealth.infra.FormataData;
/**
 * Classe responsável pelos serviços de validações de campo e datas
 */

@SuppressWarnings({"FieldCanBeLocal", "BooleanMethodIsAlwaysInverted"})
public class ValidaCadastro {
    private final int TAMANHO_SENHA = 6;
    private final int TAMANHO_CPF = 14;
    private final int TAMANHO_CRM_MIN = 5;
    private final int TAMANHO_CRM_MAX = 13;
    private final int TAMANHO_DATA = 10;

    public boolean isCampoVazio(String texto){
        return (texto.trim().isEmpty() || TextUtils.isEmpty(texto));
    }

    public boolean isEmail(String texto){
        return (Patterns.EMAIL_ADDRESS.matcher(texto).matches());
    }

    public boolean isSenhaValida(String texto) {
        return !isCampoVazio(texto) && texto.length() >= TAMANHO_SENHA;
    }

    public boolean isCpfValida(String texto) {
        return !isCampoVazio(texto) && texto.length() == TAMANHO_CPF;
    }

    public  boolean isCrmValido (String texto) {
        return !isCampoVazio(texto) && (texto.length() >= TAMANHO_CRM_MIN && texto.length() <= TAMANHO_CRM_MAX);

    }

    public boolean isDataNascimento (String data){

        return (FormataData.dataExiste(data) && FormataData.dataMenorOuIgualQueAtual(data) && data.length() == TAMANHO_DATA);

    }

    public boolean isDataNoPassado(String data){
        return (FormataData.dataExiste(data) && FormataData.dataMaiorOuIgualQueAtual(data) && data.length() == TAMANHO_DATA);
    }


}
