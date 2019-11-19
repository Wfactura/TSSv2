/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mk.tss.dao;

import com.mk.tss.entities.Usuario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author WF Consulting
 */
@Local
public interface UsuarioFacadeLocal {

    public abstract void create(Usuario paramUsuario);

    public abstract void edit(Usuario paramUsuario);

    public abstract void remove(Usuario paramUsuario);

    public abstract Usuario find(Object paramObject);

    public abstract List<Usuario> findAll();

    public abstract List<Usuario> findRange(int[] paramArrayOfInt);

    public abstract Usuario encontrarUsuarioPorNombreYPassword(Usuario paramUsuario);

    public Usuario buscarConteo(Integer c);

    public abstract int count();

    public boolean updateOper(Usuario usu);
    
}
