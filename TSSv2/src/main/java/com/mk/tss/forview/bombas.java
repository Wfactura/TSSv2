/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mk.tss.forview;

import com.mk.tss.beans.ListaBombas;
import com.mk.tss.dao.BitacoraFacadeLocal;
import com.mk.tss.dao.BombaFacadeLocal;
import com.mk.tss.dao.OperadorFacadeLocal;
import com.mk.tss.entities.Bomba;
import com.mk.tss.entities.Operador;
import com.mk.tss.entities.Usuario;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author WF Consulting
 */
@ManagedBean(name = "bombas")
@ViewScoped
public class bombas implements Serializable {

    @EJB
    private BitacoraFacadeLocal bitacoraService;
    @EJB
    private BombaFacadeLocal bombaService;
    @EJB
    private OperadorFacadeLocal operadorService;

    private List<ListaBombas> listBomba;
    private Usuario usuarioElegido;

    public List<ListaBombas> getListBomba() {
        return listBomba;
    }

    public void setListBomba(List<ListaBombas> listBomba) {
        this.listBomba = listBomba;
    }

    public Usuario getUsuarioElegido() {
        return usuarioElegido;
    }

    public void setUsuarioElegido(Usuario usuarioElegido) {
        this.usuarioElegido = usuarioElegido;
    }

    @PostConstruct
    public void inicializarG() {
        verificarSesion();

    }

    public void verificarSesion() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            usuarioElegido = (Usuario) context.getExternalContext().getSessionMap().get("usuarioElegido");
            if (usuarioElegido == null) {
                context.getExternalContext().redirect("LogIn.xhtml");
            } else {
                listarBombas();
            }
        } catch (Exception localException) {
        }
    }

    public void listarBombas() {
        List<Bomba> bombas = new ArrayList();
        listBomba = new ArrayList<ListaBombas>();
        List<Operador> listOper = new ArrayList<Operador>();
        if (listOper != null) {
            listOper = operadorService.buscarUsuario(usuarioElegido);
            bombas = bombaService.listarBomba(listOper);
            if (bombas.size() > 0) {
                for (Bomba b : bombas) {
                    ListaBombas lb = new ListaBombas();
                    lb.setBomba(b);
                    lb.setBitacora(bitacoraService.listarBitacoraBomba(usuarioElegido, b));
                    listBomba.add(lb);
                }
            }
        }
    }

    public String irBombeo(ListaBombas bomba) throws IOException {        
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("bombaE", bomba);
        return  "Mapa?faces-redirect=true";

    }
    public String irUbicacion(ListaBombas bomba) throws IOException {        
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("bombaE", bomba);
        return  "Ubicacion?faces-redirect=true";

    }
    public String irRutas(ListaBombas bomba) throws IOException {        
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("bombaE", bomba);
        return  "Rutas?faces-redirect=true";

    }

}
