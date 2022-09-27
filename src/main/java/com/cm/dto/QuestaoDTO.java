package com.cm.dto;

import java.util.List;

public class QuestaoDTO {

    private String enuciado;
    private List<AlternativaDTO> alternativas;
    private Long temaId;
    private String codigo;

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

    public void setAlternativas(List<AlternativaDTO> alternativas) {
        this.alternativas = alternativas;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
