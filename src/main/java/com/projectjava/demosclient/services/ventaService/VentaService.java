package com.projectjava.demosclient.services.ventaService;

import com.projectjava.demosclient.entity.Productos;
import com.projectjava.demosclient.entity.Ventas;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public interface VentaService {

    Page<Ventas> findByFechaCreacion( Date fechaCreacion, int page, int size);

    Optional<Ventas> findById(Long id);


    Map<Long, Productos> listaProductos();
    Page<Ventas> findAll(int page, int size);
    ResponseEntity<String> save(Ventas venta);
    Ventas saveVenta(Ventas venta);
    void deleteById(Long id);
}
