/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mk.tss.dao;

import com.mk.tss.entities.Operador;
import com.mk.tss.entities.Usuario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author WF Consulting
 */
@Local
public interface OperadorFacadeLocal {

    public abstract void create(Operador paramOperador);

    public abstract void edit(Operador paramOperador);

    public abstract void remove(Operador paramOperador);

    public abstract Operador find(Object paramObject);

    public abstract List<Operador> findAll();

    public abstract List<Operador> findRange(int[] paramArrayOfInt);

    public abstract int count();

    public List<Operador> buscarUsuario(Usuario c);

    public boolean updateOper(Operador oper);
    
    public Operador buuscarID(Integer id);
    
}
