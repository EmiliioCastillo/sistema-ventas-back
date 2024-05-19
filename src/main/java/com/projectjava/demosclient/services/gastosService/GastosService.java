package com.projectjava.demosclient.services.gastosService;

import com.projectjava.demosclient.dto.GastosProveedorDTO;
import com.projectjava.demosclient.entity.Gastos;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

@Service
public interface GastosService {


    Page<Gastos> findByCategoriaAndFechaPago(String categoria, Date fechaPago, int page, int size);
    Set<String> findAllCategorias();
    ResponseEntity<String> save(Gastos gasto);
    ResponseEntity<String> edit(Gastos gasto);
    Page<GastosProveedorDTO> consultarGastoYProveedor(String nombre, int page, int size);

    Optional<Gastos> findById(Long id);

    void deleteById(Long id);
}
