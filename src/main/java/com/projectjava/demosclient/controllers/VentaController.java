package com.projectjava.demosclient.controllers;


import com.projectjava.demosclient.entity.Productos;
import com.projectjava.demosclient.entity.Ventas;
import com.projectjava.demosclient.services.productoService.ProductoServices;
import com.projectjava.demosclient.services.ventaService.VentaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RestController
@RequestMapping("/api/v1/ventas")
public class VentaController {

    @Autowired
    VentaService ventaService;

    @Autowired
    ProductoServices productoServices;


    @GetMapping("/all")
    Page<Ventas> findAll(@RequestParam(name = "page", defaultValue = "0") int page,
                         @RequestParam(name = "size", defaultValue = "25") int size,
                         @RequestParam(required = false) Date fechaCreacion) {

        Page<Ventas> ventas;
        ventas = ventaService.findByFechaCreacion(fechaCreacion, page, size);
        return ventas;
    }



    @GetMapping("/listaProductos")
    public ResponseEntity<Map<Long, Productos>> listaProductos() {

        Map<Long, Productos> listaProductos = ventaService.listaProductos();

        return ResponseEntity.ok(listaProductos);

    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> BuscarIdVenta(@PathVariable(value = "id") Long id) {
        Optional<Ventas> OptionalVentas = ventaService.findById(id);
        return ResponseEntity.ok(OptionalVentas.get());
    }

    @PostMapping("/save")
    public ResponseEntity<?> guardarVenta(@RequestBody Ventas venta) {
        Ventas ventaGuardada = ventaService.saveVenta(venta);
        Productos producto = ventaGuardada.getProducto();
        int cantidadVendida = Integer.parseInt(venta.getCantidad());
        int cantidadActualizada = Integer.parseInt(producto.getCantidad()) - cantidadVendida;
        producto.setCantidad(String.valueOf(cantidadActualizada));
        productoServices.saveProductoVenta(producto);
        return ResponseEntity.ok().body("{\"response\": \"200\"}");
    }


    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editarVenta(@PathVariable(value = "id") Long idVentas,
                                         @RequestBody Ventas ventaActualizada) {
        Ventas ventaOriginal = ventaService.findById(idVentas)
                .orElseThrow(() -> new EntityNotFoundException("Venta no encontrada"));
        int cantidadOriginal = Integer.parseInt(ventaOriginal.getCantidad());
        int nuevaCantidad = Integer.parseInt(ventaActualizada.getCantidad());
        if (nuevaCantidad < 0) {
            throw new IllegalArgumentException("La cantidad actualizada no puede ser negativa.");
        }
        Productos producto = ventaOriginal.getProducto();
        int diferenciaCantidad = nuevaCantidad - cantidadOriginal;
        int cantidadDelProducto = Integer.parseInt(producto.getCantidad());
        if (nuevaCantidad < 0) {
            throw new IllegalArgumentException("La cantidad actualizada no puede ser negativa.");
        }
        if (diferenciaCantidad < 0) {
            int diferenciaCantidad2 = cantidadOriginal - nuevaCantidad;
            cantidadDelProducto += diferenciaCantidad2;
        } else if (diferenciaCantidad > 0) {
            cantidadDelProducto -= diferenciaCantidad; // Incrementamos la cantidad del producto
        }
        if (diferenciaCantidad != 0) {
            cantidadDelProducto = Math.max(cantidadDelProducto, 0);
            producto.setCantidad(String.valueOf(cantidadDelProducto));
            ventaActualizada.setProducto(producto);
            productoServices.saveProductoVenta(producto);
            Ventas ventaActualizadaGuardada = ventaService.saveVenta(ventaActualizada);
            return ResponseEntity.ok().body(Map.of("response", "200"));
        } else {
            return ResponseEntity.ok().body(Map.of("response", "No se realizaron cambios en la cantidad."));
        }
    }

        @DeleteMapping("/delete/{id}")
        public ResponseEntity<?> deleteVenta (@PathVariable(value = "id") Long id){
            ventaService.deleteById(id);
            return ResponseEntity.ok("{\"response\": \"200\"}");
        }
    }
