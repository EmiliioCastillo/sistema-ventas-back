package com.projectjava.demosclient.dao;

import com.projectjava.demosclient.entity.Inventario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InventarioDao extends JpaRepository<Inventario, Long> {
}
