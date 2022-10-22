package com.cm.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.cm.dto.TemaDTOIn;
import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Tema implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id  @GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private Long id;
	@Column(nullable = false, length = 50 , unique= true)
	private String codigo;
	private String tema;

	private Boolean temaJogo;
	@JsonBackReference
	@OneToMany( mappedBy="tema")
	private List<Desempenho> desempenhos = new ArrayList<>();
	@JsonBackReference
	@OneToMany( mappedBy="tema")
	private List<Questao> questaos = new ArrayList<>();

	public Tema(Long id, String tema) {
		super();
		this.id = id;
		this.tema = tema;
	}
	
	
	public Tema(String tema) {
		super();
		this.tema = tema;
	}

	public Tema(TemaDTOIn temaDTOIn){
		this.tema = temaDTOIn.getDescricao();
		this.codigo = temaDTOIn.getCodigo();
	}

	public Tema() {}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	public List<Desempenho> getDesempenhos() {
		return desempenhos;
	}

	public void setDesempenhos(List<Desempenho> desempenhos) {
		this.desempenhos = desempenhos;
	}

	public List<Questao> getQuestaos() {
		return questaos;
	}

	public void setQuestaos(List<Questao> questaos) {
		this.questaos = questaos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tema other = (Tema) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Boolean getTemaJogo() {
		return temaJogo;
	}

	public void setTemaJogo(Boolean temaJogo) {
		this.temaJogo = temaJogo;
	}
}
