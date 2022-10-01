package com.cm.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cm.dto.TurmaDTO;
import com.cm.modelo.Turma;
import com.cm.modelo.User;
import com.cm.repository.TurmaRepository;
import com.cm.exception.ObjectNotFoundException;
import java.util.UUID;

@Service
public class TurmaService {

	@Autowired private UserService userService;
	@Autowired private TurmaRepository repo;
	@Autowired private UserTurmaService utservice;
	
	public Turma create(TurmaDTO turmaDTO) {
		User u = userService.find(turmaDTO.getUser_id());
		Turma t = new Turma(u, turmaDTO);
		t = repo.save(t);
		utservice.create(u,t);
		return t;
	}
	
	

	public Turma show(Long id) {
		Optional<Turma> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Turma não encontrada com esse id: " + id));
	}

	public void incluirAlunoNaTurma(Long turmaID, Long userID) {
		User u = userService.find(userID);
		Turma t = show(turmaID);
		utservice.create(u,t);
		
	}



	public void deleteTurma(Long id) {
		Turma t = show(id);
		repo.delete(t);
		
	}
	public void updateTurma(Turma t){
		repo.save(t);
	}

    public String gerarCodigo(Long id) {
		Turma t = show(id);
		String codigo = UUID.randomUUID().toString().substring(0,6);
		t.setCodigo(codigo);
		updateTurma(t);
		return  codigo;
    }
}
