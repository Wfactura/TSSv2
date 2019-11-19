/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mk.tss.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author WF Consulting
 */
@Entity
@Table(name = "localizacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Localizacion.findAll", query = "SELECT l FROM Localizacion l")
    , @NamedQuery(name = "Localizacion.findByIdLocalizacion", query = "SELECT l FROM Localizacion l WHERE l.idLocalizacion = :idLocalizacion")
    , @NamedQuery(name = "Localizacion.findByUsuario", query = "SELECT l FROM Localizacion l WHERE l.usuario = :usuario")
    , @NamedQuery(name = "Localizacion.findByBomba", query = "SELECT l FROM Localizacion l WHERE l.bomba = :bomba")
    , @NamedQuery(name = "Localizacion.findByFecha", query = "SELECT l FROM Localizacion l WHERE l.fecha = :fecha")
    , @NamedQuery(name = "Localizacion.findByLatitud", query = "SELECT l FROM Localizacion l WHERE l.latitud = :latitud")
    , @NamedQuery(name = "Localizacion.findByLongitud", query = "SELECT l FROM Localizacion l WHERE l.longitud = :longitud")})
public class Localizacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_localizacion")
    private Integer idLocalizacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "usuario")
    private int usuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "bomba")
    private int bomba;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Size(max = 30)
    @Column(name = "latitud")
    private String latitud;
    @Size(max = 30)
    @Column(name = "longitud")
    private String longitud;

    public Localizacion() {
    }

    public Localizacion(Integer idLocalizacion) {
        this.idLocalizacion = idLocalizacion;
    }

    public Localizacion(Integer idLocalizacion, int usuario, int bomba, Date fecha) {
        this.idLocalizacion = idLocalizacion;
        this.usuario = usuario;
        this.bomba = bomba;
        this.fecha = fecha;
    }

    public Integer getIdLocalizacion() {
        return idLocalizacion;
    }

    public void setIdLocalizacion(Integer idLocalizacion) {
        this.idLocalizacion = idLocalizacion;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public int getBomba() {
        return bomba;
    }

    public void setBomba(int bomba) {
        this.bomba = bomba;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLocalizacion != null ? idLocalizacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Localizacion)) {
            return false;
        }
        Localizacion other = (Localizacion) object;
        if ((this.idLocalizacion == null && other.idLocalizacion != null) || (this.idLocalizacion != null && !this.idLocalizacion.equals(other.idLocalizacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mk.tssv2.Localizacion[ idLocalizacion=" + idLocalizacion + " ]";
    }
    
}
