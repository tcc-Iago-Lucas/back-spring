package com.cm.controller.dto;

import java.util.List;

public class QuestaoDTO {

    private String enuciado;
    private List<AlternativaDTO> alternativas;
    private Long temaId;

    public QuestaoDTO() {
    }

    public Long getTemaId() {
        return temaId;
    }

    public void setTemaId(Long temaId) {
        this.temaId = temaId;
    }

    public String getEnuciado() {
        return enuciado;
    }

    public void setEnuciado(String enuciado) {
        this.enuciado = enuciado;
    }

    public List<AlternativaDTO> getAlternativas() {
        return alternativas;
    }

    public void setAlternativaDTOS(List<AlternativaDTO> alternativas) {
        this.alternativas = alternativas;
    }
}
