package com.projectjava.demosclient.services.clienteService;

import com.projectjava.demosclient.entity.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
public interface ClienteServices {


     Page<Cliente> findByNombre(String nombre, int page, int size);
    Set<String> findAllNombreClientes();
    ResponseEntity<String> save(Cliente cliente);

    Optional<Cliente> findById(Long id);

    void deleteById(Long id);

}
