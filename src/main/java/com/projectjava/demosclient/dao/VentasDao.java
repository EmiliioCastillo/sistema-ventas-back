package com.projectjava.demosclient.dao;

import com.projectjava.demosclient.entity.Productos;
import com.projectjava.demosclient.entity.Ventas;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface VentasDao extends JpaRepository<Ventas, Long> {

    Page<Ventas> findAll(Pageable pageable);

    boolean existsByIdVentas(@Param(value = "idventas") Long idVentas);

    Page<Ventas> findAll(Specification<Ventas> spec, Pageable pageable);
}
