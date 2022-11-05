package com.cm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cm.modelo.Questao;
import com.cm.modelo.Resposta;
import com.cm.modelo.Tema;
import com.cm.modelo.UserTurma;

@Repository
public interface RespostaRepository  extends JpaRepository<Resposta, Long> {
	
	
	@Query("select r from #{#entityName} r where r.userTurma = :userTurma and  r.questao = :questao")
	Resposta jaRespondeu(Questao questao, UserTurma userTurma);

	
	@Query("select r from #{#entityName} r where r.userTurma = :userTurma and  r.tema = :tema")
	List<Resposta> temaRespondido(Tema tema, UserTurma userTurma);


}
