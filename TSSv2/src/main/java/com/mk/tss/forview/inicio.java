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
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

/**
 *
 * @author WF Consulting
 */
@ManagedBean(name = "inicio")
@ViewScoped
public class inicio implements Serializable{
    private static final long serialVersionUID =1L;
    
    @EJB
    private UsuarioFacadeLocal usuarioDao;
    private Usuario usurioElegido;
    private Usuario usuario;
    private String imagen;

    public Usuario getUsurioElegido() {
        return usurioElegido;
    }

    public void setUsurioElegido(Usuario usurioElegido) {
        this.usurioElegido = usurioElegido;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    
    @PostConstruct
    public void constructor(){
        imagen="Portal/Imagenes/logo.JPG";
        usuario= new Usuario();
        usurioElegido= new Usuario();
        usuario.setPassworUsuario("JdVa0oOqQAr0ZMdtcTwHrQ==");
        usuario.setNombreUsuario("Tss");
    }
    
    public String demo(){        
        usurioElegido = usuarioDao.encontrarUsuarioPorNombreYPassword(usuario);
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().put("usuarioElegido", usurioElegido);
        return "Bombas?faces-redirect=true";
    }
    
}
