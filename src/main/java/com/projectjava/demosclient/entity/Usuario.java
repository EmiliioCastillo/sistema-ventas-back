package com.projectjava.demosclient.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "email", length = 365)
    private String email;

    @Column(name = "password", length = 300)
    private String password;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @ManyToOne
    @JoinColumn(name = "id_rol")
    private Rol rol;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    List<Bitacora> bitacoraList;
    // Constructores, getters y setters

    public Usuario() {
        // Constructor por defecto necesario para JPA
    }

    public Usuario(String email, String password, String nombre, String apellido, Rol rol) {
        this.email = email;
        this.password = password;
        this.nombre = nombre;
        this.apellido = apellido;
        this.rol = rol;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
    /*
    public void añadirRol(Rol rol){
        listRoles.add(rol);
    }
     public void eliminarRol(Rol rol){
        listRoles.remove(rol);
    }

    public Set<Rol> getListRoles() {
        return listRoles;
    }
     */
    @Override
    public String toString() {
        return "Usuario [id=" + id + ", email=" + email + ", password=" + password + ", nombre=" + nombre + ", apellido=" + apellido + ", rol=" + rol + "]";
    }
}
