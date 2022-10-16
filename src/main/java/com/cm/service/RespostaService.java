package com.cm.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cm.dto.RespostaDTO;
import com.cm.exception.BadRequestException;
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
	@Autowired private DesempenhoService desempenhoService;

	private Boolean haveAtivo = false;
	private Turma turma = new Turma() ;
	private UserTurma userTurma = new UserTurma();
	
	public Resposta responder(String token, RespostaDTO respostaDTO) {
		this.haveAtivo = false;
		Long idUsuario = tokenService.getIdUser(token.substring(7, token.length()));
		User usuario = userService.find(idUsuario);
		Alternativa alternativa = alternativaService.findByCodigo(respostaDTO.getAlternativaCodigo());
		

		
		usuario.getUsuariosTurma().forEach(t -> {
			if(t.getTurma().getAtivo()) {
				this.haveAtivo = t.getTurma().getAtivo();
				this.setTurma(t.getTurma());
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
		
		Resposta respondeu = repo.jaRespondeu(alternativa.getQuestao(), this.userTurma);
		if(!Objects.isNull(respondeu)) {
			throw new BadRequestException("Você já respondeu esse questão nessa turma");
		}
		
		
		Resposta resposta = new Resposta(alternativa, this.userTurma);
		
		resposta = repo.save(resposta);
		
		temCalcularDesempenho(alternativa);

		
		return resposta;
		
	}
	
	private void temCalcularDesempenho(Alternativa alternativa) {
		int nquestaoPorTema = alternativa.getQuestao().getTema().getQuestaos().size();
		List<Resposta> nTemaRespondido = repo.TemaRespondido(alternativa.getQuestao().getTema(), this.userTurma);
		if(nquestaoPorTema == nTemaRespondido.size()) {
			desempenhoService.calcularPorTema(nTemaRespondido, this.userTurma);
		}
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
