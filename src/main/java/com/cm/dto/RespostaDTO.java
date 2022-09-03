package com.cm.dto;

public class RespostaDTO {
	
	private Long questaoId;
	private Long alternativaId;
	
	
	
	
	public RespostaDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public RespostaDTO(Long questaoId, Long alternativaId) {
		super();
		this.questaoId = questaoId;
		this.alternativaId = alternativaId;
	}


	public Long getQuestaoId() {
		return questaoId;
	}
	
	public void setQuestaoId(Long questãoId) {
		this.questaoId = questãoId;
	}
	public Long getAlternativaId() {
		return alternativaId;
	}
	public void setAlternativaId(Long alternativaId) {
		this.alternativaId = alternativaId;
	}

}
