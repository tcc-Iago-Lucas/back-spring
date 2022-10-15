package com.cm.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cm.modelo.Turma;
import com.cm.modelo.User;
import com.cm.modelo.UserTurma;
import com.cm.repository.UserTurmaRepository;
import com.cm.exception.ObjectNotFoundException;

@Service
public class UserTurmaService {
	@Autowired private UserTurmaRepository repo;
	
	public void create(User u, Turma t) {
		UserTurma ut = new UserTurma(u, t);
		
		repo.save(ut);
	}
	
	public UserTurma find(Long id) {
		Optional<UserTurma> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Não foi encrontado usuario de turma com esse id: " + id));
	}

	public UserTurma findUserTurmaAtivabyUser(Long idUser){
		return null;
	}
}
