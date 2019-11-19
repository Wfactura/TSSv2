/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mk.tss.dao;

import com.mk.tss.entities.Operador;
import com.mk.tss.entities.Usuario;
import java.util.ArrayList;
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
public class OperadorFacade extends AbstractFacade<Operador> implements OperadorFacadeLocal {

    @PersistenceContext(unitName = "com.mk_TSSv2_war_2PU")
    private EntityManager em;
  
  protected EntityManager getEntityManager()
  {
    return this.em;
  }
  
  public OperadorFacade()
  {
    super(Operador.class);
  }
  
  
  @Override
  public List<Operador> buscarUsuario(Usuario c) {
      
        List<Operador> dato = new ArrayList<Operador>();
        try {
            System.out.println("Usuario recibido "+ c.getIdUsuario());
            String queryString = "from Operador u where u.idUsuario=:idUsu";
            Query query = em.createQuery(queryString);
            query.setParameter("idUsu", c);
            dato = query.getResultList();
            if(dato.size()>0){
                return dato;
            }else{
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dato;
    }
  
    @Override
    public boolean updateOper(Operador oper){
        boolean editado=false;
        try {
            em.merge(oper);
            editado=true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return editado;
        
    }
    
    @Override
    public Operador buuscarID(Integer id){
        Operador dato = null;
        try {
            String queryString = "from Operador u where u.idOperador=:id";
            Query query = em.createQuery(queryString);
            query.setParameter("id", id);
            List<Operador> datos = query.getResultList();
            if(datos.size()>0){
                dato=datos.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dato;
    }
}
