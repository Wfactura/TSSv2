/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mk.tss.beans;

import java.util.Date;

/**
 *
 * @author WF Consulting
 */
public class TotalBomba {
    private String nombre;
    private Date fechaI;
    private Date fechaF;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaI() {
        return fechaI;
    }

    public void setFechaI(Date fechaI) {
        this.fechaI = fechaI;
    }

    public Date getFechaF() {
        return fechaF;
    }

    public void setFechaF(Date fechaF) {
        this.fechaF = fechaF;
    }

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }
    private int contador;

    public TotalBomba(String nombre, Date fechaI, Date fechaF, int contador) {
        this.nombre = nombre;
        this.fechaI = fechaI;
        this.fechaF = fechaF;
        this.contador = contador;
    }

    public TotalBomba() {
    }
}
