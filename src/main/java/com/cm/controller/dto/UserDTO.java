package com.cm.controller.dto;

import com.cm.modelo.User;

public class UserDTO {
	private String name;
	private String email;
	
	public UserDTO() {
		
	}
	public UserDTO(String name, String email) {
		this.name = name;
		this.email = email;
	}
	public UserDTO(User u) {
		this.email = u.getUsername();
		this.name = u.getNome();
	}
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
	
	

	
}
