package com.projectjava.demosclient.services.clienteService;


import com.projectjava.demosclient.dao.ClienteDao;
import com.projectjava.demosclient.entity.Cliente;

import com.projectjava.demosclient.entity.Productos;
import com.projectjava.demosclient.entity.Proveedor;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.hibernate.HibernateException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

@Service
public class ClienteServicesImpl implements ClienteServices {

@Autowired
ClienteDao clienteDao;

    @Override
    public Page<Cliente> findByNombre(String nombre, int page, int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);

            Specification<Cliente> specification = (root, query, builder) -> {
                query.distinct(true);
                List<Predicate> predicates = new ArrayList<>();

                if (nombre != null) {
                    predicates.add(builder.equal(root.get("nombre"), nombre));
                }
                // Aplica las condiciones al query usando query.where(...)
                query.where(builder.and(predicates.toArray(new Predicate[0])));

                return null; // No necesitas devolver nada aquí
            };

            return clienteDao.findAll(specification, pageable);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error al consultar cliente: " + e.getMessage());
            throw new RuntimeException("Error al consultar cliente: " + e.getMessage(), e);
        }
    }


    @Override
    public Set<String> findAllNombreClientes() {
        List<Cliente> listaClientes = clienteDao.findAll(Sort.by(Sort.Direction.ASC, "nombre"));
        Set<String> listaClientesNombres = new HashSet<>();
        for (Cliente cliente : listaClientes) {
            listaClientesNombres.add(cliente.getNombre());
        }
        return listaClientesNombres;
    }
    @Override
    public ResponseEntity<String> save(Cliente cliente) {
        try{
            clienteDao.save(cliente);
            return ResponseEntity.ok("{\"response\": \"200\"}");

        } catch (HibernateException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar registro");

        } catch (PersistenceException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar registro");

        }

    }

    @Override
    public Optional<Cliente> findById(Long id) {
        return clienteDao.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        clienteDao.deleteById(id);
    }



}
