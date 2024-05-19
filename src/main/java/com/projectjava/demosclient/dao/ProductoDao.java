package com.projectjava.demosclient.dao;

import com.projectjava.demosclient.dto.ProductosDTO;
import com.projectjava.demosclient.entity.Cliente;
import com.projectjava.demosclient.entity.Productos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface ProductoDao extends JpaRepository<Productos, Long> {

    Page<Productos> findAll(Pageable pageable);

    Page<Productos> findAll(Specification<Productos> spec, Pageable pageable);

    boolean existsByCodigo(@Param(value="codigo") String codigo);



}
