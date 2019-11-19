/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mk.tss.dao;

import com.mk.tss.entities.Localizacion;
import java.util.Date;
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
public class LocalizacionFacade extends AbstractFacade<Localizacion> implements LocalizacionFacadeLocal {

    @PersistenceContext(unitName = "com.mk_TSSv2_war_2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LocalizacionFacade() {
        super(Localizacion.class);
    }

    @Override
    public List<Localizacion> buscarUsuBomList(Integer u, Integer b, String i, String f) {
        List<Localizacion> lista = null;
        try {
            String queryString = "from Localizacion l where l.usuario=:u and l.bomba=:b and l.fecha between '"+i+"' and '"+f+"' order by l.fecha asc";
            System.out.println(queryString);
            Query query = this.em.createQuery(queryString);
            query.setParameter("u", u);
            query.setParameter("b", b);
            lista = query.getResultList();
            System.out.println("total de datos "+ lista.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    @Override
    public Localizacion buscarUsuBomUbic(Integer u, Integer b) {
        Localizacion local = null;
        try {
            String queryString = "from Localizacion l where l.usuario=:u and l.bomba=:b order by l.fecha desc";
            Query query = this.em.createQuery(queryString);
            query.setParameter("u", u);
            query.setParameter("b", b);
            List<Localizacion> lista = query.getResultList();
            if(lista.size()>0){
                local=lista.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return local;
    }
    
}
