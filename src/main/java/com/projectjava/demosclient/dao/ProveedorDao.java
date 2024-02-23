package com.projectjava.demosclient.dao;


import com.projectjava.demosclient.entity.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProveedorDao extends JpaRepository<Proveedor, Long> {
}
