package com.projectjava.demosclient.controllers;

import com.projectjava.demosclient.dao.ProveedorDao;
import com.projectjava.demosclient.dto.GastosProveedorDTO;
import com.projectjava.demosclient.entity.Gastos;
import com.projectjava.demosclient.entity.Proveedor;
import com.projectjava.demosclient.services.authService.AuthService;
import com.projectjava.demosclient.services.gastosService.GastosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RestController
@RequestMapping("/api/v1/gastos")
public class GastoController {

    @Autowired
    GastosService gastoService;

    @Autowired
    AuthService authService;

    @Autowired
    ProveedorDao proveedorDao;

    @GetMapping("/all")
    public Page<Gastos> buscarTodos(@RequestParam(name = "limit", defaultValue = "0") int page,
                                        @RequestParam(name = "offset", defaultValue = "10") int size,
                                        @RequestParam(required = false) String categoria,
                                        @RequestParam(required = false) Date fechaPago
    ){

        Page<Gastos> gastos;
        gastos = gastoService.findByCategoriaAndFechaPago(categoria,fechaPago, page, size);
        return gastos;
    }

    @GetMapping("/consultarGastoYProveedor")
    public ResponseEntity<?> consultarGastoYProveedor(@RequestParam(name = "limit", defaultValue = "0") int page,
                                                             @RequestParam(name = "offset", defaultValue = "3") int size,
                                                             @RequestParam(required = false) String nombreProveedor){
        Page<GastosProveedorDTO> gastosProveedorDTOS;

        gastosProveedorDTOS = gastoService.consultarGastoYProveedor(nombreProveedor, page, size);

        return ResponseEntity.ok(gastosProveedorDTOS);
    }

    @GetMapping("/listaCategorias")
    public Set<String> mostrarTodo() {
        return gastoService.findAllCategorias();
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> mirarGasto(@PathVariable(value = "id") Long id){
        Optional<Gastos> gastosOptional = gastoService.findById(id);
        return ResponseEntity.ok(gastosOptional.get());
    }

    @PostMapping(value = "/save")
    public ResponseEntity<?> guardarGasto(@RequestBody Gastos gasto) {
        if (gasto.getProveedor() == null) {
            return gastoService.save(gasto);
        }
        Optional<Proveedor> proveedorOptional = proveedorDao.
                findById(gasto.getProveedor().getIdProveedor());
        if (!proveedorOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        gasto.setProveedor(proveedorOptional.get());
        return gastoService.save(gasto);
    }
    @PutMapping(value = "/edit/{id}" )
    public ResponseEntity<?> editarGasto(@PathVariable(value = "id") Long id,
                                         @RequestBody Gastos gastoBody) {
        Optional<Proveedor> proveedorOptional = proveedorDao.findById(gastoBody.getProveedor().getIdProveedor());

        if(!proveedorOptional.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }

        Optional<Gastos> gastosOptional = gastoService.findById(id);
        if(!gastosOptional.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }
        String usuarioLogueado = authService.obtenerUsuarioLogueado().getEmail();
        gastoBody.setUsuarioModificacion(usuarioLogueado);
        gastoBody.setProveedor(proveedorOptional.get());
        gastoBody.setIdGastos(gastosOptional.get().getIdGastos());

        return  gastoService.edit(gastoBody);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteGasto(@PathVariable(value = "id") Long id) {
        gastoService.deleteById(id);
        return ResponseEntity.ok("{\"response\": \"200\"}");
    }




}
