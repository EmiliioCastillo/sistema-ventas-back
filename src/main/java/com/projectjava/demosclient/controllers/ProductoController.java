package com.projectjava.demosclient.controllers;


import com.projectjava.demosclient.dao.ProductoDao;
import com.projectjava.demosclient.dao.ProveedorDao;
import com.projectjava.demosclient.entity.Productos;
import com.projectjava.demosclient.entity.Proveedor;
import com.projectjava.demosclient.services.productoService.ProductoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.Map;
import java.util.Optional;



@CrossOrigin(origins = "*", methods={RequestMethod.GET})
@RestController
@RequestMapping("/api/v1/productos")
public class ProductoController {

    @Autowired
    ProductoServices productoServices;

    @Autowired
    ProductoDao productoDao;

    @Autowired
    ProveedorDao proveedorDao;


    @GetMapping("/all")
    public Page<Productos>  buscarPorProductos(@RequestParam(name = "limit", defaultValue = "0") int page,
                                         @RequestParam(name = "offset", defaultValue = "25") int size,
                                         @RequestParam(required = false) String categoria,
                                         @RequestParam(required = false) String codigo,
                                         @RequestParam(required = false) String descripcion,
                                               @RequestParam(required = false) String nombre,
                                               @RequestParam(required = false) Date fechaEntrega
    ){

        Page<Productos> productos;
        productos = productoServices.findByCategoriaAndCodigoAndDescripcionAndNombre(categoria,codigo, descripcion,fechaEntrega,nombre, page, size);
        return productos;
    }





    @PutMapping(value = "/edit/{id}" )
    public ResponseEntity<?> editProducts(@PathVariable(value = "id") Long id,
                                          @RequestBody Productos productosBody) {
        Optional<Proveedor> proveedorOptional = proveedorDao.findById(productosBody.getProveedor().getIdProveedor());

        if(!proveedorOptional.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }

        Optional<Productos> productoOptional = productoServices.findById(id);
        if(!productoOptional.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }

        productosBody.setProveedor(proveedorOptional.get());
        productosBody.setIdProductos(productoOptional.get().getIdProductos());


        return  productoServices.save(productosBody);

    }



    @GetMapping("/find/{id}")
    public ResponseEntity<?> seeProducts(@PathVariable(value = "id") Long id){
        Optional<Productos> products = productoServices.findById(id);
        return ResponseEntity.ok(products.get());
    }

    @GetMapping("/listaProveedores")
    public ResponseEntity<Map<Long, Proveedor>> listaProveedores() {

        Map<Long, Proveedor> listaProveedores = productoServices.listaProveedores();

        return ResponseEntity.ok(listaProveedores);

    }
    @PostMapping(value = "/save")
    public ResponseEntity<?> saveProducts( @RequestBody Productos products){

        Optional<Proveedor> proveedorOptional = proveedorDao.findById(products.getProveedor().getIdProveedor());

        if(!proveedorOptional.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }

        products.setProveedor(proveedorOptional.get());
        return productoServices.save(products);



    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProducts(@PathVariable(value = "id") Long id) {
        productoServices.deleteById(id);
        return ResponseEntity.ok("{\"response\": \"200\"}");
    }


    @PostMapping(value = "/upload")
    public ResponseEntity<?> importarExcel(@RequestParam("file") MultipartFile file) {
       return productoServices.saveProductsToExcel(file);

    }

}

