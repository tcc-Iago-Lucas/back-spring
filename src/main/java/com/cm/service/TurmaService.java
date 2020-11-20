package com.cm.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cm.controller.dto.TurmaDTO;
import com.cm.modelo.Turma;
import com.cm.modelo.User;
import com.cm.repository.TurmaRepository;
import com.cm.service.exceptions.ObjectNotFoundException;

@Service
public class TurmaService {

	@Autowired private UserService userService;
	@Autowired private TurmaRepository repo;
	
	public Turma create(TurmaDTO turmaDTO) {
		User u = userService.find(turmaDTO.getUser_id());
		Turma t = new Turma(u, turmaDTO.getName());
		return repo.save(t);
	}

	public Object show(Long id) {
		Optional<Turma> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Usuario não encontrado com esse id: " + id));
	}

	
	
}
