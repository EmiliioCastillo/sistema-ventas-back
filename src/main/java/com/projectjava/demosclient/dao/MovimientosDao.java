package com.projectjava.demosclient.dao;

import com.projectjava.demosclient.entity.Movimientos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovimientosDao extends JpaRepository<Movimientos,Long> {
}
