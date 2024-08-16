package com.projectjava.demosclient.services.userService;


import com.projectjava.demosclient.dao.RolDao;
import com.projectjava.demosclient.dao.TokenDao;
import com.projectjava.demosclient.dao.UserDao;
import com.projectjava.demosclient.entity.Rol;
import com.projectjava.demosclient.entity.Usuario;
import com.projectjava.demosclient.services.NotificacionService.NotificacionService;
import com.projectjava.demosclient.services.authService.AuthService;
import com.projectjava.demosclient.services.tokenService.TokenService;
import com.projectjava.demosclient.util.EmailSender;
import jakarta.mail.MessagingException;
import jakarta.persistence.PersistenceException;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    RolDao rolDao;
    @Autowired
    TokenService tokenService;

    @Autowired
    TokenDao tokenDao;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthService authService;
    @Autowired
    EmailSender emailSender;

    @Autowired
    NotificacionService notificacionService;

    @Autowired
    AuthenticationManager authenticationManager;
    @Override
    public Page<Usuario> findAll(int page, int size) {
        Pageable listPageable = PageRequest.of(page, size);
        return userDao.findAll(listPageable);
    }

    public List<Rol> findAllRoles() {
        return rolDao.findAll();
    }

    @Override
    public Map<Long, Rol> listaRolesString() {
        try {
            List<Rol> listRoles = rolDao.findAll(Sort.by(Sort.Direction.ASC, "nombrerol"));
            Map<Long, Rol> listRolesMap = new HashMap<>();

            for (Rol rol : listRoles) {
                listRolesMap.put(rol.getId(), rol);
            }
            return listRolesMap;

        } catch (HibernateException e) {
            e.printStackTrace();

        } catch (PersistenceException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public ResponseEntity<String> save(Usuario usuario) {
        try {
            userDao.save(usuario);
            return ResponseEntity.ok("{\"response\": \"200\"}");

        } catch (HibernateException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar registro");

        } catch (PersistenceException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar registro");

        }
    }

    @Override
    public ResponseEntity<String> enviarEmail(String email) {
        Usuario user = userDao.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found" + email));
        try {
            // Generar la fecha actual
            Date fechaActual = new Date();
            String token = UUID.randomUUID().toString();
            tokenService.guardarToken(user, token, fechaActual);
            emailSender.setPasswordEmail(email, token);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok("{\"response\": \"200\"}");
    }



    @Override
    public ResponseEntity<String> actualizarContraseña(String email, String nuevaContraseña) {
        try {
            Usuario usuario = userDao.findByEmail(email).orElse(null);
            if (usuario != null) {
                usuario.setPassword(passwordEncoder.encode(nuevaContraseña));
                userDao.save(usuario);
                return ResponseEntity.ok("{\"response\": \"200\"}");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar la contraseña: " + e.getMessage());
        }
    }


    @Transactional(readOnly = false)
    @Override
    public ResponseEntity<String> validarToken(String token) {
        try {
            List<Object[]> resultados = tokenDao.buscarTokenConEmail(token);
            if (!resultados.isEmpty()) {
                String tokenDB = (String) resultados.get(0)[0]; // Extraer el token de la primera fila
                String emailDB = (String) resultados.get(0)[1];
                Date fechaCreacion = (Date) resultados.get(0)[2];
                // Validar el token
                if (token.equals(tokenDB)) {
                    long tiempoTranscurrido = System.currentTimeMillis() - fechaCreacion.getTime();
                    // Definir el período de expiración en milisegundos (60 segundos)
                    long periodoExpiracion = 60 * 1000; // 60 segundos

                    // Verificar si el token ha expirado
                    if (tiempoTranscurrido <= periodoExpiracion) {
                        // Realizar la validación y retornar la respuesta exitosa
                        
                        return ResponseEntity.ok("{\"response\": \"200\", \"email\": \"" + emailDB + "\"}");
                    } else {
                        // El token ha expirado
                        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Tiempo expirado");
                    }
                }
            }
            // Si el token no es válido o ha expirado, devolver un estado no autorizado
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no válido");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al validar el token: " + e.getMessage());
        }
    }



    @Override
    public Optional<Usuario> findById(Long id) {
        return userDao.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        userDao.deleteById(id);
    }
}