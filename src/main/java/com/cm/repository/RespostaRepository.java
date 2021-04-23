package com.cm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cm.modelo.Resposta;

@Repository
public interface RespostaRepository  extends JpaRepository<Resposta, Long> {

}
