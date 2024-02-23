package com.projectjava.demosclient;

import com.projectjava.demosclient.dao.UserDao;
import com.projectjava.demosclient.entity.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
/*
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UsuariosTest {

    @Autowired
    UserDao userDao;

    @Autowired
    TestEntityManager tem;

    @Test
    public void testUsuarios(){
        Rol rolAdmin = new Rol(2L, "Admin");
        Rol roluser = new Rol(3L, "user");
        tem.merge(roluser);
    }


    @Test
    public void AgnadirUsuarioConRol() {
        Rol rolAdmin = tem.find(Rol.class, 2);

        Usuario usuario = new Usuario(4L, "emilio@hotmail.com", "123245");

        usuario.añadirRol(rolAdmin);
        userDao.save(usuario);

    }

    @Test
    public void AgnadirUsuarioConOtroRol() {
        Rol rolAdmin = tem.find(Rol.class, 1);

        Usuario usuario = new Usuario(4L, "manualescrito@hotmail.com", "RSA");

        usuario.añadirRol(rolAdmin);
        userDao.save(usuario);

    }

    @Test
    public void eliminarRol(){
        Usuario usuario = userDao.findById(2L).get();
        Rol rol = new Rol(1L);

        usuario.eliminarRol(rol);
    }

        
}

 */
