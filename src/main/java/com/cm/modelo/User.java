package com.cm.modelo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cm.dto.CadastrarDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(schema = "public" , name = "usuario")
public class User implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id  @GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private Long id;
	private String nome;
	@Column(nullable = false, length = 50 , unique= true)
	private String email;
	@Column(nullable = false, length = 15 , unique= true)
	private String nickname;

	private String senha;
	@JsonBackReference
	@OneToMany( mappedBy="user")
	private List<Turma> turmas = new ArrayList<>();

	@JsonBackReference
	@OneToMany(mappedBy="user")
	@LazyCollection(LazyCollectionOption.FALSE)
	private Set<UserTurma> UsuariosTurma = new HashSet<>();
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setUserName(String nickName) {
		this.nickname = nickName;
	}

	public User(String nome, String email, String senha, String username) {
		super();
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.nickname = username;
	}

	public User(CadastrarDTO cadastro) {
		this.nome = cadastro.getName();
		this.email = cadastro.getEmail();
		this.senha = cadastro.getPassword();
		this.nickname = cadastro.getUserName();
	}
	public User() {
		
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	

	
	
	public List<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}
	@JsonIgnore
	public Set<UserTurma> getUsuariosTurma() {
		return UsuariosTurma;
	}

	public void setUsuariosTurma(Set<UserTurma> usuariosTurma) {
		UsuariosTurma = usuariosTurma;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@JsonIgnore
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.nickname;
	}



	public String getEmail() {
		return email;
	}




	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
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
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
}
