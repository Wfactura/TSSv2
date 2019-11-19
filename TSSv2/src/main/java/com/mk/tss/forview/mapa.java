/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mk.tss.forview;

import com.mk.tss.beans.ListaBombas;
import com.mk.tss.beans.TotalBomba;
import com.mk.tss.dao.BitacoraFacadeLocal;
import com.mk.tss.dao.BombaFacadeLocal;
import com.mk.tss.entities.Bitacora;
import com.mk.tss.entities.Bomba;
import com.mk.tss.entities.Usuario;
import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

/**
 *
 * @author WF Consulting
 */
@ManagedBean(name = "mapa")
@ViewScoped
public class mapa implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private BitacoraFacadeLocal bitacoraService;
    @EJB
    private BombaFacadeLocal bombaService;

    private Date fechaInicio = null;
    private Date fechaFin = null;
    private ListaBombas infoBomba = new ListaBombas();
    private Integer serieBomba = 0;
    private List<Bitacora> listaBitacora;
    private MapModel simpleModel = new DefaultMapModel();
    private String center = "";
    private List<TotalBomba> totalBomba = new ArrayList();
    private Bomba bombaActual = new Bomba();
    private String imagen = "resources/img/tss.jpg";
    private Usuario usuarioElegido;
    private Integer totalRegistos;
    private String heightP;

    public String getHeightP() {
        return heightP;
    }

    public void setHeightP(String heightP) {
        this.heightP = heightP;
    }

    public Integer getTotalRegistos() {
        return totalRegistos;
    }

    public void setTotalRegistos(Integer totalRegistos) {
        this.totalRegistos = totalRegistos;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Usuario getUsuarioElegido() {
        return usuarioElegido;
    }

    public void setUsuarioElegido(Usuario usuarioElegido) {
        this.usuarioElegido = usuarioElegido;
    }

    public List<Bitacora> getListaBitacora() {
        return this.listaBitacora;
    }

    public void setListaBitacora(List<Bitacora> listaBitacora) {
        this.listaBitacora = listaBitacora;
    }

    public Integer getSerieBomba() {
        return this.serieBomba;
    }

    public void setSerieBomba(Integer serieBomba) {
        this.serieBomba = serieBomba;
    }

    public Bomba getBombaActual() {
        return this.bombaActual;
    }

    public void setBombaActual(Bomba bombaActual) {
        this.bombaActual = bombaActual;
    }

    public List<TotalBomba> getTotalBomba() {
        return this.totalBomba;
    }

    public void setTotalBomba(List<TotalBomba> totalBomba) {
        this.totalBomba = totalBomba;
    }

    public Date getFechaInicio() {
        return this.fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return this.fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public ListaBombas getInfoBomba() {
        return this.infoBomba;
    }

    public void setInfoBomba(ListaBombas infoBomba) {
        this.infoBomba = infoBomba;
    }

    public String getCenter() {
        return this.center;
    }

    public void setCenter(String center) {
        this.center = center;
    }

    public MapModel getSimpleModel() {
        return this.simpleModel;
    }

    @PostConstruct
    public void iniciar() {
        verificarSesion();
    }

    public void verificarSesion() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            usuarioElegido = (Usuario) context.getExternalContext().getSessionMap().get("usuarioElegido");
            if (usuarioElegido == null) {
                context.getExternalContext().redirect("LogIn.xhtml");
            } else {
                try {
                    ListaBombas bombaL = (ListaBombas) context.getExternalContext().getSessionMap().get("bombaE");
                    if (bombaL != null) {
                        showM(bombaL);
                    }
                } catch (Exception localException) {
                }
            }
        } catch (Exception localException) {
        }
    }

    public void showM(ListaBombas bomba) {
        System.out.println("bomba enviada "+ bomba.getBomba().getIdBomba());
        simpleModel = null;
        simpleModel = new DefaultMapModel();
        Date hoy = new Date();
        Date antes = new Date();
        totalBomba = new ArrayList<TotalBomba>();
        List<Bitacora> lBitacora = new ArrayList();
        serieBomba = bomba.getBomba().getIdBomba();
        lBitacora = bitacoraService.listarBitacora(bomba.getBomba());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String hoyS = formatter.format(hoy);
        String antesS = formatter.format(antes);
        try {
            hoy = formatter.parse(hoyS);
            antes = formatter.parse(antesS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int i;
        Integer total = 0;
        if (lBitacora.size() > 0) {
            List<Integer> totalesZ = new ArrayList<Integer>();
            List<Bitacora> lGABitacoranew = new ArrayList<Bitacora>();
            int contador = 0;
            for (Bitacora bit : lBitacora) {

                if (contador == 0) {
                    lGABitacoranew.add(bit);
                    totalesZ.add(bit.getConteoFinal() - bit.getConteoInicial());

                } else {
                    try {
                        String fechbit = formatter.format(bit.getFechai());
                        Date fIBit = formatter.parse(fechbit);
                        int comaBIT = bit.getUbicacion().indexOf(",");
                        Double latBit = Double.parseDouble(bit.getUbicacion().substring(0, comaBIT).replaceAll(",", "").trim());
                        Double lonBit = Double.parseDouble(bit.getUbicacion().substring(comaBIT).replaceAll(",", "").trim());
                        boolean band = false;
                        int contador2 = 0;
                        for (Bitacora lGA : lGABitacoranew) {

                            String fechLGA = formatter.format(lGA.getFechai());
                            Date fILGA = formatter.parse(fechLGA);
                            int comaLGA = lGA.getUbicacion().indexOf(",");
                            Double latLGA = Double.parseDouble(lGA.getUbicacion().substring(0, comaLGA).replaceAll(",", "").trim());
                            Double lonLGA = Double.parseDouble(lGA.getUbicacion().substring(comaLGA).replaceAll(",", "").trim());

                            if (fIBit.compareTo(fILGA) == 0 && (((latBit >= (latLGA - 0.001)) && latBit <= (latLGA + 0.001))) && (((lonBit >= (lonLGA - 0.001)) && lonBit <= (lonLGA + 0.001)))) {

                                lGA.setFechai(bit.getFechai());
                                lGA.setConteoInicial(bit.getConteoInicial());
                                Integer tl = totalesZ.get(contador2);
                                tl += bit.getConteoFinal() - bit.getConteoInicial();
                                totalesZ.set(contador2, tl);
                                band = false;
                                break;
                            } else {
                                contador2++;
                                band = true;
                            }
                        }
                        if (band) {
                            totalesZ.add(bit.getConteoFinal() - bit.getConteoInicial());
                            lGABitacoranew.add(bit);
                        }
                    } catch (ParseException ex) {
                        Logger.getLogger(mapa.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                contador++;
            }

            i = 0;
            for (Bitacora b : lGABitacoranew) {
                try {
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
                    String dateString = dateFormat.format(b.getFecha());
                    Date date = dateFormat.parse(dateString);
                    String cfecha = formatter.format(b.getFechai());
                    Date cDate = formatter.parse(cfecha);
                    if (cDate.before(antes)) {
                        antes = cDate;
                    }
                    TotalBomba tb = new TotalBomba();
                    int coma = b.getUbicacion().indexOf(",");
                    String coor = b.getUbicacion();
                    Double latitud = Double.parseDouble(coor.substring(0, coma).replaceAll(",", "").trim());
                    Double longitud = Double.parseDouble(coor.substring(coma).replaceAll(",", "").trim());
                    LatLng coord1 = new LatLng(latitud.doubleValue(), longitud.doubleValue());
                    simpleModel.addOverlay(new Marker(coord1, "" + (i + 1), "https://maps.google.com/mapfiles/ms/micons/pink-dot.png"));

                    tb.setNombre("" + (i + 1));
                    tb.setFechaI(b.getFechai());
                    tb.setFechaF(b.getFechaf());
                    tb.setContador(totalesZ.get(i));
                    total += (b.getConteoFinal() - b.getConteoInicial());
                    totalBomba.add(tb);
                    if (i == 0) {
                        center = coor;
                    }
                    i++;
                } catch (ParseException ex) {
                    Logger.getLogger(mapa.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            totalRegistos = total;
        }
        fechaInicio = antes;
        fechaFin = hoy;
        infoBomba = bomba;
        bombaActual = bomba.getBomba();

    }

    public void buscarFecha() throws ParseException {
        simpleModel = null;
        totalBomba = new ArrayList<TotalBomba>();
        totalRegistos=0;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String fi = formatter.format(fechaInicio);
        String ff = formatter.format(fechaFin);
        Date ini = formatter.parse(fi);
        Date fin = formatter.parse(ff);

        Calendar cal = Calendar.getInstance();
        cal.setTime(fin);
        cal.add(Calendar.DATE, 1);
        Date fechaF = cal.getTime();
        Bomba nBomba = bombaService.find(serieBomba);
        if (ini.before(fechaF)) {
            List<Bitacora> lBitacora = new ArrayList();
            lBitacora = bitacoraService.listarBitacoraFecha(nBomba, ini, fechaF);
            Integer total = 0;
            if (lBitacora.size() > 0) {
                List<Integer> totalesZ = new ArrayList<Integer>();
                List<Bitacora> lGABitacoranew = new ArrayList<Bitacora>();
                int contador = 0;
                for (Bitacora bit : lBitacora) {
                    if (contador == 0) {

                        lGABitacoranew.add(bit);
                        totalesZ.add(bit.getConteoFinal() - bit.getConteoInicial());

                    } else {
                        String fechbit = formatter.format(bit.getFechai());
                        Date fIBit = formatter.parse(fechbit);
                        int comaBIT = bit.getUbicacion().indexOf(",");
                        Double latBit = Double.parseDouble(bit.getUbicacion().substring(0, comaBIT).replaceAll(",", "").trim());
                        Double lonBit = Double.parseDouble(bit.getUbicacion().substring(comaBIT).replaceAll(",", "").trim());
                        boolean band = false;
                        int contador2 = 0;
                        for (Bitacora lGA : lGABitacoranew) {
                            String fechLGA = formatter.format(lGA.getFechai());
                            Date fILGA = formatter.parse(fechLGA);
                            int comaLGA = lGA.getUbicacion().indexOf(",");
                            Double latLGA = Double.parseDouble(lGA.getUbicacion().substring(0, comaLGA).replaceAll(",", "").trim());
                            Double lonLGA = Double.parseDouble(lGA.getUbicacion().substring(comaLGA).replaceAll(",", "").trim());

                            if (fIBit.compareTo(fILGA) == 0 && (((latBit >= (latLGA - 0.001)) && latBit <= (latLGA + 0.001))) && (((lonBit >= (lonLGA - 0.001)) && lonBit <= (lonLGA + 0.001)))) {
                                lGA.setFechai(bit.getFechai());
                                lGA.setConteoInicial(bit.getConteoInicial());
                                Integer tl = totalesZ.get(contador2);
                                tl += bit.getConteoFinal() - bit.getConteoInicial();
                                totalesZ.set(contador2, tl);
                                band = false;
                                break;
                            } else {
                                contador2++;
                                band = true;
                            }

                        }
                        if (band) {
                            lGABitacoranew.add(bit);
                            totalesZ.add(bit.getConteoFinal() - bit.getConteoInicial());
                        }
                    }
                    contador++;
                }
                int i = 0;
                simpleModel = new DefaultMapModel();
                for (Bitacora b : lGABitacoranew) {
                    TotalBomba tb = new TotalBomba();
                    int coma = b.getUbicacion().indexOf(",");
                    String coor = b.getUbicacion();
                    Double latitud = Double.parseDouble(coor.substring(0, coma).replaceAll(",", "").trim());
                    Double longitud = Double.parseDouble(coor.substring(coma).replaceAll(",", "").trim());
                    LatLng coord1 = new LatLng(latitud.doubleValue(), longitud.doubleValue());
                    simpleModel.addOverlay(new Marker(coord1, "" + (i + 1), "https://maps.google.com/mapfiles/ms/micons/pink-dot.png"));

                    tb.setNombre("" + (i + 1));
                    tb.setFechaI(b.getFechai());
                    tb.setFechaF(b.getFechaf());
                    tb.setContador(totalesZ.get(i));

                    totalBomba.add(tb);
                    total += (b.getConteoFinal() - b.getConteoInicial());
                    if (i == 0) {
                        center = coor;
                    }
                    i++;

                }
                totalRegistos = total;
            }
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
