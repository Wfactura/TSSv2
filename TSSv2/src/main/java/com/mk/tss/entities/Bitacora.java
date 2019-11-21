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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "bitacora")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bitacora.findAll", query = "SELECT b FROM Bitacora b")
    , @NamedQuery(name = "Bitacora.findByIdBitacora", query = "SELECT b FROM Bitacora b WHERE b.idBitacora = :idBitacora")
    , @NamedQuery(name = "Bitacora.findByFecha", query = "SELECT b FROM Bitacora b WHERE b.fecha = :fecha")
    , @NamedQuery(name = "Bitacora.findByUbicacion", query = "SELECT b FROM Bitacora b WHERE b.ubicacion = :ubicacion")
    , @NamedQuery(name = "Bitacora.findByConteoInicial", query = "SELECT b FROM Bitacora b WHERE b.conteoInicial = :conteoInicial")
    , @NamedQuery(name = "Bitacora.findByConteoFinal", query = "SELECT b FROM Bitacora b WHERE b.conteoFinal = :conteoFinal")
    , @NamedQuery(name = "Bitacora.findByFechai", query = "SELECT b FROM Bitacora b WHERE b.fechai = :fechai")
    , @NamedQuery(name = "Bitacora.findByFechaf", query = "SELECT b FROM Bitacora b WHERE b.fechaf = :fechaf")
    , @NamedQuery(name = "Bitacora.findByNumenvio", query = "SELECT b FROM Bitacora b WHERE b.numenvio = :numenvio")})
public class Bitacora implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bitacora", nullable = false)
    private Integer idBitacora;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "ubicacion")
    private String ubicacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "conteo_inicial")
    private int conteoInicial;
    @Basic(optional = false)
    @NotNull
    @Column(name = "conteo_final")
    private int conteoFinal;
    @Column(name = "fechai")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechai;
    @Column(name = "fechaf")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaf;
    @Column(name = "numenvio")
    private Integer numenvio;
    @JoinColumn(name = "id_bomba", referencedColumnName = "id_bomba")
    @ManyToOne(optional = false)
    private Bomba idBomba;

    public Bitacora() {
    }

    public Bitacora(Integer idBitacora) {
        this.idBitacora = idBitacora;
    }

    public Bitacora(Integer idBitacora, Date fecha, String ubicacion, int conteoInicial, int conteoFinal) {
        this.idBitacora = idBitacora;
        this.fecha = fecha;
        this.ubicacion = ubicacion;
        this.conteoInicial = conteoInicial;
        this.conteoFinal = conteoFinal;
    }

    public Integer getIdBitacora() {
        return idBitacora;
    }

    public void setIdBitacora(Integer idBitacora) {
        this.idBitacora = idBitacora;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public int getConteoInicial() {
        return conteoInicial;
    }

    public void setConteoInicial(int conteoInicial) {
        this.conteoInicial = conteoInicial;
    }

    public int getConteoFinal() {
        return conteoFinal;
    }

    public void setConteoFinal(int conteoFinal) {
        this.conteoFinal = conteoFinal;
    }

    public Date getFechai() {
        return fechai;
    }

    public void setFechai(Date fechai) {
        this.fechai = fechai;
    }

    public Date getFechaf() {
        return fechaf;
    }

    public void setFechaf(Date fechaf) {
        this.fechaf = fechaf;
    }

    public Integer getNumenvio() {
        return numenvio;
    }

    public void setNumenvio(Integer numenvio) {
        this.numenvio = numenvio;
    }

    public Bomba getIdBomba() {
        return idBomba;
    }

    public void setIdBomba(Bomba idBomba) {
        this.idBomba = idBomba;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBitacora != null ? idBitacora.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bitacora)) {
            return false;
        }
        Bitacora other = (Bitacora) object;
        if ((this.idBitacora == null && other.idBitacora != null) || (this.idBitacora != null && !this.idBitacora.equals(other.idBitacora))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mk.tssv2.Bitacora[ idBitacora=" + idBitacora + " ]";
    }
    
}
