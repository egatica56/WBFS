/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
@Table(name = "COMPETENCIA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Competencia.findAll", query = "SELECT c FROM Competencia c")
    , @NamedQuery(name = "Competencia.findByIdComp", query = "SELECT c FROM Competencia c WHERE c.idComp = :idComp")
    , @NamedQuery(name = "Competencia.findByNombreCompetencia", query = "SELECT c FROM Competencia c WHERE c.nombreCompetencia = :nombreCompetencia")
    , @NamedQuery(name = "Competencia.findByDescripcionCompetencia", query = "SELECT c FROM Competencia c WHERE c.descripcionCompetencia = :descripcionCompetencia")
    , @NamedQuery(name = "Competencia.findBySiglaCompetencia", query = "SELECT c FROM Competencia c WHERE c.siglaCompetencia = :siglaCompetencia")})
public class Competencia implements Serializable {

    @JoinColumn(name = "ID_ESTADO", referencedColumnName = "ID_ESTADO")
    @ManyToOne
    private ControlEstados controlEstados;

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_COMP")
    private int idComp;
    @Basic(optional = false)
    @Column(name = "NOMBRE_COMPETENCIA")
    private String nombreCompetencia;
    @Basic(optional = false)
    @Column(name = "DESCRIPCION_COMPETENCIA")
    private String descripcionCompetencia;
    @Basic(optional = false)
    @Column(name = "SIGLA_COMPETENCIA")
    private String siglaCompetencia;
    @JoinTable(name = "PERFIL_COMP", joinColumns = {
        @JoinColumn(name = "ID_COMP", referencedColumnName = "ID_COMP")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_PERFIL", referencedColumnName = "ID_PERFIL")})
    @ManyToMany
    private Collection<Perfil> perfilCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "competencia")
    private Collection<Cuestionario> cuestionarioCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "competencia")
    private Collection<Observacion> observacionCollection;
    @JoinColumn(name = "ID_NIVEL", referencedColumnName = "ID_NIVEL")
    @ManyToOne(optional = false)
    private Nivel nivel;

    public Competencia() {
    }

    public Competencia(int idComp) {
        this.idComp = idComp;
    }

    public Competencia(int idComp, String nombreCompetencia, String descripcionCompetencia, String siglaCompetencia) {
        this.idComp = idComp;
        this.nombreCompetencia = nombreCompetencia;
        this.descripcionCompetencia = descripcionCompetencia;
        this.siglaCompetencia = siglaCompetencia;
    }

    public int getIdComp() {
        return idComp;
    }

    public void setIdComp(int idComp) {
        this.idComp = idComp;
    }

    public String getNombreCompetencia() {
        return nombreCompetencia;
    }

    public void setNombreCompetencia(String nombreCompetencia) {
        this.nombreCompetencia = nombreCompetencia;
    }

    public String getDescripcionCompetencia() {
        return descripcionCompetencia;
    }

    public void setDescripcionCompetencia(String descripcionCompetencia) {
        this.descripcionCompetencia = descripcionCompetencia;
    }

    public String getSiglaCompetencia() {
        return siglaCompetencia;
    }

    public void setSiglaCompetencia(String siglaCompetencia) {
        this.siglaCompetencia = siglaCompetencia;
    }

    @XmlTransient
    public Collection<Perfil> getPerfilCollection() {
        return perfilCollection;
    }

    public void setPerfilCollection(Collection<Perfil> perfilCollection) {
        this.perfilCollection = perfilCollection;
    }

    @XmlTransient
    public Collection<Cuestionario> getCuestionarioCollection() {
        return cuestionarioCollection;
    }

    public void setCuestionarioCollection(Collection<Cuestionario> cuestionarioCollection) {
        this.cuestionarioCollection = cuestionarioCollection;
    }

    @XmlTransient
    public Collection<Observacion> getObservacionCollection() {
        return observacionCollection;
    }

    public void setObservacionCollection(Collection<Observacion> observacionCollection) {
        this.observacionCollection = observacionCollection;
    }

    public Nivel getNivel() {
        return nivel;
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }



    @Override
    public String toString() {
        return "Entities.Competencia[ idComp=" + idComp + " ]";
    }

    public ControlEstados getControlEstados() {
        return controlEstados;
    }

    public void setControlEstados(ControlEstados controlEstados) {
        this.controlEstados = controlEstados;
    }
    
}
