package com.projectjava.demosclient.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/*
@Entity
@Table(name = "proveedores")
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idproveedores")
    private Long id;
    private Long numtransferencia;
    private String nombre;

/*
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "inventario", joinColumns = @JoinColumn(name = "proveedores_idproveedores", referencedColumnName = "idproveedores"),
            inverseJoinColumns = @JoinColumn(name = "productos_idproductos", referencedColumnName = "idproductos"))
     Set<Productos> listProductos;



    public Proveedor(){

    }
    public Proveedor( Long numTransferencia, String nombre){
        this.numtransferencia = numTransferencia;
        this.nombre = nombre;


    }

    public Proveedor(Long id) {
        this.id = id;
    }


   /* public void addProductos(Productos producto){
        listProductos.add(producto);
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumtransferencia() {
        return numtransferencia;
    }

    public void setNumtransferencia(Long numtransferencia) {
        this.numtransferencia = numtransferencia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


//Este metodo puede ser agregar inventario por inventario en el caso de que el proveedor ofrezca
    //Multiples servicios

}
*/

    @Entity
    @Table(name = "proveedores")
    public class Proveedor {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id_proveedor")
        private Long idProveedor;

        @Column(name = "numtransferencia")
        private String numTransferencia;

        @Column(name = "nombre")
        private String nombre;


        @OneToMany(cascade = CascadeType.ALL, mappedBy = "proveedor")
        List<Inventario> inventarioList;


        // Constructores, getters y setters

        public Proveedor() {
            // Constructor por defecto necesario para JPA
        }

        public Proveedor(String numTransferencia, String nombre) {
            this.numTransferencia = numTransferencia;
            this.nombre = nombre;
        }


        public Long getIdProveedor() {
            return idProveedor;
        }

        public void setIdProveedor(Long idProveedor) {
            this.idProveedor = idProveedor;
        }

        public String getNumTransferencia() {
            return numTransferencia;
        }

        public void setNumTransferencia(String numTransferencia) {
            this.numTransferencia = numTransferencia;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public void agnadirInventario( Inventario inventario){
            inventarioList.add(inventario);
        }
        @Override
        public String toString() {
            return "Proveedor [idProveedor=" + idProveedor + ", numTransferencia=" + numTransferencia + ", nombre=" + nombre + "]";
        }
    }
