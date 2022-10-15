package com.cm.dto;

public class RespostaDTO {
	
	private String questaoCodigo;
	private String alternativaCodigo;
	
	
	
	
	public RespostaDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public RespostaDTO(String alternativaCodigo, String questaoCodigo) {
		super();
		this.alternativaCodigo = alternativaCodigo;
		this.questaoCodigo = questaoCodigo;
	}


	public String getQuestaoCodigo() {
		return questaoCodigo;
	}

	public void setQuestaoCodigo(String questaoCodigo) {
		this.questaoCodigo = questaoCodigo;
	}

	public String getAlternativaCodigo() {
		return alternativaCodigo;
	}

	public void setAlternativaCodigo(String alternativaCodigo) {
		this.alternativaCodigo = alternativaCodigo;
	}


}
