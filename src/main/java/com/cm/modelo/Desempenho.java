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


    public Desempenho(){ }


    public Float getAproveitamento() {
        return aproveitamento;
    }

    public void setAproveitamento(Float aproveitamento) {
        this.aproveitamento = aproveitamento;
    }
}
