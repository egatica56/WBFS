/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;

import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author EduardoGatica
 */
@Entity
@Table(name = "CONTROL_ESTADOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ControlEstados.findAll", query = "SELECT c FROM ControlEstados c")
    , @NamedQuery(name = "ControlEstados.findByIdEstado", query = "SELECT c FROM ControlEstados c WHERE c.idEstado = :idEstado")
    , @NamedQuery(name = "ControlEstados.findByDescripcionEstado", query = "SELECT c FROM ControlEstados c WHERE c.descripcionEstado = :descripcionEstado")})
public class ControlEstados implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_ESTADO")
    private int idEstado;
    @Basic(optional = false)
    @Column(name = "DESCRIPCION_ESTADO")
    private String descripcionEstado;
    @OneToMany(mappedBy = "controlEstados")
    private Collection<Usuario> usuarioCollection;

    public ControlEstados() {
    }

    public ControlEstados(int idEstado) {
        this.idEstado = idEstado;
    }

    public ControlEstados(int idEstado, String descripcionEstado) {
        this.idEstado = idEstado;
        this.descripcionEstado = descripcionEstado;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public String getDescripcionEstado() {
        return descripcionEstado;
    }

    public void setDescripcionEstado(String descripcionEstado) {
        this.descripcionEstado = descripcionEstado;
    }

    @XmlTransient
    public Collection<Usuario> getUsuarioCollection() {
        return usuarioCollection;
    }

    public void setUsuarioCollection(Collection<Usuario> usuarioCollection) {
        this.usuarioCollection = usuarioCollection;
    }


    @Override
    public String toString() {
        return "Entities.ControlEstados[ idEstado=" + idEstado + " ]";
    }
    
}
