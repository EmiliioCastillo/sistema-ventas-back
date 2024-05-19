package com.projectjava.demosclient.dao;

import com.projectjava.demosclient.entity.HistorialPago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import java.util.List;

public interface PagoDao extends JpaRepository<HistorialPago, Long> {

    @Procedure("obtenerFechaPago")
    List<Object[]> obtenerFechaPago();
}
