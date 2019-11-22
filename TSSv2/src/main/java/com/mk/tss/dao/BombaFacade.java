/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mk.tss.dao;

import com.mk.tss.entities.Bomba;
import com.mk.tss.entities.Operador;
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
public class BombaFacade extends AbstractFacade<Bomba> implements BombaFacadeLocal {

    @PersistenceContext(unitName = "com.mk_TSSv2_war_2PU")
     private EntityManager em;

    protected EntityManager getEntityManager() {
        return this.em;
    }

    public BombaFacade() {
        super(Bomba.class);
    }

    @Override
    public List<Bomba> listarBomba(List<Operador> operador) {
        List<Bomba> l = new ArrayList<Bomba>();
        if (operador.size() > 0) {
            for (Operador op : operador) {
                try {
                    String queryString = "from Bomba b where b.idOperador=:operador";
                    Query query = this.em.createQuery(queryString);
                    query.setParameter("operador", op);
                    List<Bomba> l2 = query.getResultList();
                    if(l2.size()>0){
                      for(Bomba bom:l2){
                          l.add(bom);
                      }
                    }
                } catch (Exception e) {
                    
                    e.printStackTrace();
                }
            }
        }
        return l;
    }

    @Override
    public Bomba buscarBomba(Integer bomba, Operador op) {
        List<Bomba> l = null;
        Bomba bom = null;
        try {
            String queryString = "from Bomba b where b.idBomba=:idBomba and b.idOperador=:op";
            Query query = em.createQuery(queryString);
            query.setParameter("idBomba", bomba);
            query.setParameter("op", op);
            l = query.getResultList();
            if (l.size() > 0) {
                bom = l.get(0);
            }
        } catch (Exception e) {
            bom = null;
            e.printStackTrace();
        }
        return bom;
    }
    
    @Override
    public boolean updateOper(Bomba bom){
        boolean editado=false;
        try {
            em.merge(bom);
            editado=true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return editado;
        
    }
    
}
