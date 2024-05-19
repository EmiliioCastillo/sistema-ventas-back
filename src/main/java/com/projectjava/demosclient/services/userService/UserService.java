package com.projectjava.demosclient.services.userService;

import com.projectjava.demosclient.entity.Rol;
import com.projectjava.demosclient.entity.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public interface UserService {

 Page<Usuario> findAll(int page, int size);

 ResponseEntity<String> save(Usuario usuario);

    Optional<Usuario> findById(Long id);

    public ResponseEntity<String> enviarEmail(String email);
    Map<Long, Rol> listaRolesString();
    List<Rol> findAllRoles();
    void deleteById(Long id);

    ResponseEntity<String> validarToken(String token);
     ResponseEntity<String> actualizarContraseña(String email, String nuevaContraseña);
}



