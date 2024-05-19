package com.projectjava.demosclient.dao;

import com.projectjava.demosclient.entity.Cliente;
import com.projectjava.demosclient.entity.Productos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClienteDao extends JpaRepository<Cliente, Long>, JpaSpecificationExecutor<Cliente> {


    Page<Cliente> findAll(Pageable pageable);

    Page<Cliente> findAll(Specification<Cliente> spec, Pageable pageable);


}
