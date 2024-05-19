package com.projectjava.demosclient.entity;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "gastos")
public class Gastos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idgastos")
    private int idGastos;

    @Column(name = "categoria")
    private String categoria;

    @Column(name = "importe")
    private double importe;

    @Column(name = "comprobante")
    private String comprobante;

    @Column(name = "numero_comprobante")
    private String numeroComprobante;
    @Column(name = "fecha_pago")
    private Date fechaPago;

    @Column(name = "medio_pago", length = 20)
    private String medioPago;
    @Column(name = "observacion", length = 365)
    private String observacion;

    @ManyToOne
    @JoinColumn(name = "proveedores_id_proveedor", referencedColumnName = "id_proveedor")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Proveedor proveedor;

    @Column(name = "usuario_creacion" , length = 45)
    private String usuarioCreacion;

    @Column(name = "usuario_modificacion", length = 45)
    private String usuarioModificacion;


    // Constructor, getters y setters


    public Gastos() {
    }


    public Gastos(String categoria, double importe,
                  String comprobante, Date fechaPago, Proveedor proveedor) {
        this.categoria = categoria;
       this.importe = importe;
        this.comprobante = comprobante;
        this.fechaPago = fechaPago;

        this.proveedor = proveedor;
    }

    public String getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(String usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }

    public String getUsuarioModificacion() {
        return usuarioModificacion;
    }

    public void setUsuarioModificacion(String usuarioModificacion) {
        this.usuarioModificacion = usuarioModificacion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getNumeroComprobante() {
        return numeroComprobante;
    }

    public void setNumeroComprobante(String numeroComprobante) {
        this.numeroComprobante = numeroComprobante;
    }

    public int getIdGastos() {
        return idGastos;
    }

    public void setIdGastos(int idGastos) {
        this.idGastos = idGastos;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public String getMedioPago() {
        return medioPago;
    }

    public void setMedioPago(String medioPago) {
        this.medioPago = medioPago;
    }

    public String getComprobante() {
        return comprobante;
    }

    public void setComprobante(String comprobante) {
        this.comprobante = comprobante;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }


    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }
}
