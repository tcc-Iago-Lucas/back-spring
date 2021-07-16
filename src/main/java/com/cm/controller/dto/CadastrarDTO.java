package com.cm.controller.dto;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class CadastrarDTO {

	
	private String name;
	private String email;
	private String password;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
