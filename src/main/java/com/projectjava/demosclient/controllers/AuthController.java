package com.projectjava.demosclient.controllers;

import com.projectjava.demosclient.dao.UserDao;
import com.projectjava.demosclient.dto.AuthResponse;
import com.projectjava.demosclient.dto.LoginDTO;
import com.projectjava.demosclient.dto.RegistroDTO;
import com.projectjava.demosclient.services.authService.AuthService;
import com.projectjava.demosclient.services.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "*", methods= {RequestMethod.GET})
@RestController
@RequestMapping("api/v1/auth")
@Controller
public class AuthController {

    @Autowired
    AuthService authServices;

    @Autowired
    UserDao userDao;

    @Autowired
    UserService userService;


@PostMapping("/login")
public ResponseEntity<?> loginUsuario(@RequestBody LoginDTO datos) {
    try {
        return ResponseEntity.ok(authServices.login(datos));
    } catch (RuntimeException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"response\": \"not-valid\"}");
    }
}
@GetMapping("/all")
public ResponseEntity<?> findAll(){
    return ResponseEntity.ok().body(userDao.findAll());
}

    @GetMapping("/validar")
    public ResponseEntity<?> validarToken(@RequestParam("token") String token){
        return userService.validarToken(token);
    }


    @PostMapping("/actualizarPassword")
    public ResponseEntity<?> actualizarContrasegna(@RequestBody Map<String, String> body) {
        String nuevaContraseña = body.get("nuevaContraseña");
        String email = body.get("email");
        return ResponseEntity.ok(userService.actualizarContraseña(email, nuevaContraseña));
    }

@GetMapping("/perfil")
public ResponseEntity<?> traerPerfil(){
    return ResponseEntity.ok().body(authServices.obtenerUsuarioLogueado());
}



}

