package com.projectjava.demosclient.dao;

import com.projectjava.demosclient.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserDao extends JpaRepository<Usuario, Long> {


     Usuario findByEmail(String email);

}






