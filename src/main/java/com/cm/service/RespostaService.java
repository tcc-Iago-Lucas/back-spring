package com.cm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cm.controller.dto.RespostaDTO;
import com.cm.controller.exception.BadRequestException;
import com.cm.modelo.Alternativa;
import com.cm.modelo.Resposta;
import com.cm.modelo.Turma;
import com.cm.modelo.User;
import com.cm.modelo.UserTurma;
import com.cm.repository.RespostaRepository;

@Service
public class RespostaService {
	
	@Autowired private TokenService tokenService;
	@Autowired private UserService userService;
	@Autowired private AlternativaService alternativaService;
	@Autowired private RespostaRepository repo;

	private Boolean haveAtivo = false;
	private Turma turma = new Turma() ;
	private UserTurma userTurma = new UserTurma();
	
	public Resposta responder(String token, RespostaDTO respostaDTO) {
		
		Long idUsuario = tokenService.getIdUser(token.substring(7, token.length()));
		User usuario = userService.find(idUsuario);
		Alternativa alternativa = alternativaService.find(respostaDTO.getAlternativaId());
		
		
		
		usuario.getTurmas().forEach(t -> {
			if(t.getAtivo()) {
				this.haveAtivo = t.getAtivo();
				this.setTurma(t);
			}
		});
		if(!this.haveAtivo) {
			throw new BadRequestException("O usuario não está em nenhuma turma ativa");
		}
		turma.getUsuariosTurma().forEach(ut -> {
			if(ut.getUser().equals(usuario)) {
				this.setUserTurma(ut);
			}
		});
		
		
		Resposta resposta = new Resposta(alternativa, this.userTurma);
		
		return repo.save(resposta);
		
		
	}
	public Turma getTurma() {
		return turma;
	}
	public void setTurma(Turma turma) {
		this.turma = turma;
	}
	public UserTurma getUserTurma() {
		return userTurma;
	}
	public void setUserTurma(UserTurma userTurma) {
		this.userTurma = userTurma;
	}

}
