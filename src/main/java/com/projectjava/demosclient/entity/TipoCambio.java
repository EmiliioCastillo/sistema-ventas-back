package com.projectjava.demosclient.entity;


import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tipo_cambio")
public class TipoCambio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idtipo_cambio")
    private Long idTipoCambio;

    @Column(name = "tipo_cambio")
    private String tipoCambio;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoCambio")
    List<PagoVenta> pagoVentasList;


    public TipoCambio( String tipo_cambio) {

        this.tipoCambio = tipo_cambio;
        /*this.clienteSet = new HashSet<>();*/
    }

    public TipoCambio() {

    }

    public TipoCambio(Long id, String tipo_cambio) {
        this.idTipoCambio = id;
        this.tipoCambio = tipo_cambio;
    }

    public Long getIdTipoCambio() {
        return idTipoCambio;
    }

    public void setIdTipoCambio(Long idTipoCambio) {
        this.idTipoCambio = idTipoCambio;
    }

    public String getTipoCambio() {
        return tipoCambio;
    }

    public void setTipoCambio(String tipoCambio) {
        this.tipoCambio = tipoCambio;
    }
}
