package com.cm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cm.controller.dto.CadastrarDTO;
import com.cm.controller.dto.UserDTO;
import com.cm.modelo.User;
import com.cm.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repo;
	
	
	
	public User create(CadastrarDTO cadastro) {
		User u = new User(cadastro);
		BCryptPasswordEncoder hashPassowrd = new BCryptPasswordEncoder();
		u.setSenha(hashPassowrd.encode(cadastro.getSenha()));
		return repo.save(u);
		
		
	}



	public User find(Long id) {
		// TODO Auto-generated method stub
		return  repo.findById(id).get();
	}

}
