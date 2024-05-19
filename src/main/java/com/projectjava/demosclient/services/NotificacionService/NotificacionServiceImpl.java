package com.projectjava.demosclient.services.NotificacionService;

import com.projectjava.demosclient.dao.NotificacionDao;
import com.projectjava.demosclient.dao.PagoDao;
import com.projectjava.demosclient.entity.Notificacion;
import com.projectjava.demosclient.entity.Usuario;
import com.projectjava.demosclient.services.authService.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class NotificacionServiceImpl implements NotificacionService {

    @Autowired
    AuthService authService;

    @Autowired
    NotificacionDao notificacionDao;

    @Autowired
    PagoDao pagoDao;

    @Transactional(readOnly = false)
    @Override
    public ResponseEntity<String> sendMonthlyNotificationToLoggedInUser() throws ParseException {
        Usuario userLogueado = authService.obtenerUsuarioLogueado();

        if (userLogueado != null && userLogueado.isEnabled()) {
            List<Object[]> resultados = pagoDao.obtenerFechaPago();

            if (!resultados.isEmpty()) {
                String dateString = (String) resultados.get(0)[0]; // Obtener la fecha como cadena
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Formato de fecha esperado
                Date currentMonth = dateFormat.parse(dateString); // Convertir la cadena a Date

                // Calcular la fecha de pago del próximo mes
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(currentMonth);
                calendar.add(Calendar.MONTH, 1); // Agregar un mes
                Date nextPaymentDate = calendar.getTime();

                // Calcular la cantidad de días hasta la fecha de pago del próximo mes
                long daysUntilPayment = ChronoUnit.DAYS.between(LocalDate.now(),
                        nextPaymentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                long fecha = daysUntilPayment;
                if (daysUntilPayment > 2) {
                    // Enviar notificación con los días restantes
                	String message = "{\"response\": \"Le quedan " + daysUntilPayment + " días para realizar el pago.\", \"email\": \"" + userLogueado.getEmail() + "\", \"fecha\": \"" + fecha + "\"}";
                    return ResponseEntity.ok(message);
                } else {
                    // La notificación no existe para este mes, crea una nueva
                    Notificacion newNotification = new Notificacion(userLogueado, currentMonth);
                    notificacionDao.save(newNotification);
                    return sendNotificationToUser(userLogueado);
                }
            } else {
                // No se encontraron fechas de pago
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron fechas de pago");
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado o no habilitado");
    }

    @Override
    public ResponseEntity<String> sendNotificationToUser(Usuario user) {
        return ResponseEntity.ok("{\"response\": \"Ultimos días para realizar el pago\", \"email\": \"" + user.getEmail() + "\"}");
    }
}