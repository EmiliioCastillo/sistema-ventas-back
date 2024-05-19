package com.projectjava.demosclient.controllers;

import com.projectjava.demosclient.entity.Cliente;
import com.projectjava.demosclient.services.clienteService.ClienteServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;


@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RequestMapping("/api/v1/clientes")
@RestController
public class ClienteController {

    @Autowired
    ClienteServices clienteServices;

    @GetMapping("/all")
    public Page<Cliente> buscarPorCliente(@RequestParam(name = "limit", defaultValue = "0") int page,
                                          @RequestParam(name = "offset", defaultValue = "10") int size,
                                          @RequestParam(required = false) String nombre) {
        Page<Cliente> clientes;
        clientes = clienteServices.findByNombre(nombre, page, size); // Se mantiene el orden page, size
        return clientes;
    }



    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editar(@PathVariable(value = "id") Long id,
                                    @RequestBody Cliente clienteBody) {

        Optional<Cliente> clienteDb = clienteServices.findById(id);
        //Si no hay nada, retorna error 401
        if(clienteDb.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Cliente cliente1  = clienteDb.get();
        cliente1.setNombre(clienteBody.getNombre());
        cliente1.setApellido(clienteBody.getApellido());
        cliente1.setComentarios(clienteBody.getComentarios());
        cliente1.setSaldoAbonado(clienteBody.getSaldoAbonado());
        cliente1.setSaldoDeudor(clienteBody.getSaldoDeudor());
        return clienteServices.save(clienteBody);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> mostrarCliente(@PathVariable(value = "id") Long id) {
        Optional<Cliente> cliente = clienteServices.findById(id);
        return ResponseEntity.ok(cliente.get());
    }

    @GetMapping("/listaClientes")
    public Set<String> mostrarTodo() {
        return clienteServices.findAllNombreClientes();
    }

    @PostMapping("/save")
    public ResponseEntity<?> guardarCliente(@RequestBody Cliente cliente) {
        if (cliente.getSaldoAbonado() != null && !cliente.getSaldoAbonado().isEmpty()) {
            double saldoDeudor = cliente.getSaldoDeudor() != null && !cliente.getSaldoDeudor().isEmpty()
                    ? Double.parseDouble(cliente.getSaldoDeudor()) : 0.0;
            double saldoAbonado = Double.parseDouble(cliente.getSaldoAbonado());
            double saldoNeto = saldoDeudor - saldoAbonado;
            cliente.setSaldoNeto(Double.toString(saldoNeto));
        }
        return clienteServices.save(cliente);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> eliminarCliente(@PathVariable(value = "id") Long id) {
        clienteServices.deleteById(id);
        return ResponseEntity.ok("{\"response\": \"200\"}");
    }

}
