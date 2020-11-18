package com.cm.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cm.controller.dto.CadastrarDTO;
import com.cm.modelo.User;
import com.cm.repository.UserRepository;
import com.cm.service.exceptions.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repo;
	
	
	
	public User create(CadastrarDTO cadastro) {
		User u = new User(cadastro);
		BCryptPasswordEncoder hashPassowrd = new BCryptPasswordEncoder();
		u.setSenha(hashPassowrd.encode(cadastro.getPassword()));
		return repo.save(u);
		
		
	}



	public User find(Long id) {
		// TODO Auto-generated method stub
		Optional<User> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Usuario não encontrado com esse id: " + id)) ;
	}

}
