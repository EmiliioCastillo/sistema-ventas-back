package com.projectjava.demosclient.services.authService;

import com.projectjava.demosclient.dao.UserDao;
import com.projectjava.demosclient.dto.AuthResponse;
import com.projectjava.demosclient.dto.LoginDTO;
import com.projectjava.demosclient.dto.RegistroDTO;
import com.projectjava.demosclient.entity.Usuario;
import com.projectjava.demosclient.security.jwt.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService{

    @Autowired
    UserDao userDao;

    @Autowired
     JwtService jwtService;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Override
    public AuthResponse login(LoginDTO datos) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(datos.getEmail(), datos.getPassword()));
        UserDetails user = userDao.findByEmail(datos.getEmail()).orElseThrow();
        String token = jwtService.getToken(user);
        String rol = user.getAuthorities().stream().findFirst().orElseThrow().getAuthority();
        AuthResponse authResponse = AuthResponse.builder()
                .token(token)
                .rol(rol)
                .build();
        return authResponse;
    }
    @Override
    public Usuario obtenerUsuarioLogueado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return (Usuario) authentication.getPrincipal();
        } else {
            return null;
        }
    }
    @Override
    public AuthResponse registroUsuarios(RegistroDTO datos) {
        Optional<Usuario> userOptional = userDao.findByEmail(datos.getEmail());
        if (userOptional.isPresent()) {
            throw new RuntimeException("Ya existe un usuario con ese email");
        }

        Usuario user = Usuario.builder()
                .email(datos.getEmail())
                .password(passwordEncoder.encode(datos.getPassword()))
                .nombre(datos.getNombre())
                .apellido(datos.getApellido())
                .rol(datos.getRol())
                .build();

        userDao.save(user);
        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();

    }

    @Override
    public String recuperarPassword(String email) {
        Usuario user = userDao.findByEmail(email).orElseThrow(()-> new RuntimeException("No existe el email" + email));
        return null;
    }
}
