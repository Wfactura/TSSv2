/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mk.tss.dao;

import com.mk.tss.entities.Bitacora;
import com.mk.tss.entities.Bomba;
import com.mk.tss.entities.Usuario;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author WF Consulting
 */
@Local
public interface BitacoraFacadeLocal {

    public abstract void create(Bitacora paramBitacora);

    public abstract void edit(Bitacora paramBitacora);

    public abstract void remove(Bitacora paramBitacora);

    public abstract Bitacora find(Object paramObject);

    public abstract List<Bitacora> findAll();

    public abstract List<Bitacora> findRange(int[] paramArrayOfInt);

    public abstract List<Bitacora> listarBitacora(Bomba paramBomba);

    public abstract List<Bitacora> listarBitacoraFecha(Bomba paramBomba, Date paramDate1, Date paramDate2);

    public abstract Bitacora listarBitacoraBomba(Usuario paramUsuario, Bomba paramBomba);

    public void insertar(Bitacora bitacora);

    public Bitacora buscar(Bomba bomba);

    public boolean buscarNumEnv(Bomba bomba, Integer numEnv);

    public abstract int count();
    
}
