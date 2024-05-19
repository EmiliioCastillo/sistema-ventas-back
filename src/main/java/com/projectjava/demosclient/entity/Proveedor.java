package com.projectjava.demosclient.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

    @Entity
    @Table(name = "proveedores")
    public class Proveedor {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id_proveedor")
        private Long idProveedor;
        @Column(name = "nombre")
        private String nombre;
        @Column(name = "email")
        private String email;
        @Column(name = "telefono")
        private String telefono;
        @Column(name = "direccion")
        private String direccion;
        @Column(name = "numeroTributario")
        private String numeroTributario;
        @Column(name = "estatus")
        private String estatus;

        @OneToMany(mappedBy = "proveedor", cascade = CascadeType.ALL)
        @JsonBackReference
        List<Productos> productosList;


        // Constructores, getters y setters

        public Proveedor() {
            // Constructor por defecto necesario para JPA
        }


        public Proveedor( String nombre, String email, String telefono, String direccion, String numeroTributario, String estatus) {

            this.nombre = nombre;
            this.email = email;
            this.telefono = telefono;
            this.direccion = direccion;
            this.numeroTributario = numeroTributario;
            this.estatus = estatus;
        }

        public Long getIdProveedor() {
            return idProveedor;
        }

        public void setIdProveedor(Long idProveedor) {
            this.idProveedor = idProveedor;
        }


        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public void agnadirProductos( Productos producto){
            productosList.add(producto);
        }

        public List<Productos> getProductosList() {
            return productosList;
        }

        public void setProductosList(List<Productos> productosList) {
            this.productosList = productosList;
        }
    }
