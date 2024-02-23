package com.projectjava.demosclient.controllers;



import com.projectjava.demosclient.entity.Cliente;

import com.projectjava.demosclient.enums.API;
import com.projectjava.demosclient.excel.UserExcelImport;
import com.projectjava.demosclient.services.clienteService.ClienteServices;
import com.projectjava.demosclient.services.clienteService.ClienteServicesImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

import static com.mysql.cj.conf.PropertyKey.logger;
//COnfiguramos cors con el crossOrigin

@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/v1/clientes")
@RestController
public class ClienteController {

    @Autowired
    ClienteServices clienteServices;
    private static final Logger logger = LoggerFactory.getLogger(ClienteServicesImpl.class);

    @GetMapping("/all")
    public Page<Cliente>  buscarPorBanco(@RequestParam(name = "limit", defaultValue = "0") int page,
                                        @RequestParam(name = "offset", defaultValue = "3") int size,
                                        @RequestParam(required = false) String descripcion,
                                        @RequestParam(required = false) String tipoFactura
    ){

        Page<Cliente> clientes;
        clientes = clienteServices.findByDescripcionAndTipoFactura(descripcion,tipoFactura, page, size);
        return clientes;
    }



    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editar(@PathVariable(value = "id") Long id, @RequestBody Cliente clienteBody) {

        Optional<Cliente> clienteDb = clienteServices.findById(id);
        //Si no hay nada, retorna error 401
        if(clienteDb.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Cliente cliente1  = clienteDb.get();
        cliente1.setNombre(clienteBody.getNombre());
        cliente1.setApellido(clienteBody.getApellido());
        cliente1.setEmail(clienteBody.getEmail());
        cliente1.setNumTel(clienteBody.getNumTel());
        cliente1.setDescripcion(clienteBody.getDescripcion());
        cliente1.setTipoFactura(clienteBody.getTipoFactura());

        return ResponseEntity.ok().body(clienteServices.save(cliente1));
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> mostrarCliente(@PathVariable(value = "id") Long id) {
        Optional<Cliente> cliente = clienteServices.findById(id);
        return ResponseEntity.ok(cliente.get());
    }


    @PostMapping("/save")
    public ResponseEntity<?> guardarCliente( @RequestBody Cliente cliente) {
        Cliente clienteGuardado = clienteServices.save(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteGuardado);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> eliminarCliente(@PathVariable(value = "id") Long id) {
        clienteServices.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
