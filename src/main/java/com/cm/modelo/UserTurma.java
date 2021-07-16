package com.cm.modelo;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class UserTurma implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id  @GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private Long id;
	
	private BigDecimal ranking;
	
	@ManyToOne
	@JsonManagedReference
	@JoinColumn(name = "id_user")
	private User user;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "id_turma")
	private Turma turma;

	public UserTurma( User user, Turma turma) {

		this.user = user;
		this.turma = turma;
	}

	public UserTurma() {
		super();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
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
