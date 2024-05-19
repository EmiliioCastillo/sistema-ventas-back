package com.projectjava.demosclient.dto;

import java.math.BigDecimal;

public class PagoDTO {
    private String IdPago;
    private String titulo;
    private String descripcion;
    private BigDecimal precio;
    private String categoria;

    public PagoDTO(String idPago, String titulo, String descripcion, BigDecimal precio, String categoria) {
        IdPago = idPago;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.precio = precio;
        this.categoria = categoria;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getIdPago() {
        return IdPago;
    }

    public void setIdPago(String idPago) {
        IdPago = idPago;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }
}
