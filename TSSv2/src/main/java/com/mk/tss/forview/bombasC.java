/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mk.tss.forview;

import com.mk.tss.dao.BombaFacadeLocal;
import com.mk.tss.dao.OperadorFacadeLocal;
import com.mk.tss.entities.Bomba;
import com.mk.tss.entities.Operador;
import com.mk.tss.entities.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;

/**
 *
 * @author WF Consulting
 */
@ManagedBean(name ="bombasC")
@RequestScoped
public class bombasC implements Serializable{
    
    private static final long serialVersionUID=1L;
    
    private Usuario usuarioElegido;
    private List<Bomba> listaBombas;
    private Bomba bombaEdit;
    private String  serie="";
    private String modelo="";
    
    @EJB
    private BombaFacadeLocal bombaService;
    @EJB
    private OperadorFacadeLocal operadorService;

    public List<Bomba> getListaBombas() {
        return listaBombas;
    }

    public void setListaBombas(List<Bomba> listaBombas) {
        this.listaBombas = listaBombas;
    }

    public Bomba getBombaEdit() {
        return bombaEdit;
    }

    public void setBombaEdit(Bomba bombaEdit) {
        this.bombaEdit = bombaEdit;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getEconomico() {
        return economico;
    }

    public void setEconomico(String economico) {
        this.economico = economico;
    }
    private String economico="";

    public Usuario getUsuarioElegido() {
        return usuarioElegido;
    }

    public void setUsuarioElegido(Usuario usuarioElegido) {
        this.usuarioElegido = usuarioElegido;
    }
    
    @PostConstruct
    public void constrir(){
        bombaEdit= new Bomba();
        verificarSesion();
    }
    
    public void verificarSesion() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            usuarioElegido = (Usuario) context.getExternalContext().getSessionMap().get("usuarioElegido");
            if (usuarioElegido == null) {
                context.getExternalContext().redirect("login.xhtml");
            }else{
                listarBombas();
            }
        } catch (Exception localException) {
        }
    }
    
    
    public void listarBombas() {
        List<Bomba> bombas = new ArrayList();
        listaBombas = new ArrayList<Bomba>();
        List<Operador> listOper = new ArrayList<Operador>();
        if (listOper != null) {
            listOper = operadorService.buscarUsuario(usuarioElegido);
            bombas = bombaService.listarBomba(listOper);
            if (bombas.size() > 0) {
               listaBombas=bombas;
            }
        }
    }
    
    public void modal(Bomba bom) {
        bombaEdit= new Bomba();
        System.out.println("entro a editar"+ bom.getModelo());
        bombaEdit=bom;
        PrimeFaces.current().executeScript("PF('editarBom').show();");
    }
    
    public void updateBomba() {
        System.out.println("Editando bomba");
        FacesContext context = FacesContext.getCurrentInstance();
        boolean editar=bombaService.updateOper(bombaEdit);
        if(editar){
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso! Editado", "Bomba Editada"));
            listarBombas();
        }else{
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error! ", "Error al Editar"));
        }
        PrimeFaces.current().executeScript("PF('editarBom').hide();");
    }
    public void cerrarM(){
        PrimeFaces.current().executeScript("PF('editarBom').hide();");
    }
    
}
