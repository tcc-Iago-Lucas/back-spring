package com.cm.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Questao implements Serializable {

	private static final long serialVersionUID = 1L;
    @Id  @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
	private Long id;

    private String enuciado;

    @JsonManagedReference
    @ManyToOne
   	@JoinColumn( name = "id_tema")
    private Tema tema;

    @JsonManagedReference
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "questao")
    private List<Alternativa> alternativas = new ArrayList<Alternativa>();

    public List<Alternativa> getAlternativas() {
        return alternativas;
    }

    public void setAlternativas(List<Alternativa> alternativas) {
        this.alternativas = alternativas;
    }

    public Questao () {}

    public Questao(String enuciado, Tema tema) {
        this.enuciado = enuciado;
        this.tema = tema;
    }

    public String getEnuciado() {
        return enuciado;
    }

    public void setEnuciado(String enuciado) {
        this.enuciado = enuciado;
    }

    public Tema getTema() {
        return tema;
    }

    public void setTema(Tema tema) {
        this.tema = tema;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
