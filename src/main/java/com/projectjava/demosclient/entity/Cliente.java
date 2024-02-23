package com.projectjava.demosclient.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "clientes")
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idclientes")
    private Long idClientes;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "numtel")
    private String numTel;

    @Column(name = "tipofactura")
    private String tipoFactura;

    @Column(name = "fecha")
    //Temporal indica en que formato se va a guardar la fecha
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "email", nullable = false)
    private String email;
    @Serial
    private static final long serialVersionUID = 1L;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
    private List<Marca> marcaList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
    List<Correo> correosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
    List<PagoVenta> pagoVentasList;
    public Cliente(){

    }

    public Cliente(String nombre, String apellido, String email, String numtel, String tipofactura, String descripcion) {

        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.numTel = numtel;
        this.tipoFactura = tipofactura;
        this.descripcion = descripcion;



    }

    public Cliente(Long id, String nombre, String apellido, String email, String numtel, String tipofactura, String descripcion) {
        this.idClientes = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.numTel = numtel;
        this.tipoFactura = tipofactura;
        this.descripcion = descripcion;
    }



    @PrePersist
private void prePersist(){
    fecha = new Date();
}

    public Long getIdClientes() {
        return idClientes;
    }

    public void setIdClientes(Long idClientes) {
        this.idClientes = idClientes;
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

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public String getTipoFactura() {
        return tipoFactura;
    }

    public void setTipoFactura(String tipoFactura) {
        this.tipoFactura = tipoFactura;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //El orderBY lo que hace es ordenar de manera descendente todas las fechas
    @OrderBy(value = "fecha DESC")
    public Date getFecha() {
        return fecha;
    }

     /*public void agregarBanco(Banco banco){
       bancoSet.add(banco);
    }
    public void agregarTipoCambio(TipoCambio tipoCambio){
        tipoCambioSet.add(tipoCambio);
    }
*/
}