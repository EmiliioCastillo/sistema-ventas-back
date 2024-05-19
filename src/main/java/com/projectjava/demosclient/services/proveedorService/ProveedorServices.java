package com.projectjava.demosclient.services.proveedorService;


import com.projectjava.demosclient.dto.ProveedorDTO;
import com.projectjava.demosclient.entity.Proveedor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public interface ProveedorServices {

    Page<ProveedorDTO> findAll(String nombre, String estatus, int page, int size);
    ResponseEntity<String> save(ProveedorDTO proveedor);
    void deleteById(Long id);
    Optional<Proveedor> findById(Long id);

    ResponseEntity<String> actualizarProveedor(ProveedorDTO proveedor);
    Optional<ProveedorDTO> buscarProveedoresPorId(Long id);
    Set<ProveedorDTO> listarProveedoresConProductos(String nombre, String producto, String estatus, int page, int size);

    Set<String> findAllNombreProveedores();


}
