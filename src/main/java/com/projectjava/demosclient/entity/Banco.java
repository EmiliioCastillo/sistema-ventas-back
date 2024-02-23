package com.projectjava.demosclient.entity;


import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "banco")
public class Banco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_banco")
    private int idBanco;
    @Column(name = "nombre_banco" , length = 45)
    private String nombreBanco;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "banco")
    List<Marca> marcaList;

    // Constructores, getters y setters

    public Banco() {
        // Constructor por defecto necesario para JPA
    }

    public Banco(int idBanco, String nombreBanco) {
        this.idBanco = idBanco;
        this.nombreBanco = nombreBanco;
    }

    public int getIdBanco() {
        return idBanco;
    }

    public void setIdBanco(int idBanco) {
        this.idBanco = idBanco;
    }

    public String getNombreBanco() {
        return nombreBanco;
    }

    public void setNombreBanco(String nombreBanco) {
        this.nombreBanco = nombreBanco;
    }


    /*public void addCliente(Cliente cliente){
        this.clienteSet.add(cliente);
    }

     */
}
