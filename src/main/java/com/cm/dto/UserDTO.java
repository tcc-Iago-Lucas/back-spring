package com.cm.dto;

import com.cm.modelo.User;

public class UserDTO {
	private String name;
	private String email;
	private String userName;
	private Long id;
	
	public UserDTO() {
		
	}
	public UserDTO(String name, String email, String userName) {
		this.name = name;
		this.email = email;
		this.userName = userName;
	}
	public UserDTO(User u) {
		this.email = u.getEmail();
		this.name = u.getNome();
		this.id = u.getId();
		this.userName = u.getUsername();
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
