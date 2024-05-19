package com.projectjava.demosclient.dao;

import com.projectjava.demosclient.entity.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserDao extends JpaRepository<Usuario, Long> {


     Optional<Usuario> findByEmail(String email);

     @Query("SELECT u FROM Usuario u JOIN FETCH u.rol WHERE u.email = :email")
     Optional<Usuario> findByEmailWithRol(@Param("email") String email);



     Page<Usuario> findAll(Pageable pageable);
}






