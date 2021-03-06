package com.cm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cm.modelo.Turma;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Long> {

}
