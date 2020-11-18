package com.cm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cm.service.TokenService;
import com.cm.service.UserService;

@RestController
public class AuthCrontroller {
	
	@Autowired private UserService s;
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired private TokenService tokenService;
	
	@GetMapping
	@RequestMapping("/")
	public String helloWorld() {
		return "Hello Wolrd!";
	}
	
	
	
	

}
