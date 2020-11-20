package com.cm.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cm.controller.dto.CadastrarDTO;
import com.cm.controller.dto.UserDTO;
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



	public UserDTO show(Long id) {
		// TODO Auto-generated method stub
		
		
		UserDTO u = new UserDTO(find(id));
		return u;
	}


	public User find(Long id) {
		Optional<User> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Usuario não encontrado com esse id: " + id));
	}
	public void update(Long id, UserDTO u) {
		User user = find(id);
		if(u.getEmail() != null && !u.getEmail().isEmpty()) {
			user.setEmail(u.getEmail());
		}
		if(u.getName() != null &&  !u.getName().isEmpty()) {
			user.setNome(u.getName());
		}
		
		 repo.save(user);
	}



	public void delete(Long id) {
			repo.delete(find(id));
		
	}

}
