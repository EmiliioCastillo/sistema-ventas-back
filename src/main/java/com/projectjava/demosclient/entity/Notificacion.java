package com.projectjava.demosclient.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "notificacion")
public class Notificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_notificacion")
    private Long idNotificacion;

    @Column(name = "fecha_notificacion")
    private Date fechaNotificacion;


    @OneToOne
   private Usuario usuario;

    public Notificacion(Long idNotificacion, Date fechaNotificacion, Usuario usuario) {
        this.idNotificacion = idNotificacion;
        this.fechaNotificacion = fechaNotificacion;
        this.usuario = usuario;
    }

    public Notificacion(Usuario usuario, Date fechaNotificacion) {
        this.usuario = usuario;
        this.fechaNotificacion = fechaNotificacion;
    }

    public Notificacion() {
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Long getIdNotificacion() {
        return idNotificacion;
    }

    public void setIdNotificacion(Long idNotificacion) {
        this.idNotificacion = idNotificacion;
    }

    public Date getFechaNotificacion() {
        return fechaNotificacion;
    }

    public void setFechaNotificacion(Date fechaNotificacion) {
        this.fechaNotificacion = fechaNotificacion;
    }


}
