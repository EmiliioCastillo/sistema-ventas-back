package com.projectjava.demosclient.services.NotificacionService;

import com.projectjava.demosclient.entity.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
public interface NotificacionService {
     ResponseEntity<String> sendMonthlyNotificationToLoggedInUser() throws ParseException;
    ResponseEntity<String> sendNotificationToUser(Usuario user);
}
