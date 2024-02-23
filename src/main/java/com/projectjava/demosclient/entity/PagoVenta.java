package com.projectjava.demosclient.entity;
import jakarta.persistence.*;



@Entity
@Table(name = "pagos_venta")
public class PagoVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_pagos_venta")
    private Long idPagosVenta;


    @ManyToOne
    @JoinColumn(name = "clientes_idclientes")
    private Cliente cliente;


    @ManyToOne
    @JoinColumn(name = "tipo_cambio_idtipo_cambio")
    private TipoCambio tipoCambio;


    @ManyToOne
    @JoinColumn(name = "productos_id_producto")
    private Productos producto;

    // Constructores, getters y setters

    public PagoVenta() {
        // Constructor por defecto necesario para JPA
    }

    public PagoVenta(Cliente cliente, TipoCambio tipoCambio, Productos producto, TipoCambio tipoCambioRelacionado) {
        this.cliente = cliente;
        this.tipoCambio = tipoCambio;
        this.producto = producto;

    }

    // Métodos getters y setters...


    public Long getIdPagosVenta() {
        return idPagosVenta;
    }

    public void setIdPagosVenta(Long idPagosVenta) {
        this.idPagosVenta = idPagosVenta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public TipoCambio getTipoCambio() {
        return tipoCambio;
    }

    public void setTipoCambio(TipoCambio tipoCambio) {
        this.tipoCambio = tipoCambio;
    }

    public Productos getProducto() {
        return producto;
    }

    public void setProducto(Productos producto) {
        this.producto = producto;
    }
}
