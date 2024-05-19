package com.projectjava.demosclient.dto;

public class PaymentRequestDTO {

    private String nombreTitular;

    private Long numeroTarjeta;

    private String mesExpiracion;
    private String agnoExpiracion;

    private int cvc;

    public PaymentRequestDTO() {
    }

    public PaymentRequestDTO(String nombreTitular, Long numeroTarjeta, String mesExpiracion, String agnoExpiracion, int cvc) {
        this.nombreTitular = nombreTitular;
        this.numeroTarjeta = numeroTarjeta;
        this.mesExpiracion = mesExpiracion;
        this.agnoExpiracion = agnoExpiracion;
        this.cvc = cvc;
    }

    public String getNombreTitular() {
        return nombreTitular;
    }

    public void setNombreTitular(String nombreTitular) {
        this.nombreTitular = nombreTitular;
    }

    public Long getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(Long numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public String getMesExpiracion() {
        return mesExpiracion;
    }

    public void setMesExpiracion(String mesExpiracion) {
        this.mesExpiracion = mesExpiracion;
    }

    public String getAgnoExpiracion() {
        return agnoExpiracion;
    }

    public void setAgnoExpiracion(String agnoExpiracion) {
        this.agnoExpiracion = agnoExpiracion;
    }

    public int getCvc() {
        return cvc;
    }

    public void setCvc(int cvc) {
        this.cvc = cvc;
    }
}
