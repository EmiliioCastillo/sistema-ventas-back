package com.projectjava.demosclient.dto;


import com.projectjava.demosclient.entity.Proveedor;
import lombok.NoArgsConstructor;

import java.util.Date;



public class ProductosDTO {
    private Long idProductos;


    private String codigo;


    private String descripcion;


    private String categoria;


    private String producto;


    private String precio;


    private String cantidad;


    private String estatus;

    private Date fechaEntrega;

    private Proveedor proveedor;

    public Long getIdProductos() {
        return idProductos;
    }

    public ProductosDTO(Long idProductos, String codigo,
                        String descripcion, String categoria,
                        String producto, String precio, String cantidad,
                        String estatus, Date fechaEntrega, Proveedor proveedor) {
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


    public ProductosDTO() {
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

    public void setProducto(String producto) {
        this.producto = producto;
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

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }
}

