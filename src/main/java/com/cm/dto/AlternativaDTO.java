package com.cm.dto;

public class AlternativaDTO {

    private String alternativa;
    private Boolean certo;
    private String codigo;

    public AlternativaDTO() {
    }

    public AlternativaDTO(String alternativa, Boolean certo) {
        this.alternativa = alternativa;
        this.certo = certo;
    }

    public String getAlternativa() {
        return alternativa;
    }

    public void setAlternativa(String alternativa) {
        this.alternativa = alternativa;
    }

    public Boolean getCerto() {
        return certo;
    }

    public void setCerto(Boolean certo) {
        this.certo = certo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
