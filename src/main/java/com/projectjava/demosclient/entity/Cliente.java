package com.projectjava.demosclient.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "clientes")
public class Cliente{

    @Id
    @Column(name ="idclientes")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;

    @Column(name ="nombre", length = 20)
    private String nombre;

    @Column(name = "apellido", length = 20)
    private String apellido;


    @Column(name = "comentarios", length = 365)
    private String comentarios;


    @Column(name = "saldo_abonado", length = 30)
    private String saldoAbonado;

    @Column(name = "saldo_deudor", length = 30)
    private String saldoDeudor;

    @Column(name = "saldo_neto", length = 45)
    private String saldoNeto;
    public Cliente() {
    }

    public Cliente(Long idCliente, String nombre, String apellido, String comentarios, String saldoAbonado, String saldoDeudor) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.comentarios = comentarios;
        this.saldoAbonado = saldoAbonado;
        this.saldoDeudor = saldoDeudor;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public String getSaldoNeto() {
        return saldoNeto;
    }

    public void setSaldoNeto(String saldoNeto) {
        this.saldoNeto = saldoNeto;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public String getSaldoAbonado() {
        return saldoAbonado;
    }

    public void setSaldoAbonado(String saldoAbonado) {
        this.saldoAbonado = saldoAbonado;
    }

    public String getSaldoDeudor() {
        return saldoDeudor;
    }

    public void setSaldoDeudor(String saldoDeudor) {
        this.saldoDeudor = saldoDeudor;
    }
}