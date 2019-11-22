/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mk.tss.dao;

import com.mk.tss.entities.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author WF Consulting
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {

    @PersistenceContext(unitName = "com.mk_TSSv2_war_2PU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return this.em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    @Override
    public Usuario encontrarUsuarioPorNombreYPassword(Usuario usuario) {
        Usuario usuarioElegido = null;
        List<Usuario> lUsuario = null;
        try {
            Query query = this.em.createQuery("from Usuario u where u.nombreUsuario =:nombre and u.passworUsuario=:password");
            query.setParameter("nombre", usuario.getNombreUsuario());
            query.setParameter("password", usuario.getPassworUsuario());
            lUsuario = query.getResultList();
            if (lUsuario.size() > 0) {
                usuarioElegido = (Usuario) lUsuario.get(0);
            } else {
                usuarioElegido = null;
            }
        } catch (Exception e) {
            usuarioElegido = null;
            e.printStackTrace();
        }
        return usuarioElegido;
    }
    
    
    @Override
    public Usuario buscarConteo(Integer c) {
        System.out.println("DatosRemoto buscando usuario "+c+"");
        Usuario dato = null;
        try {
            String queryString = "from Usuario u where u.idUsuario=:idUsu";
            Query query = em.createQuery(queryString);
            query.setParameter("idUsu", c);
            List<Usuario> datos = query.getResultList();
            if(datos.size()>0){
                dato= datos.get(0);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dato;
    }
    
    @Override
    public boolean updateOper(Usuario usu){
        boolean editado=false;
        try {
            em.merge(usu);
            editado=true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return editado;
        
    }
    
}
