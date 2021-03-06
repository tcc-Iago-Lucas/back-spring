package com.cm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cm.modelo.Alternativa;

@Repository
public interface AlternativaRepository extends JpaRepository<Alternativa, Long> {

}