package com.projectjava.demosclient.services.authService;

import com.projectjava.demosclient.dto.AuthResponse;
import com.projectjava.demosclient.dto.LoginDTO;
import com.projectjava.demosclient.dto.RegistroDTO;
import com.projectjava.demosclient.entity.Usuario;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {

     AuthResponse login(LoginDTO datos);
     Usuario obtenerUsuarioLogueado();

     String recuperarPassword(String email);
    AuthResponse registroUsuarios(RegistroDTO datos);
}
