package com.projectjava.demosclient.controllers;


import com.projectjava.demosclient.dao.MovimientosDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RestController
@RequestMapping("/api/v1/movimientos")
public class MovimientoController {


    @Autowired
    MovimientosDao movimientosDao;

    @GetMapping("/all")
    public ResponseEntity<?> obtenerMovimientos(){
        return ResponseEntity.ok().body(movimientosDao.findAll());
    }
 }
