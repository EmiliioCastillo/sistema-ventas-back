package com.projectjava.demosclient.entity;

import jakarta.persistence.*;



@Entity
@Table(name = "inventario")
public class Inventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_inventario")
    private Long idInventario;

    @ManyToOne
    @JoinColumn(name = "productos_id_producto")
    private Productos producto;

    @ManyToOne
    @JoinColumn(name = "proveedores_id_proveedor")
    private Proveedor proveedor;

    @Column(name = "costos")
    private String costos;

    @Column(name = "impuestos")
    private String impuestos;

    // Constructores, getters y setters

    public Inventario() {
        // Constructor por defecto necesario para JPA
    }

    public Inventario(Productos producto, Proveedor proveedor, String costos, String impuestos) {
        this.producto = producto;
        this.proveedor = proveedor;
        this.costos = costos;
        this.impuestos = impuestos;
    }


    public Long getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(Long idInventario) {
        this.idInventario = idInventario;
    }

    public Productos getProducto() {
        return producto;
    }

    public void setProducto(Productos producto) {
        this.producto = producto;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public String getCostos() {
        return costos;
    }

    public void setCostos(String costos) {
        this.costos = costos;
    }

    public String getImpuestos() {
        return impuestos;
    }

    public void setImpuestos(String impuestos) {
        this.impuestos = impuestos;
    }

    @Override
    public String toString() {
        return "Inventario [id=" + idInventario + ", producto=" + producto + ", proveedor=" + proveedor + ", costos=" + costos + ", impuestos=" + impuestos + "]";
    }
}
