package com.cm.repository;

import com.cm.modelo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cm.modelo.Alternativa;

import java.util.Optional;

@Repository
public interface AlternativaRepository extends JpaRepository<Alternativa, Long> {

    Optional<Alternativa> findByCodigo(String codigo);

}