package com.cm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cm.modelo.Desempenho;

@Repository
public interface DesempenhoRepository extends JpaRepository<Desempenho, Long> {

}
