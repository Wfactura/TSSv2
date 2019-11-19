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
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

/**
 *
 * @author WF Consulting
 */
@ManagedBean(name = "login")
@ViewScoped
public class login implements Serializable{
    
    private static final long serialVersionUID = -2152389656664659477L;
    @EJB
    private UsuarioFacadeLocal usuarioService;

    private Usuario usuarioElegido;
    private Usuario usuario;
    private String imagen = "resources/img/tss.jpg";

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
   
    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @PostConstruct
    public void inicializar() {
        logOut();
        usuario = new Usuario();
    }

    public Usuario getUsuarioElegido() {
        return usuarioElegido;
    }

    public void setUsuarioElegido(Usuario usuarioElegido) {
        this.usuarioElegido = usuarioElegido;
    }

    public String validarInicioSesion() {
        System.out.println("intento de ingresar " + this.usuario.getNombreUsuario() + "  " + this.usuario.getPassworUsuario());
        FacesContext context = FacesContext.getCurrentInstance();
        if ("".equals(usuario.getNombreUsuario())) {
            System.out.println("sin usuario");
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso!", "Debe ingresar un nombre de usuario"));
        } else if ("".equals(usuario.getPassworUsuario())) {
            System.out.println("sin contrasena");
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso!", "Debe ingresar un password"));
        } else {
            return iniciarSesion();
        }
        return null;
    }

    public String iniciarSesion() {
        System.out.println("verificando datos");
        String redireccion = null;
        try {
            usuarioElegido = usuarioService.encontrarUsuarioPorNombreYPassword(this.usuario);
            if (this.usuarioElegido != null) {

                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioElegido", usuarioElegido);
                redireccion = "Bombas?faces-redirect=true";
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Usuario o Password Incorrecto"));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "No hay conexion"));
        }
        return redireccion;
    }
    
    public void logOut(){        
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("usuarioElegido");
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("bombaE");
    }
}
