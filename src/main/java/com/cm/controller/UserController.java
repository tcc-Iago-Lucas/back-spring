package com.cm.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cm.controller.dto.CadastrarDTO;
import com.cm.controller.dto.TokenDto;
import com.cm.controller.dto.UserDTO;
import com.cm.modelo.User;
import com.cm.service.TokenService;
import com.cm.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired private UserService s;
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired private TokenService tokenService;
	
	@PostMapping
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
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> show(@PathVariable Long id) {
		return ResponseEntity.ok( s.show(id));
	}

	@GetMapping("/profile")
	public ResponseEntity<UserDTO> profile(@RequestHeader("Authorization") String token){
		return ResponseEntity.ok(s.profile(token));
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody UserDTO u, @PathVariable Long id){
		
		s.update(id, u);
		return ResponseEntity.noContent().build();
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<?>  delete(@PathVariable Long id){
		s.delete(id);
		
		return ResponseEntity.ok().build();
	}
	
}
