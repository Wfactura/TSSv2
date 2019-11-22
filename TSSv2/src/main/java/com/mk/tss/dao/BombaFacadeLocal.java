/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mk.tss.dao;

import com.mk.tss.entities.Bomba;
import com.mk.tss.entities.Operador;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author WF Consulting
 */
@Local
public interface BombaFacadeLocal {

    public abstract void create(Bomba paramBomba);

    public abstract void edit(Bomba paramBomba);

    public abstract void remove(Bomba paramBomba);

    public abstract Bomba find(Object paramObject);

    public abstract List<Bomba> findAll();

    public abstract List<Bomba> findRange(int[] paramArrayOfInt);

    public abstract List<Bomba> listarBomba(List<Operador> paramUsuario);

    public abstract int count();

    public Bomba buscarBomba(Integer bomba, Operador op);

    public boolean updateOper(Bomba bom);
    
}
