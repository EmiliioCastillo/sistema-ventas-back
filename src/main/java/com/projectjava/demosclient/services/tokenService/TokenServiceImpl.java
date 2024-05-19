package com.projectjava.demosclient.services.tokenService;

import com.projectjava.demosclient.dao.TokenDao;
import com.projectjava.demosclient.dao.UserDao;
import com.projectjava.demosclient.entity.Token;
import com.projectjava.demosclient.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class TokenServiceImpl implements TokenService{

    @Autowired
    TokenDao tokenDao;

    @Autowired
    UserDao userDao;

    @Override
    public void guardarToken(Usuario usuario, String token, Date fechaCreacion) {
        Token tokenEntity = new Token();
        tokenEntity.setUsuario(usuario);
        tokenEntity.setToken(token);
        tokenEntity.setFechaCreacion(fechaCreacion);
        tokenDao.save(tokenEntity);
    }




    @Override
    public boolean validarToken(String token) {
        Optional<Token> tokenEntity = tokenDao.findByToken(token);
        return tokenEntity.isPresent();
    }
}
