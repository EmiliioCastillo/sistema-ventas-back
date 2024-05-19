package com.projectjava.demosclient.controllers;


import com.projectjava.demosclient.dto.AuthResponse;
import com.projectjava.demosclient.dto.RegistroDTO;
import com.projectjava.demosclient.entity.Rol;
import com.projectjava.demosclient.entity.Usuario;
import com.projectjava.demosclient.services.NotificacionService.NotificacionService;
import com.projectjava.demosclient.services.authService.AuthService;
import com.projectjava.demosclient.services.historialPagoService.HistorialPagoService;
import com.projectjava.demosclient.services.tokenService.TokenService;
import com.projectjava.demosclient.services.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "*", methods= {RequestMethod.GET})
@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    @Autowired
    UserService userService;
    @Autowired
    AuthService authServices;
    @Autowired
    NotificacionService notificacionService;

    @Autowired
    HistorialPagoService historialPagoService;

    @Autowired
    TokenService tokenService;
    @GetMapping("/all")
    public ResponseEntity<?> obtenerTodosUsuarios(@RequestParam(name = "limit", defaultValue = "0") int page,
                                                  @RequestParam(name = "offset", defaultValue = "25") int size){
        return ResponseEntity.ok().body(userService.findAll(page, size));
    }


    @GetMapping("/notificacion")
    public ResponseEntity<String> enviarMensajeNotificacionPagar() throws ParseException {
        return notificacionService.sendMonthlyNotificationToLoggedInUser();
    }


    @PostMapping("/save")
    public ResponseEntity<?> guardarUsuario(@RequestBody Usuario usuario){
        return userService.save(usuario);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable(value = "id") Long id) {
        userService.deleteById(id);
        return ResponseEntity.ok("{\"response\": \"200\"}");
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> BuscarId(@PathVariable(value = "id") Long id){
        Optional<Usuario> Optionalusuario = userService.findById(id);
        return ResponseEntity.ok(Optionalusuario.get());
    }

    @GetMapping("/rol/all")
    public ResponseEntity<?> traerRolesUsuarios(){
        return ResponseEntity.ok().body(userService.findAllRoles());
    }
    @PostMapping("/registro")
    public ResponseEntity<AuthResponse> registroUsuario(@RequestBody RegistroDTO datos) {
        try {
            return ResponseEntity.ok(authServices.registroUsuarios(datos));
        } catch (RuntimeException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/enviarEmail")
    public ResponseEntity<?> enviarEmail(@RequestParam(name = "email") String email){
        return ResponseEntity.ok(userService.enviarEmail(email));
    }


    @GetMapping("/historialPago")
    public ResponseEntity<?> traerHistoriales(){
        return ResponseEntity.ok().body(historialPagoService.findAll());
    }


    @GetMapping("/listRoles")
    public ResponseEntity<?> traerRoles(){
        Map<Long, Rol> listaRol = userService.listaRolesString();

        return ResponseEntity.ok(listaRol);
    }

}
