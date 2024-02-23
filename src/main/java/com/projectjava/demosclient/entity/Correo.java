package com.projectjava.demosclient.entity;
import jakarta.persistence.*;


@Entity
@Table(name = "correo")
public class Correo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_correo")
    private Long idCorreo;

    @Column(name = "correo")
    private String direccionCorreo;

    @ManyToOne
    @JoinColumn(name = "clientes_idclientes")
    private Cliente cliente;

    // Constructores, getters y setters

    public Correo() {
        // Constructor por defecto necesario para JPA
    }

    public Correo(String direccionCorreo, Cliente cliente) {
        this.direccionCorreo = direccionCorreo;
        this.cliente = cliente;
    }

    public Long getIdCorreo() {
        return idCorreo;
    }

    public void setIdCorreo(Long idCorreo) {
        this.idCorreo = idCorreo;
    }

    public String getDireccionCorreo() {
        return direccionCorreo;
    }

    public void setDireccionCorreo(String direccionCorreo) {
        this.direccionCorreo = direccionCorreo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }


}
