package com.projectjava.demosclient.dao;

import com.projectjava.demosclient.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenDao extends JpaRepository<Token, Long> {
    Optional<Token> findByToken(@Param("token")String token);


    @Procedure("buscarTokenConEmail")
    List<Object[]> buscarTokenConEmail(@Param("token") String token);
}