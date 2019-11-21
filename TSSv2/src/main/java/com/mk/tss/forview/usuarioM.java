/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mk.tss.forview;

import com.mk.tss.dao.UsuarioFacadeLocal;
import com.mk.tss.entities.Usuario;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author WF Consulting
 */
@ManagedBean(name = "usuarioM")
@RequestScoped
public class usuarioM implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    UsuarioFacadeLocal usuarioService;

    private Usuario usuarioElegido;
    private String contra;
    private String nueva;

    public Usuario getUsuarioElegido() {
        return usuarioElegido;
    }

    public void setUsuarioElegido(Usuario usuarioElegido) {
        this.usuarioElegido = usuarioElegido;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

    public String getNueva() {
        return nueva;
    }

    public void setNueva(String nueva) {
        this.nueva = nueva;
    }

    @PostConstruct
    public void inicializarU() {
        verificarSesion();

    }

    public void verificarSesion() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            usuarioElegido = (Usuario) context.getExternalContext().getSessionMap().get("usuarioElegido");
            if (usuarioElegido == null) {
                context.getExternalContext().redirect("login.xhtml");
            } else {
                contra = "";
                nueva = "";
            }
        } catch (Exception localException) {
        }
    }
    
    public void updateUser() {

        if (contra.equals(usuarioElegido.getPassworUsuario())) {
            usuarioElegido.setPassworUsuario(nueva);
            FacesContext context = FacesContext.getCurrentInstance();
            boolean upd = usuarioService.updateOper(usuarioElegido);
            if (upd) {
                contra = "";
                nueva = "";
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso! Editado", "Bomba Editada"));
            } else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error! ", "Error al Editar"));
            }
        }

    }
    
}
