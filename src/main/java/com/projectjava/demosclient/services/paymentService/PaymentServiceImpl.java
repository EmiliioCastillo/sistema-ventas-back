package com.projectjava.demosclient.services.paymentService;

import com.projectjava.demosclient.dao.PagoDao;
import com.projectjava.demosclient.entity.HistorialPago;
import jakarta.persistence.PersistenceException;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService{

    @Autowired
    PagoDao pagoDao;
    @Override
    public ResponseEntity<String> guardarPago(HistorialPago historialPago) {
        try{
            pagoDao.save(historialPago);
            return ResponseEntity.ok("{\"response\": \"200\"}");

        } catch (HibernateException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar registro");

        } catch (PersistenceException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar registro");

        }
    }
}
