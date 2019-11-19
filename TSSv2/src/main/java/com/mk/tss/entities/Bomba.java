/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mk.tss.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author WF Consulting
 */
@Entity
@Table(name = "bomba")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bomba.findAll", query = "SELECT b FROM Bomba b")
    , @NamedQuery(name = "Bomba.findByIdBomba", query = "SELECT b FROM Bomba b WHERE b.idBomba = :idBomba")
    , @NamedQuery(name = "Bomba.findByNuminterno", query = "SELECT b FROM Bomba b WHERE b.numinterno = :numinterno")
    , @NamedQuery(name = "Bomba.findByFechaAlta", query = "SELECT b FROM Bomba b WHERE b.fechaAlta = :fechaAlta")
    , @NamedQuery(name = "Bomba.findByModelo", query = "SELECT b FROM Bomba b WHERE b.modelo = :modelo")
    , @NamedQuery(name = "Bomba.findBySerie", query = "SELECT b FROM Bomba b WHERE b.serie = :serie")
    , @NamedQuery(name = "Bomba.findByNumeco", query = "SELECT b FROM Bomba b WHERE b.numeco = :numeco")
    , @NamedQuery(name = "Bomba.findByOdometro", query = "SELECT b FROM Bomba b WHERE b.odometro = :odometro")})
public class Bomba implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_bomba")
    private Integer idBomba;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "numinterno")
    private String numinterno;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_alta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAlta;
    @Size(max = 200)
    @Column(name = "modelo")
    private String modelo;
    @Size(max = 200)
    @Column(name = "serie")
    private String serie;
    @Size(max = 200)
    @Column(name = "numeco")
    private String numeco;
    @Basic(optional = false)
    @NotNull
    @Column(name = "odometro")
    private int odometro;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idBomba")
    private Collection<Bitacora> bitacoraCollection;
    @JoinColumn(name = "id_operador", referencedColumnName = "id_operador")
    @ManyToOne(optional = false)
    private Operador idOperador;

    public Bomba() {
    }

    public Bomba(Integer idBomba) {
        this.idBomba = idBomba;
    }

    public Bomba(Integer idBomba, String numinterno, Date fechaAlta, int odometro) {
        this.idBomba = idBomba;
        this.numinterno = numinterno;
        this.fechaAlta = fechaAlta;
        this.odometro = odometro;
    }

    public Integer getIdBomba() {
        return idBomba;
    }

    public void setIdBomba(Integer idBomba) {
        this.idBomba = idBomba;
    }

    public String getNuminterno() {
        return numinterno;
    }

    public void setNuminterno(String numinterno) {
        this.numinterno = numinterno;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getNumeco() {
        return numeco;
    }

    public void setNumeco(String numeco) {
        this.numeco = numeco;
    }

    public int getOdometro() {
        return odometro;
    }

    public void setOdometro(int odometro) {
        this.odometro = odometro;
    }

    @XmlTransient
    public Collection<Bitacora> getBitacoraCollection() {
        return bitacoraCollection;
    }

    public void setBitacoraCollection(Collection<Bitacora> bitacoraCollection) {
        this.bitacoraCollection = bitacoraCollection;
    }

    public Operador getIdOperador() {
        return idOperador;
    }

    public void setIdOperador(Operador idOperador) {
        this.idOperador = idOperador;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBomba != null ? idBomba.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bomba)) {
            return false;
        }
        Bomba other = (Bomba) object;
        if ((this.idBomba == null && other.idBomba != null) || (this.idBomba != null && !this.idBomba.equals(other.idBomba))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mk.tssv2.Bomba[ idBomba=" + idBomba + " ]";
    }
    
}
