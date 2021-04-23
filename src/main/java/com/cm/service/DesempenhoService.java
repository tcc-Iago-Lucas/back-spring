package com.cm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cm.modelo.Desempenho;
import com.cm.modelo.Resposta;
import com.cm.modelo.UserTurma;
import com.cm.repository.DesempenhoRepository;

@Service
public class DesempenhoService {
	@Autowired private DesempenhoRepository repo;
	@Autowired private UserTurmaService userTurmaService;
	private int acertou = 0;
	public void calcularPorTema(List<Resposta> respostas, UserTurma userTurma) {
		this.acertou = 0;
		Desempenho desempenho = new Desempenho();
		desempenho.setTema(respostas.get(0).getTema());
		respostas.forEach(r -> {
			if(r.getAcertou()) {
				this.acertou++;
				
			}
		});
		if(this.acertou == 0) {
			desempenho.setAproveitamento((float) 0);
		}else {
			float result = ((float)this.acertou/(float)respostas.size());
			desempenho.setAproveitamento(result);
		}
		desempenho.setUserTurma(userTurma);
		repo.save(desempenho);
	}
	public List<Desempenho> findByUserTurma(Long userTurmaId) {
		UserTurma userTurma = userTurmaService.find(userTurmaId);
		return repo.findByUserTurma(userTurma);
	}


}
