package com.projectjava.demosclient.dto;

import jakarta.persistence.Column;

public class GastosProveedorDTO {


    private String nombreProveedor;
    private String medioPago;

    private double importe;



    public GastosProveedorDTO() {
    }

    public GastosProveedorDTO(String medioPago, String comprobante, double importe, String observacion) {
        this.medioPago = medioPago;

        this.importe = importe;

    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public String getMedioPago() {
        return medioPago;
    }

    public void setMedioPago(String medioPago) {
        this.medioPago = medioPago;
    }


    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }


}
