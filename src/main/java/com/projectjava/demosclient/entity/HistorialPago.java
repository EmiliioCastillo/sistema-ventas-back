package com.projectjava.demosclient.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "historial_pago")
public class HistorialPago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idhistorial_pago")
    private Long idPago;

    @Column(name = "fecha_pago")
    private String fechaPago;

    @Column(name = "monto")
    private String monto;

    @Column(name = "estado",  length = 45)
    private String estado;

    @Column(name = "identificador_pago", length = 45)
    private String identificadorPago;


    public HistorialPago() {
    }

    public Long getIdPago() {
        return idPago;
    }

    public void setIdPago(Long idPago) {
        this.idPago = idPago;
    }


    public String getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getIdentificadorPago() {
        return identificadorPago;
    }

    public void setIdentificadorPago(String identificadorPago) {
        this.identificadorPago = identificadorPago;
    }
}
