package com.projectjava.demosclient.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "movimientos")
public class Movimientos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_movimiento")
    private Long idMovimientos;

    @Column(name = "fecha_movimiento")
    private Date fechaMovimiento;

    @Column(name = "usuario_creacion")
    private String usuarioCreacion;


    @Column(name = "usuario_modificacion")
    private String usuarioModificador;


    @Column(name = "observacion")
    private String observacion;


    @ManyToOne(fetch = FetchType.LAZY , optional = false)
    @JoinColumn(name = "proveedores_id_proveedor")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Proveedor proveedor;


    @ManyToOne(fetch = FetchType.LAZY , optional = false)
    @JoinColumn(name = "gastos_idgastos")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Gastos gastos;


    public Long getIdMovimientos() {
        return idMovimientos;
    }

    public void setIdMovimientos(Long idMovimientos) {
        this.idMovimientos = idMovimientos;
    }

    public Date getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(Date fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }

    public String getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(String usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }

    public String getUsuarioModificador() {
        return usuarioModificador;
    }

    public void setUsuarioModificador(String usuarioModificador) {
        this.usuarioModificador = usuarioModificador;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Gastos getGastos() {
        return gastos;
    }

    public void setGastos(Gastos gastos) {
        this.gastos = gastos;
    }
}
