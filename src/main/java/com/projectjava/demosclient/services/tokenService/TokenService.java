package com.projectjava.demosclient.services.tokenService;

import com.projectjava.demosclient.entity.Usuario;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public interface TokenService {

    void guardarToken(Usuario usuario, String token, Date fechaCreacion);
    boolean validarToken(String token);
}
