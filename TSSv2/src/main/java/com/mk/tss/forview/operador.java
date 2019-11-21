/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mk.tss.forview;

import com.mk.tss.dao.OperadorFacadeLocal;
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
@ManagedBean(name ="operador")
@RequestScoped
public class operador implements Serializable {

    private static final long serialVersionUID=1L;
    
    @EJB
    private OperadorFacadeLocal operadorService;
    private String operadorS="";
    private Operador operador;
    private List<Operador> listOperador;
    private Usuario usuarioElegido;
    private Integer numOper=0;

    public String getOperadorS() {
        return operadorS;
    }

    public void setOperadorS(String operadorS) {
        this.operadorS = operadorS;
    }

    public Operador getOperador() {
        return operador;
    }

    public void setOperador(Operador operador) {
        this.operador = operador;
    }

    public List<Operador> getListOperador() {
        return listOperador;
    }

    public void setListOperador(List<Operador> listOperador) {
        this.listOperador = listOperador;
    }

    public Usuario getUsuarioElegido() {
        return usuarioElegido;
    }

    public void setUsuarioElegido(Usuario usuarioElegido) {
        this.usuarioElegido = usuarioElegido;
    }

    public Integer getNumOper() {
        return numOper;
    }

    public void setNumOper(Integer numOper) {
        this.numOper = numOper;
    }
    
    @PostConstruct
    public void inniciar(){
        System.out.println("constructor");
        verificarSesion();
        buscarOperadores();
    }
    
    public void verificarSesion(){
        System.out.println("session");
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            usuarioElegido = (Usuario) context.getExternalContext().getSessionMap().get("usuarioElegido");
            if (usuarioElegido == null) {
                context.getExternalContext().redirect("login.xhtml");
            }
        } catch (Exception localException) {
        }
    }
    public void buscarOperadores() {
        listOperador=new ArrayList<Operador>();
        System.out.println("buscar operadores");
        listOperador = operadorService.buscarUsuario(usuarioElegido);
    }
    
    public void modal(Operador oper) {
        numOper=oper.getIdOperador();
        operadorS=oper.getNombreOperador();
        System.out.println("numero "+ numOper + "  operadorS0  "+operadorS);
        PrimeFaces.current().executeScript("PF('editarOp').show();");
    }
    
    public void updateOper() {
        System.out.println("Editando operador "+ operadorS);
        FacesContext context = FacesContext.getCurrentInstance();
        
        if(!operadorS.equals("")){
            operador=new Operador();
            operador= operadorService.buuscarID(numOper);
            operador.setNombreOperador(operadorS);
            System.out.println("cabio nombre "+ operador.getNombreOperador());
            boolean editado=operadorService.updateOper(operador);            
            if(editado){                
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso! Editado", "Operador Editado"));
                buscarOperadores();
            }else{
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR!", "No se pudo Editar"));
            }
        }else{
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR!", "No se puede estar vacio el campo"));
        }
        PrimeFaces.current().executeScript("PF('editarOp').hide();");
    }
    
    public void cerrarM(){
        PrimeFaces.current().executeScript("PF('editarOp').hide();");
    }
    
    public void operadorSet(){
        operadorS=operadorS;
        System.out.println("nombre op "+ operadorS);
    }
}
