package com.projectjava.demosclient.dao;


import com.projectjava.demosclient.dto.ProveedorDTO;
import com.projectjava.demosclient.entity.Productos;
import com.projectjava.demosclient.entity.Proveedor;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProveedorDao extends JpaRepository<Proveedor, Long> {

    @Procedure(name = "devolverProveedorProducto")
    List<Object[]> devolverProveedorProducto();

    Page<Proveedor> findAll(Specification<Proveedor> spec, Pageable pageable);

    @Procedure(name = "buscarTodosProveedores")
    List<Object[]> buscarTodosProveedores();

    @Procedure(name = "insertarProveedores")
    void insertarProveedores(
            @Param("p_nombre") String nombre,
            @Param("p_direccion") String direccion,
            @Param("p_email") String email,
            @Param("p_telefono") String telefono,
            @Param("p_estatus") String estatus,
            @Param("p_numero_tributario") String numeroTributario
    );

    @Procedure(name = "actualizarProveedor")
    void actualizarProveedor(
            @Param("p_idProveedor") Long idProveedor,
            @Param("p_nombre") String nombre,
            @Param("p_direccion") String direccion,
            @Param("p_email") String email,
            @Param("p_telefono") String telefono,
            @Param("p_estatus") String estatus,
            @Param("p_numero_tributario") String numeroTributario
    );

    @Procedure(name = "buscarProveedorPorId")
    List<Object[]> buscarProveedorPorId(@Param("in_id_proveedor") Long idProveedor);
}