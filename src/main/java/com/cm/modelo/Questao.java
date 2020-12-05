package com.cm.modelo;

public class Questao {

    private String enuciado;

    private Tema tema;


    public Questao () {}

    public Questao(String enuciado, Tema tema) {
        this.enuciado = enuciado;
        this.tema = tema;
    }

    public String getEnuciado() {
        return enuciado;
    }

    public void setEnuciado(String enuciado) {
        this.enuciado = enuciado;
    }

    public Tema getTema() {
        return tema;
    }

    public void setTema(Tema tema) {
        this.tema = tema;
    }
}
