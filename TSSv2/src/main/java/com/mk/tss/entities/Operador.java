/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mk.tss.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author WF Consulting
 */
@Entity
@Table(name = "operador")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Operador.findAll", query = "SELECT o FROM Operador o")
    , @NamedQuery(name = "Operador.findByIdOperador", query = "SELECT o FROM Operador o WHERE o.idOperador = :idOperador")
    , @NamedQuery(name = "Operador.findByNombreOperador", query = "SELECT o FROM Operador o WHERE o.nombreOperador = :nombreOperador")})
public class Operador implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_operador")
    private Integer idOperador;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "nombre_operador")
    private String nombreOperador;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idOperador")
    private Collection<Bomba> bombaCollection;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuario idUsuario;

    public Operador() {
    }

    public Operador(Integer idOperador) {
        this.idOperador = idOperador;
    }

    public Operador(Integer idOperador, String nombreOperador) {
        this.idOperador = idOperador;
        this.nombreOperador = nombreOperador;
    }

    public Integer getIdOperador() {
        return idOperador;
    }

    public void setIdOperador(Integer idOperador) {
        this.idOperador = idOperador;
    }

    public String getNombreOperador() {
        return nombreOperador;
    }

    public void setNombreOperador(String nombreOperador) {
        this.nombreOperador = nombreOperador;
    }

    @XmlTransient
    public Collection<Bomba> getBombaCollection() {
        return bombaCollection;
    }

    public void setBombaCollection(Collection<Bomba> bombaCollection) {
        this.bombaCollection = bombaCollection;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOperador != null ? idOperador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Operador)) {
            return false;
        }
        Operador other = (Operador) object;
        if ((this.idOperador == null && other.idOperador != null) || (this.idOperador != null && !this.idOperador.equals(other.idOperador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mk.tssv2.Operador[ idOperador=" + idOperador + " ]";
    }
    
}
