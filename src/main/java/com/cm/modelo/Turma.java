package com.cm.modelo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.cm.dto.TurmaDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Turma implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id  @GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private Long id;
	
	@JsonManagedReference
	@ManyToOne
	@JoinColumn( name = "user_id")
	private User user;
	@Column(nullable = false, length = 50 , unique= true)
	private String codigo;

	private String codigoTurma;

	private String name;
	
	private Boolean ativo;

	@JsonManagedReference
	@OneToMany(mappedBy="turma", cascade = CascadeType.REMOVE)
	private Set<UserTurma> UsuariosTurma = new HashSet<>();

	public Turma() {

	}

	public Turma(User user, TurmaDTO turmaDTO) {
		super();
		this.user = user;
		this.name = turmaDTO.getName();
		this.codigo = turmaDTO.getCodigo();
		this.codigoTurma = turmaDTO.getCodigo();
		this.ativo = true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	@JsonIgnore
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	public Set<UserTurma> getUsuariosTurma() {
		return UsuariosTurma;
	}
	@JsonIgnore
	public void setUsuariosTurma(Set<UserTurma> usuariosTurma) {
		UsuariosTurma = usuariosTurma;
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
		Turma other = (Turma) obj;
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

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}


	public String getCodigoTurma() {
		return codigoTurma;
	}

	public void setCodigoTurma(String codigoTurma) {
		this.codigoTurma = codigoTurma;
	}
}
