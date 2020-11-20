package com.cm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cm.modelo.Turma;
import com.cm.modelo.User;
import com.cm.modelo.UserTurma;
import com.cm.repository.UserTurmaRepository;

@Service
public class UserTurmaService {
	@Autowired private UserTurmaRepository repo;
	
	public void create(User u, Turma t) {
		UserTurma ut = new UserTurma(u, t);
		
		repo.save(ut);
	}

}
