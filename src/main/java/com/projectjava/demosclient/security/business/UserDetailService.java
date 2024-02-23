package com.projectjava.demosclient.security.business;
/*
import com.projectjava.demosclient.dao.PasswordDao;
import com.projectjava.demosclient.dao.UserDao;
import com.projectjava.demosclient.entity.Password;
import com.projectjava.demosclient.entity.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailService implements UserDetailsService {
    @Autowired
    UserDao userDao;

    @Autowired
    PasswordDao passwordDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Usuario usuario = userDao.FindByEmail(username);
        Password password = passwordDao.findPasswordById(usuario.getId());
        //Logica donde autentifica usuario
        if(usuario == null){
        throw new UsernameNotFoundException("Usuario no encontrado");
        }
        return new User(username,password.getPassword(),new ArrayList<>());

    }
}

 */


