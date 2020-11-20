package com.cm.modelo;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class UserTurma implements Serializable {

	private static final long serialVersionUID = 1L;
	@EmbeddedId
	private UserTurmaPK id =  new UserTurmaPK();
	private String codigo;
	private BigDecimal ranking;
	
	
	

	public UserTurma(User user, Turma turma) {
		this.id.setUser(user);
		this.getId().setTurma(turma);
	}

	
	public UserTurma() {
		super();
	}


	public User getUser() {
		return id.getUser();
	}
	
	public Turma getTurma() {
		return id.getTurma();
	}
	
	public UserTurmaPK getId() {
		return id;
	}
	public void setId(UserTurmaPK id) {
		this.id = id;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public BigDecimal getRanking() {
		return ranking;
	}
	public void setRanking(BigDecimal ranking) {
		this.ranking = ranking;
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
		UserTurma other = (UserTurma) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	} 
	
}
