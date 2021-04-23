package com.cm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cm.modelo.Desempenho;
import com.cm.modelo.UserTurma;

@Repository
public interface DesempenhoRepository extends JpaRepository<Desempenho, Long> {

	List<Desempenho> findByUserTurma(UserTurma userTurma);

}
