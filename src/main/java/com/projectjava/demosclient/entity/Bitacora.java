package com.projectjava.demosclient.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "bitacora")
public class Bitacora {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idbitacora")
    private int idbitacora;

    @Column(name = "fecha_creado_por")
    private Date fechaCreadoPor;

    @Column(name = "fecha_modificado_por")
    private Date fechaModificadoPor;

    @Column(name = "usuario_creacion")
    private String usuarioCreacion;

    @Column(name = "usuario_modificador")
    private String usuarioModificador;

    @ManyToOne
    @JoinColumn(name = "usuarios_id", nullable = false)
    private Usuario usuario;

    // Constructor, getters, and setters

    public Bitacora() {
    }

    // Constructor con todos los campos
    public Bitacora(int idbitacora, Date fechaCreadoPor, Date fechaModificadoPor,
                    String usuarioCreacion, String usuarioModificador, Usuario usuario) {
        this.idbitacora = idbitacora;
        this.fechaCreadoPor = fechaCreadoPor;
        this.fechaModificadoPor = fechaModificadoPor;
        this.usuarioCreacion = usuarioCreacion;
        this.usuarioModificador = usuarioModificador;
        this.usuario = usuario;
    }

    // Getters y setters

    // ...

}
