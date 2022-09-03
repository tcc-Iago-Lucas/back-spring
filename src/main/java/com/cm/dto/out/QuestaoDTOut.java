package com.cm.dto.out;

import com.cm.modelo.Questao;

public class QuestaoDTOut {

    private Long id;

    private String enuciado;

    public QuestaoDTOut() {
    }
    public QuestaoDTOut(Questao questao){
        this.id = questao.getId();
        this.enuciado =questao.getEnuciado();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEnuciado() {
        return enuciado;
    }

    public void setEnuciado(String enuciado) {
        this.enuciado = enuciado;
    }
}
