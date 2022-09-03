package com.cm.dto;

import com.cm.modelo.User;

public class UserDTO {
	private String name;
	private String email;
	private Long id;
	
	public UserDTO() {
		
	}
	public UserDTO(String name, String email) {
		this.name = name;
		this.email = email;
	}
	public UserDTO(User u) {
		this.email = u.getUsername();
		this.name = u.getNome();
		this.id = u.getId();
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
