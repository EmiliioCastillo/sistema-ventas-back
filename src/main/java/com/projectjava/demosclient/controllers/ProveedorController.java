package com.projectjava.demosclient.controllers;


import com.projectjava.demosclient.entity.Proveedor;
import com.projectjava.demosclient.services.proveedorService.ProveedorServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
/*
@RequestMapping("")
@Controller
public class ProveedorController {
    @Autowired
    ProveedorServices proveedorServices;



    @GetMapping("/listarproveedores")
    public String listarProveedores(Model model){

        List<Proveedor> listProveedores = proveedorServices.listSupplier();

        model.addAttribute("proveedor", listProveedores);

        return "/listarproveedores";
    }

    @RequestMapping("/crearproveedor")
    public String crear(Map<String, Object> model){
        Proveedor proveedor = new Proveedor();
        model.put("proveedor", proveedor);
        return "/crearproveedor";
    }
    @PostMapping("/crearproveedor")
    public String crearProducto(Proveedor proveedor){
       proveedorServices.save(proveedor);

        return "redirect:/listarproveedores";
    }

}
*/


@RestController
@RequestMapping("/api/v1/proveedores")
public class ProveedorController{
    private static final Logger log = LoggerFactory.getLogger(ProveedorController.class);

    @Autowired
    ProveedorServices proveedorServices;

    @GetMapping("/all")
    public ResponseEntity<?> findAllSupplier(){
        try {
            Iterable<Proveedor> listSupplier = proveedorServices.findAll();
            // Log para verificar si se está ejecutando y la lista se recupera correctamente
            log.info("ProveedorController - findAllSupplier: List size = {}", ((Collection<?>) listSupplier).size());
            return ResponseEntity.ok().body(listSupplier);
        } catch (Exception e) {
            // Log para imprimir cualquier excepción que pueda ocurrir
            log.error("ProveedorController - findAllSupplier: Error occurred", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



    @GetMapping("/edit/{id}")
    public ResponseEntity<?> editSupplier(@PathVariable(value = "id") Long id, @RequestBody Proveedor supplierBody) {

        Optional<Proveedor> supplierDb = proveedorServices.findById(id);

        if(supplierDb.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        //Seteamos y rellenamos los datos del producto, el @RequestBody del parametro inicial
        //Permite que podamos deserializar y rellenar el cuerpo del alumno
        Proveedor supplier  = supplierDb.get();
        supplier.setIdProveedor(supplierBody.getIdProveedor());
        supplier.setNombre(supplierBody.getNombre());
        supplier.setNumTransferencia(supplierBody.getNumTransferencia());

        return ResponseEntity.ok().body(proveedorServices.save(supplier));
    }


    @GetMapping("/find/{id}")
    public ResponseEntity<?> find(@PathVariable(value = "id") Long id){
        Optional<Proveedor> products = proveedorServices.findById(id);
        return ResponseEntity.ok(products.get());
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveSupplier(Proveedor proveedor ){
        Proveedor supplierEntity = proveedorServices.save(proveedor);
        return ResponseEntity.status(HttpStatus.CREATED).body(supplierEntity);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSupplier(@PathVariable(value = "id") Long id) {

        proveedorServices.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}

