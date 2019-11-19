/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mk.tss.dao;

import com.mk.tss.entities.Localizacion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author WF Consulting
 */
@Local
public interface LocalizacionFacadeLocal {

    void create(Localizacion localizacion);

    void edit(Localizacion localizacion);

    void remove(Localizacion localizacion);

    Localizacion find(Object id);

    List<Localizacion> findAll();

    List<Localizacion> findRange(int[] range);

    int count();
    
    List<Localizacion> buscarUsuBomList(Integer u, Integer b, String i, String f);
    
    Localizacion buscarUsuBomUbic(Integer u, Integer b);
    
}
