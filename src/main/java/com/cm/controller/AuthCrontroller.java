package com.cm.controller;

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

import com.cm.controller.dto.CadastrarDTO;
import com.cm.controller.dto.TokenDto;
import com.cm.controller.exception.BadRequestException;
import com.cm.service.TokenService;

@RestController
public class AuthCrontroller {
	
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired private TokenService tokenService;
	
	@GetMapping
	@RequestMapping("/")
	public String helloWorld() {
		return "Hello Wolrd!";
	}
	
	@PostMapping
	@RequestMapping("/auth")
	public ResponseEntity<?> login(@RequestBody CadastrarDTO cadastrarDTO){
		UsernamePasswordAuthenticationToken dadosLogin = cadastrarDTO.converter();
		try {
			Authentication authentication = authManager.authenticate(dadosLogin);
			String token = tokenService.gerarToken(authentication);
			return ResponseEntity.ok(new TokenDto(token, "Bearer"));
		} catch (AuthenticationException e) {
			throw new BadRequestException(e.getMessage());
		}
	}
	
	
	

}
