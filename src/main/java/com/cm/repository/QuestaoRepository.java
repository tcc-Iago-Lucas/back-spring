package com.cm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cm.modelo.Questao;

@Repository
public interface QuestaoRepository extends JpaRepository<Questao, Long> {

}
