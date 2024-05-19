package com.projectjava.demosclient.entity;


import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


import java.io.Serializable;
import java.util.*;



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

    @Column(name = "producto")
    private String producto;

    @Column(name = "precio")
    private String precio;


    @Column(name = "cantidad")
    private String cantidad;


    @Column(name = "estatus")
    private String estatus;

    @Column(name = "fechaEntrega")
    private Date fechaEntrega;


    @ManyToOne(fetch = FetchType.LAZY , optional = false)
    @JoinColumn(name = "proveedores_id_proveedor")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Proveedor proveedor;

    public Productos(){

    }

    public Productos(Long idProductos, String codigo, String descripcion, String categoria, String producto, String precio, String cantidad, String estatus, Date fechaEntrega, Proveedor proveedor) {
        this.idProductos = idProductos;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.producto = producto;
        this.precio = precio;
        this.cantidad = cantidad;
        this.estatus = estatus;
        this.fechaEntrega = fechaEntrega;
        this.proveedor = proveedor;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    @ManyToOne
    public Proveedor getProveedor() {
        return proveedor;
    }


    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
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

    public String getProducto() {
        return producto;
    }



   /* public void agregarProveedor(Proveedor proveedor){
        proveedorSet.add(proveedor);
    }

    */

    public void setProducto(String producto) {
        this.producto = producto;
    }


    @Override
    public String toString() {
        return "Productos{" +
                "id=" + idProductos +
                ", codigo=" + codigo +
                ", descripcion='" + descripcion.toString() + '\'' +
                ", categoria='" + categoria.toString() + '\'' +
                ", producto='" + producto.toString() + '\'' +
                '}';
    }
}
