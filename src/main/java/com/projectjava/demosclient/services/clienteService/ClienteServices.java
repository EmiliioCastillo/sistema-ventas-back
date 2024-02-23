package com.projectjava.demosclient.services.clienteService;

import com.projectjava.demosclient.entity.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public interface ClienteServices {


     Page<Cliente> findByDescripcionAndTipoFactura
             (String descripcion, String tipoFactura, int size, int page);
    Page<Cliente> findAll(int page, int size);
    Cliente save(Cliente cliente);

    Optional<Cliente> findById(Long id);

    void deleteById(Long id);

}
