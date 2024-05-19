package com.projectjava.demosclient.services.historialPagoService;

import com.projectjava.demosclient.entity.HistorialPago;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface HistorialPagoService {

    List<HistorialPago> findAll();
}
