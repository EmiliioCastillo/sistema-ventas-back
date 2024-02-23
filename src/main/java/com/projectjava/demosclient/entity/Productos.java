package com.projectjava.demosclient.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "productos")
public class Productos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Long idProductos;

    @Column(name = "codigo")
    private String codigo;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "categoria")
    private String categoria;

    @Column(name = "almacen")
    private String almacen;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "producto")
    List<Inventario> inventarioList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "producto")
    List<PagoVenta> pagoVentasList;


    public Productos(){

    }
    public Productos(Long id, String codigo, String descripcion, String categoria, String almacen ){
        this.idProductos = id;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.almacen = almacen;

    }

    public Long getIdProductos() {
        return idProductos;
    }

    public void setIdProductos(Long idProductos) {
        this.idProductos = idProductos;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getAlmacen() {
        return almacen;
    }



   /* public void agregarProveedor(Proveedor proveedor){
        proveedorSet.add(proveedor);
    }

    */

    public void setAlmacen(String almacen) {
        this.almacen = almacen;
    }


    @Override
    public String toString() {
        return "Productos{" +
                "id=" + idProductos +
                ", codigo=" + codigo +
                ", descripcion='" + descripcion.toString() + '\'' +
                ", categoria='" + categoria.toString() + '\'' +
                ", almacen='" + almacen.toString() + '\'' +
                '}';
    }
}
