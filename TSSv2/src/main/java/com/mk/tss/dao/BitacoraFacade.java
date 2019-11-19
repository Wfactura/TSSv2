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
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author WF Consulting
 */
@Stateless
public class BitacoraFacade extends AbstractFacade<Bitacora> implements BitacoraFacadeLocal {

    @PersistenceContext(unitName = "com.mk_TSSv2_war_2PU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return this.em;
    }

    public BitacoraFacade() {
        super(Bitacora.class);
    }

    @Override
    public List<Bitacora> listarBitacora(Bomba bomba) {
        List<Bitacora> l = null;
        try {
            String queryString = "from Bitacora b where b.idBomba=:bomba order by b.fechai desc";
            Query query = this.em.createQuery(queryString);
            query.setParameter("bomba", bomba);
            l = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return l;
    }

    @Override
    public Bitacora listarBitacoraBomba(Usuario idUsuario, Bomba bomba) {
        List<Bitacora> l = null;
        Bitacora b = null;
        try {
            String queryString = "from Bitacora b where b.idBomba=:bomba order by b.fechaf desc";
            Query query = this.em.createQuery(queryString);
            query.setParameter("bomba", bomba);
            l = query.getResultList();
            if (l.size() > 0) {
                b = (Bitacora) l.get(0);
            }
        } catch (Exception e) {
            b = null;
            e.printStackTrace();
        }
        return b;
    }

    @Override
    public List<Bitacora> listarBitacoraFecha(Bomba idBomba, Date inicio, Date fin) {
        List<Bitacora> l = null;
        try {
            String queryString = "from Bitacora b where b.idBomba=:bomba and b.fechai between :inicio and :fin order by b.fechai desc";
            Query query = this.em.createQuery(queryString);
            query.setParameter("bomba", idBomba);
            query.setParameter("inicio", inicio);
            query.setParameter("fin", fin);
            l = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return l;
    }
    
    @Override
    public void insertar(Bitacora bitacora){
        try {
            em.merge(bitacora);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public Bitacora buscar(Bomba bomba){
        Bitacora bitacora= null;
        try {
            String queryString = "from Bitacora b where b.idBomba=:bomba";
            Query query = this.em.createQuery(queryString);
            query.setParameter("bomba", bomba);
            List<Bitacora> lBItacora=query.getResultList();
            if(lBItacora.size()>0){
                bitacora=lBItacora.get(0);
            }
            return bitacora;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitacora;
    }
    
    @Override
    public boolean buscarNumEnv(Bomba bomba, Integer numEnv){        
        try {
            String queryString = "from Bitacora b where b.idBomba=:bomba and b.numenvio=:nume";
            Query query = this.em.createQuery(queryString);
            query.setParameter("bomba", bomba);
            query.setParameter("nume", numEnv);
            List<Bitacora> lBItacora=query.getResultList();
            if(lBItacora.size()>0){
                return false;
            }else{
                return true;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
}
