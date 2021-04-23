package com.cm.modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Desempenho implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    private Float aproveitamento;
    
    @JsonManagedReference
    @ManyToOne
	@JoinColumn( name = "id_tema")
    private Tema tema;
    
	@ManyToOne
   	@JoinColumn( name = "id_userturma")
	private UserTurma userTurma;


    public Desempenho(){ }

    
    

    public Tema getTema() {
		return tema;
	}




	public void setTema(Tema tema) {
		this.tema = tema;
	}




	public Float getAproveitamento() {
        return aproveitamento;
    }

    public void setAproveitamento(Float aproveitamento) {
        this.aproveitamento = aproveitamento;
    }




	public Long getId() {
		return id;
	}




	public void setId(Long id) {
		this.id = id;
	}




	public UserTurma getUserTurma() {
		return userTurma;
	}




	public void setUserTurma(UserTurma userTurma) {
		this.userTurma = userTurma;
	}
    
}
