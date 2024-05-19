package com.projectjava.demosclient.services.paymentService;

import com.projectjava.demosclient.entity.HistorialPago;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface PaymentService {

    ResponseEntity<String> guardarPago(HistorialPago historialPago);
}
