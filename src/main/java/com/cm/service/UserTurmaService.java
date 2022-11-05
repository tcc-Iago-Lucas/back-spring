package com.cm.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

import com.cm.modelo.Desempenho;
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

	@Autowired private DesempenhoService desempenhoService;
	@Autowired private  TemaService temaService;
	
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

    public void calcularRanking(Set<UserTurma> usuariosTurma) {

		usuariosTurma.forEach(ut -> {
			List<Desempenho> desempenhos = new ArrayList<>();
			desempenhos = desempenhoService.findByUserTurma(ut.getId());
			ut.setRanking(totalDesempenho(desempenhos));
			updateUserTurma(ut);
		});
    }

	private void updateUserTurma(UserTurma ut) {
		repo.save(ut);
	}

	private Float totalDesempenho(List<Desempenho> desempenhos) {
		Float total = Float.valueOf(0);
		for (int i = 0 ; i < desempenhos.size(); i++){
			total += desempenhos.get(i).getAproveitamento();
		}

		return total/ temaService.totalTemas() ;
	}
}
