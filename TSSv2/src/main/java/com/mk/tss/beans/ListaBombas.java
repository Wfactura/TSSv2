/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mk.tss.beans;

import com.mk.tss.entities.Bitacora;
import com.mk.tss.entities.Bomba;

/**
 *
 * @author WF Consulting
 */
public class ListaBombas {

    private Bomba bomba;
    private Bitacora bitacora;

    public Bomba getBomba() {
        return this.bomba;
    }

    public void setBomba(Bomba bomba) {
        this.bomba = bomba;
    }

    public Bitacora getBitacora() {
        return this.bitacora;
    }

    public void setBitacora(Bitacora bitacora) {
        this.bitacora = bitacora;
    }

    public ListaBombas(Bomba bomba, Bitacora bitacora) {
        this.bomba = bomba;
        this.bitacora = bitacora;
    }

    public ListaBombas() {
    }
}
