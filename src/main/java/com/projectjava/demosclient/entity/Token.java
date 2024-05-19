package com.projectjava.demosclient.entity;

import jakarta.persistence.*;

import java.util.Date;


@Entity
@Table(name = "token")
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idtoken")
    private Long idToken;



    @Column(nullable = false, unique = true)
    private String token;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuarios_id_usuarios", nullable = false)
    private Usuario usuario;
    // Getters y setters
    // ...

    @Column(name = "fecha_creacion")
    private Date fechaCreacion;



    public Long getIdToken() {
        return idToken;
    }

    public void setIdToken(Long idToken) {
        this.idToken = idToken;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}