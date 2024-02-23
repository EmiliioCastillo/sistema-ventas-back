package com.projectjava.demosclient.controllers;


import com.projectjava.demosclient.dao.InventarioDao;

import com.projectjava.demosclient.entity.Inventario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/v1/inventario")
public class ProveedoresProductosController {

    @Autowired
    InventarioDao proveedoresProductosDao;


    @GetMapping("/all")
    public ResponseEntity<?> listarTodo() {
        List<Inventario> proveedoresProductosList = proveedoresProductosDao.findAll();
        return ResponseEntity.ok().body(proveedoresProductosList);
    }
}
