package com.cm.modelo;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Desempenho implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id  @GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private Long id;

    private Float aproveitamento;
    
    @JsonManagedReference
    @ManyToOne
	@JoinColumn( name = "id_tema")
    private Tema tema;
    
	@ManyToOne
   	@JoinColumn( name = "id_userturma")
	private UserTurma userTurma;
	@Column(nullable = false, length = 50 , unique= true)
	private String codigo;

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


	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
}
