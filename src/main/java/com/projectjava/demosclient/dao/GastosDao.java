package com.projectjava.demosclient.dao;

import com.projectjava.demosclient.entity.Cliente;
import com.projectjava.demosclient.entity.Gastos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import java.util.List;

public interface GastosDao extends JpaRepository<Gastos,Long> {
    Page<Gastos> findAll(Specification<Gastos> spec, Pageable pageable);
    Page<Gastos> findAll(Pageable pageable);

    @Procedure(name = "consultarGastoYProveedor")
    List<Object[]> consultarGastoYProveedor();
}


