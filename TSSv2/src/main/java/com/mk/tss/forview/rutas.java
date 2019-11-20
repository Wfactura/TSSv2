/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mk.tss.forview;

/**
 *
 * @author WF Consulting
 */
import com.mk.tss.beans.ListaBombas;
import com.mk.tss.dao.LocalizacionFacadeLocal;
import com.mk.tss.entities.Localizacion;
import com.mk.tss.entities.Usuario;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.primefaces.model.map.Polyline;

@ManagedBean(name = "rutas")
@ViewScoped
public class rutas implements Serializable {

    private static final long serialVersionUID = 1L;
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    @EJB
    private LocalizacionFacadeLocal localizacionDao;
    private Usuario usuarioElegido;
    private Localizacion listLocalizacion;
    private ListaBombas bombaL = new ListaBombas();
    private MapModel simpleModel = new DefaultMapModel();
    private String center = "";
    private Date inicio;
    private Date fin;

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

    public String getCenter() {
        return center;
    }

    public void setCenter(String center) {
        this.center = center;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    @PostConstruct
    public void iniciar() {
        inicio = new Date();
        fin = new Date();
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
                    rutasM();
                } else {
                    context.getExternalContext().redirect("Bombas.xhtml");
                }
            }
        } catch (Exception localException) {
        }
    }

    public void rutasM() {
        simpleModel = null;
        simpleModel = new DefaultMapModel();
        try {
            List<LatLng> lineas = new ArrayList<LatLng>();
            String fi = formatter.format(inicio);
            String ff = formatter.format(fin);
            fi = fi + " 00:00:00";
            ff = ff + " 23:59:59";
            System.out.println("----- " + fi + " " + ff);

            List<Localizacion> localList = localizacionDao.buscarUsuBomList(usuarioElegido.getIdUsuario(), bombaL.getBomba().getIdBomba(), fi, ff);
            if (localList != null && localList.size() > 0) {
                int i = 0;
                for (Localizacion l : localList) {
                    System.out.println(Double.parseDouble(l.getLatitud()) + " , " + Double.parseDouble(l.getLongitud()));
                    LatLng coord1 = new LatLng(Double.parseDouble(l.getLatitud()), Double.parseDouble(l.getLongitud()));
                    //simpleModel.addOverlay(new Marker(coord1, "" + (i + 1), "https://maps.google.com/mapfiles/ms/micons/pink-dot.png"));

                    lineas.add(coord1);
                    center = l.getLatitud() + "," + l.getLongitud();
                    if (i == 0) {
                        simpleModel.addOverlay(new Marker(coord1, "Inicio"));
                    } else if (i == (localList.size() - 1)) {
                        simpleModel.addOverlay(new Marker(coord1, "Fin", null, "Aplicacion/imgA/marcaMapa.png"));
                    }
                    i++;
                }
                Polyline polyline = new Polyline();
                if (lineas.size() > 0) {
                    System.out.println("entro al for "
                            + "");
                    for (LatLng t : lineas) {
                        polyline.getPaths().add(t);
                    }
                    polyline.setStrokeWeight(10);
                    polyline.setStrokeColor("#FF9900");
                    polyline.setStrokeOpacity(0.7);
                    simpleModel.addOverlay(polyline);
                }
            } else {
                Localizacion l = localizacionDao.buscarUsuBomUbic(usuarioElegido.getIdUsuario(), bombaL.getBomba().getIdBomba());
                if (l != null) {
                    LatLng coord1 = new LatLng(Double.parseDouble(l.getLatitud()), Double.parseDouble(l.getLongitud()));
                    simpleModel.addOverlay(new Marker(coord1, "Actual " + l.getFecha(), null, "Aplicacion/imgA/marcaMapa.png"));
                    center = l.getLatitud() + "," + l.getLongitud();
                }
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void irGlobal() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioElegido", usuarioElegido);
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().redirect("Bombas.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(mapa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
