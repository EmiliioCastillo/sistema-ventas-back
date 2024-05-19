package com.projectjava.demosclient.entity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ventas")
public class Ventas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idventas")
    private Long idVentas;

    @Column(name = "fecha_creacion")
    private Date fechaCreacion;

    @Column(name = "medio_pago")
    private String medioPago;

    @Column(name = "cantidad")
    private String cantidad;

    @Column(name = "precio")
    private String precio;

    @ManyToOne(fetch = FetchType.LAZY , optional = false)
    @JoinColumn(name = "productos_id_producto", referencedColumnName = "id_producto")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Productos producto;

    // Constructor, getters y setters



    public Long getIdVentas() {
        return idVentas;
    }

    public void setIdVentas(Long idVentas) {
        this.idVentas = idVentas;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getMedioPago() {
        return medioPago;
    }

    public void setMedioPago(String medioPago) {
        this.medioPago = medioPago;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public Productos getProducto() {
        return producto;
    }

    public void setProducto(Productos producto) {
        this.producto = producto;
    }
}
