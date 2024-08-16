package com.projectjava.demosclient;


import com.projectjava.demosclient.dao.*;
import com.projectjava.demosclient.entity.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class ProductosTest {
    @Autowired
    private TestEntityManager tem;

    @Autowired
    ProductoDao productoDao;

    @Autowired
    ProveedorDao proveedorDao;

    @Autowired
    ClienteDao clienteDao;




    @Test
    public void listarProductos() {
        List<Productos> productosList = productoDao.findAll();

        productosList.forEach(System.out::println);
    }

/*
    @Test
    public void añadirCliente() {
        Cliente cliente = new Cliente(19L,"Emilio", "Carrasco", "example@gmail.com", "22233122"
                , "FACTURA A", "CUCHILLO");
        clienteDao.save(cliente);
    }
    */
    @Test
    public void testObtenerProveedor() {
        Productos producto = new Productos();
        Proveedor proveedor = producto.getProveedor();
        assertNotNull(proveedor, "El proveedor no debería ser nulo");
        assertNull(proveedor, "Si es nulo");
       
        System.out.println("Nombre del proveedor: " + proveedor.getNombre());
      
    }



}