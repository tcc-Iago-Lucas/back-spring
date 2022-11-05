package com.cm.dto.out;


public class RespostaUsuarioDTOut {

    private String tema;
    private String NomeUsuario;
    private String questao;
    private String AlternativaEscolhida;
    private Boolean acertou;


    public RespostaUsuarioDTOut() {
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getNomeUsuario() {
        return NomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        NomeUsuario = nomeUsuario;
    }

    public String getQuestao() {
        return questao;
    }

    public void setQuestao(String questao) {
        this.questao = questao;
    }

    public String getAlternativaEscolhida() {
        return AlternativaEscolhida;
    }

    public void setAlternativaEscolhida(String alternativaEscolhida) {
        AlternativaEscolhida = alternativaEscolhida;
    }

    public Boolean getAcertou() {
        return acertou;
    }

    public void setAcertou(Boolean acertou) {
        this.acertou = acertou;
    }
}
