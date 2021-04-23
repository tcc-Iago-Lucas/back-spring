package com.cm.modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Resposta implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
   	@JoinColumn( name = "id_questao")
	private Questao questao;
	@ManyToOne
   	@JoinColumn( name = "id_alternativa")
	private Alternativa alternativa;
	@ManyToOne
   	@JoinColumn( name = "id_userturma")
	private UserTurma userTurma;
	
	private Boolean acertou;
	
	
	
	public Resposta() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Resposta(Alternativa alternativa, UserTurma userTurma) {
		super();
		this.alternativa = alternativa;
		this.acertou = alternativa.getCerto();
		this.questao = alternativa.getQuestao();
		this.userTurma = userTurma;
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Questao getQuestao() {
		return questao;
	}
	public void setQuestao(Questao questao) {
		this.questao = questao;
	}
	public Alternativa getAlternativa() {
		return alternativa;
	}
	public void setAlternativa(Alternativa alternativa) {
		this.alternativa = alternativa;
	}
	public Boolean getAcertou() {
		return acertou;
	}
	public void setAcertou(Boolean acertou) {
		this.acertou = acertou;
	}
	
	
	

}
