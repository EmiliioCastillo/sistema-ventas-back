package com.projectjava.demosclient.controllers;


import com.projectjava.demosclient.dto.ProveedorDTO;
import com.projectjava.demosclient.services.proveedorService.ProveedorServices;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RestController
@RequestMapping("/api/v1/proveedores")
public class ProveedorController{
    private static final Logger log = LoggerFactory.getLogger(ProveedorController.class);

    @Autowired
    ProveedorServices proveedorServices;

    @GetMapping("/all")
    public ResponseEntity<?> buscarTodosProveedores(@RequestParam(name = "limit", defaultValue = "0") int page,
                                                    @RequestParam(name = "offset", defaultValue = "25") int size,
                                                    @RequestParam(required = false) String nombre,
                                                    @RequestParam(required = false) String estatus){
        Page<ProveedorDTO> proveedores;
        proveedores = proveedorServices.findAll(nombre,estatus,  page, size);
        return ResponseEntity.ok(proveedores);
    }




    @GetMapping("/proveedorProducto")
    public ResponseEntity<?> buscarProveedorConProducto(@RequestParam(name = "limit", defaultValue = "0") int page,
                                                        @RequestParam(name = "offset", defaultValue = "25") int size,
                                                        @RequestParam(required = false) String nombre,
                                                        @RequestParam(required = false) String producto,
                                                        @RequestParam(required = false) String estatus){
        return ResponseEntity.ok(proveedorServices.listarProveedoresConProductos(nombre, producto,estatus, page, size));
    }

    @GetMapping("/traerNombreProveedores")
    public ResponseEntity<?> nombreProveedores(){
        return ResponseEntity.ok(proveedorServices.findAllNombreProveedores());
    }



    @PutMapping("edit/{id}")
    public ResponseEntity<?> actualizarProveedor(@PathVariable Long id,
                                                 @Valid @RequestBody ProveedorDTO proveedor){
        Optional<ProveedorDTO> optionalProveedor = proveedorServices.buscarProveedoresPorId(id);

        if(!optionalProveedor.isPresent()){
            return ResponseEntity.notFound().build(); // Cambiado a notFound() en lugar de unprocessableEntity()
        }

        ProveedorDTO proveedorExistente = optionalProveedor.get();
        proveedorExistente.setIdProveedor(id);
        proveedorExistente.setNombre(proveedor.getNombre());
        proveedorExistente.setDireccion(proveedor.getDireccion());
        proveedorExistente.setEmail(proveedor.getEmail());
        proveedorExistente.setTelefono(proveedor.getTelefono());
        proveedorExistente.setEstatus(proveedor.getEstatus());
        proveedorExistente.setNumeroTributario(proveedor.getNumeroTributario());

        ResponseEntity<String> respuesta = proveedorServices.actualizarProveedor(proveedorExistente);

        return ResponseEntity.ok(respuesta.getBody()); // Devuelve el cuerpo de la respuesta de proveedorServices.save()
    }



    @GetMapping("/find/{id}")
    public ResponseEntity<?> BuscarId(@PathVariable(value = "id") Long id){
        Optional<ProveedorDTO> OptionalProveedores = proveedorServices.buscarProveedoresPorId(id);
        return ResponseEntity.ok(OptionalProveedores.get());
    }

    @PostMapping("/save")
    public ResponseEntity<?> guardarProveedor( @RequestBody ProveedorDTO proveedor ){
       return proveedorServices.save(proveedor);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSupplier(@PathVariable(value = "id") Long id) {

        proveedorServices.deleteById(id);

        return ResponseEntity.ok("{\"response\": \"200\"}");
    }

}

