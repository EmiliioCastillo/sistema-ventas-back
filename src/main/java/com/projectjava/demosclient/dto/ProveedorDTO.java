package com.projectjava.demosclient.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.projectjava.demosclient.entity.Productos;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

public class ProveedorDTO {

    private Long idProveedor;

    private String nombre;

    private String direccion;

    private String telefono;
    private String email;
    private String numeroTributario;


    private String estatus;

    List<ProductosDTO> productosList;

    public ProveedorDTO(Long idProveedor, String nombre, String email, String telefono, String direccion, String numeroTributario, String estatus, List<ProductosDTO> productosList) {
        this.idProveedor = idProveedor;
        this.nombre = nombre;
        this.estatus = estatus;
        this.productosList = new ArrayList<>(); // Esto crea una nueva lista vacía sin importar si se proporciona una lista en el constructor o no
    }

    public ProveedorDTO() {
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

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public List<ProductosDTO> getProductosList() {
        return productosList;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumeroTributario() {
        return numeroTributario;
    }

    public void setNumeroTributario(String numeroTributario) {
        this.numeroTributario = numeroTributario;
    }

    public void setProductosList(List<ProductosDTO> productosList) {
        this.productosList = productosList;
    }
}
