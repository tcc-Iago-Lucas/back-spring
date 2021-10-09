package com.cm.controller.dto;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginDTO {
	private String email;
	private String password;
	

	public LoginDTO() {
		super();
		// TODO Auto-generated constructor stub
	}


	public LoginDTO(String email, String senha) {
		super();
		this.email = email;
		this.password = senha;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}

	public UsernamePasswordAuthenticationToken converter() {
		System.out.println("email: " + email + " senha: " + password);
		return new UsernamePasswordAuthenticationToken(email, password);
	}
}
