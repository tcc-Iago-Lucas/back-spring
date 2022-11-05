package com.cm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cm.modelo.Tema;

@Repository
public interface TemaRepository extends JpaRepository<Tema, Long> {
    @Query(value = "select count(*) from tema", nativeQuery = true)
    Integer total();
}
