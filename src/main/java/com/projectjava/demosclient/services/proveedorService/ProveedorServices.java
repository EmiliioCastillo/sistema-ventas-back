package com.projectjava.demosclient.services.proveedorService;


import com.projectjava.demosclient.entity.Proveedor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public interface ProveedorServices {

    List<Proveedor> findAll();
    Proveedor save(Proveedor proveedor);
    void deleteById(Long id);
    Optional<Proveedor> findById(Long id);

}
