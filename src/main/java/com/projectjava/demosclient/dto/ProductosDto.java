package com.projectjava.demosclient.dto;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor

public class ProductosDto {
    private Long id;
    private String codigo;
    private String descripcion;
    private String categoria;
    private String almacen;


    public ProductosDto(Long id, String codigo, String descripcion, String categoria, String almacen) {
        super();
        this.id = id;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.almacen = almacen;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setAlmacen(String almacen) {
        this.almacen = almacen;
    }
}
