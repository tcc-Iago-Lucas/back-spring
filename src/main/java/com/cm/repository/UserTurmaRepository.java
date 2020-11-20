package com.cm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cm.modelo.UserTurma;

@Repository
public interface UserTurmaRepository extends JpaRepository<UserTurma, Long> {

}
