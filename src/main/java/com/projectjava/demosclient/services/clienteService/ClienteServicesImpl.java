package com.projectjava.demosclient.services.clienteService;


import com.projectjava.demosclient.dao.ClienteDao;
import com.projectjava.demosclient.entity.Cliente;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteServicesImpl implements ClienteServices {
    private static final Logger logger = LoggerFactory.getLogger(ClienteServicesImpl.class);
@Autowired
ClienteDao clienteDao;

    @Override
    public Page<Cliente> findByDescripcionAndTipoFactura(String descripcion, String tipoFactura, int size, int page) {
        Pageable pageable = PageRequest.of(page, size);
        logger.info("findByDescripcionAndTipoFactura - Size: {}, Page: {}, Descripcion: {}, TipoFactura: {}", size, page, descripcion, tipoFactura);
        if (descripcion != null || tipoFactura != null) {
            Specification<Cliente> spec = Specification.where(null);

            if (descripcion != null) {
                spec = spec.and((root, query, criteriaBuilder) ->
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("descripcion")), "%" + descripcion.toLowerCase() + "%"));
            }

            if (tipoFactura != null) {
                spec = spec.and((root, query, criteriaBuilder) ->
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("tipoFactura")), "%" + tipoFactura.toLowerCase() + "%"));
            }
            return clienteDao.findAll(spec, pageable);
        } else {
            return clienteDao.findAll(pageable);
        }
    }

    @Override
    public Page<Cliente> findAll(int page, int size) {
        Pageable pageableBancos = PageRequest.of(page, size);
        return clienteDao.findAll(pageableBancos);
    }

    @Override
    @Transactional
    public Cliente save(Cliente cliente) {
      return  clienteDao.save(cliente);
    }

    @Override
    @Transactional
    public Optional<Cliente> findById(Long id) {
        return clienteDao.findById(id);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        clienteDao.deleteById(id);
    }



}
