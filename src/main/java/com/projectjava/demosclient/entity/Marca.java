package com.projectjava.demosclient.entity;


import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;



@Entity
@Table(name = "marca")
public class Marca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_marca")
    private int idMarca;

    @Column(name = "marca_tarjeta")
    private String marcaTarjeta;

    @ManyToOne
    @JoinColumn(name = "clientes_idclientes")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "banco_idbanco")
    private Banco banco;

    // Constructores, getters y setters

    public Marca() {
        // Constructor por defecto necesario para JPA
    }

    public Marca(String marcaTarjeta, Cliente cliente, Banco banco) {
        this.marcaTarjeta = marcaTarjeta;
        this.cliente = cliente;
        this.banco = banco;
    }

    public int getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }

    public String getMarcaTarjeta() {
        return marcaTarjeta;
    }

    public void setMarcaTarjeta(String marcaTarjeta) {
        this.marcaTarjeta = marcaTarjeta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    @Override
    public String toString() {
        return "Marca [idMarca=" + idMarca + ", marcaTarjeta=" + marcaTarjeta + ", cliente=" + cliente + ", banco=" + banco + "]";
    }
}
