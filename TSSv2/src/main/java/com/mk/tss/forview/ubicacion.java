/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mk.tss.forview;

import com.mk.tss.beans.ListaBombas;
import com.mk.tss.dao.LocalizacionFacadeLocal;
import com.mk.tss.entities.Localizacion;
import com.mk.tss.entities.Usuario;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

/**
 *
 * @author WF Consulting
 */
@ManagedBean(name = "ubicacion")
@ViewScoped
public class ubicacion implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private LocalizacionFacadeLocal localizacionDao;
    private Usuario usuarioElegido;
    private Localizacion listLocalizacion;
    private ListaBombas bombaL = new ListaBombas();
    private MapModel simpleModel = new DefaultMapModel();
    private String center = "";

    public String getCenter() {
        return center;
    }

    public void setCenter(String center) {
        this.center = center;
    }

    public Usuario getUsuarioElegido() {
        return usuarioElegido;
    }

    public void setUsuarioElegido(Usuario usuarioElegido) {
        this.usuarioElegido = usuarioElegido;
    }

    public Localizacion getListLocalizacion() {
        return listLocalizacion;
    }

    public void setListLocalizacion(Localizacion listLocalizacion) {
        this.listLocalizacion = listLocalizacion;
    }

    public MapModel getSimpleModel() {
        return simpleModel;
    }

    public void setSimpleModel(MapModel simpleModel) {
        this.simpleModel = simpleModel;
    }

    @PostConstruct
    public void construir() {
        verificarSesion();
    }

    public void verificarSesion() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            usuarioElegido = (Usuario) context.getExternalContext().getSessionMap().get("usuarioElegido");

            if (usuarioElegido == null) {
                context.getExternalContext().redirect("LogIn.xhtml");
            } else {
                bombaL = (ListaBombas) context.getExternalContext().getSessionMap().get("bombaE");
                if (bombaL != null) {
                    ubicacion();
                }else{
                    context.getExternalContext().redirect("Bombas.xhtml");
                }
            }
        } catch (Exception localException) {
        }
    }

    public void ubicacion() {
        simpleModel = null;
        simpleModel = new DefaultMapModel();
        listLocalizacion = localizacionDao.buscarUsuBomUbic(bombaL.getBomba().getIdOperador().getIdUsuario().getIdUsuario(), bombaL.getBomba().getIdBomba());
        System.out.println("datos de localizacion " + listLocalizacion.getLatitud()+" , " +listLocalizacion.getLongitud());
        Double latitud = Double.parseDouble(listLocalizacion.getLatitud());
        Double longitud = Double.parseDouble(listLocalizacion.getLongitud());
        LatLng coord1 = new LatLng(latitud.doubleValue(), longitud.doubleValue());
        center = listLocalizacion.getLatitud()+","+listLocalizacion.getLongitud();
        simpleModel.addOverlay(new Marker(coord1, "Bomba "+bombaL.getBomba().getNumeco() , "https://maps.google.com/mapfiles/ms/micons/pink-dot.png"));
    }
    
}
