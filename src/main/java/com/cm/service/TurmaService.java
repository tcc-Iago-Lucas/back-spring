package com.cm.service;

import java.util.Optional;

import com.cm.exception.BadRequestException;
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
	@Autowired private TokenService tokenService;
	
	public Turma create(String token, TurmaDTO turmaDTO) {
		User u = userService.find(tokenService.getIdUser(token.substring(7)));
		Optional<Turma> optionalTurma = repo.findByCodigo(turmaDTO.getCodigo());
		if (optionalTurma.isPresent()){
			throw new BadRequestException("Já tem turma com esse codigo");
		}
		Turma t = new Turma(u, turmaDTO);
		t = repo.save(t);
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


	public Turma findBycodigoTurma(String codigo){
		Optional<Turma> obj = repo.findBycodigoTurma(codigo);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Turma não encontrada com esse codigo: " + codigo));
	}
	public void deleteTurma(String token, Long id) {
		User u = userService.find(tokenService.getIdUser(token.substring(7)));
		Turma t = show(id);
		if(!u.getId().equals(t.getUser().getId())){
			throw  new BadRequestException("Voce nao pode excluir essa turma");
		}
		t.setAtivo(false);
		repo.save(t);
		
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

	public void matricular(String token, String codigo) {
		Turma t = findBycodigoTurma(codigo);
		User user = userService.find(tokenService.getIdUser(token.substring(7, token.length())));

		user.getUsuariosTurma().forEach(ut -> {
			if(ut.getTurma().getAtivo()){
				throw  new BadRequestException("Você já está ativo em uma turma");
			}
		});

		utservice.create(user, t);
	}
}
