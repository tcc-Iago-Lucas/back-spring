package com.cm.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cm.controller.dto.CadastrarDTO;
import com.cm.modelo.User;
import com.cm.service.TokenService;
import com.cm.service.UserService;
import com.cm.controller.dto.*;

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
	
	
	@PostMapping
	@RequestMapping("/cadastrar")
	public ResponseEntity<Object> cadastrar(@RequestBody CadastrarDTO cadastrarDTO){
		UsernamePasswordAuthenticationToken dadosLogin = cadastrarDTO.converter();
		User  user = s.create(cadastrarDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		try {
			Authentication authentication = authManager.authenticate(dadosLogin);
			String token = tokenService.gerarToken(authentication);
			return ResponseEntity.created(uri).body(new TokenDto(token, "Bearer"));
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	

}
