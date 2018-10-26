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
import javax.persistence.ManyToMany;
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
@Table(name = "PERFIL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Perfil.findAll", query = "SELECT p FROM Perfil p")
    , @NamedQuery(name = "Perfil.findByIdPerfil", query = "SELECT p FROM Perfil p WHERE p.idPerfil = :idPerfil")
    , @NamedQuery(name = "Perfil.findByNombrePerfil", query = "SELECT p FROM Perfil p WHERE p.nombrePerfil = :nombrePerfil")
    , @NamedQuery(name = "Perfil.findByAbreviacionPerfil", query = "SELECT p FROM Perfil p WHERE p.abreviacionPerfil = :abreviacionPerfil")})
public class Perfil implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_PERFIL")
    private int idPerfil;
    @Basic(optional = false)
    @Column(name = "NOMBRE_PERFIL")
    private String nombrePerfil;
    @Basic(optional = false)
    @Column(name = "ABREVIACION_PERFIL")
    private String abreviacionPerfil;
    @ManyToMany(mappedBy = "perfilCollection")
    private Collection<Competencia> competenciaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "perfil")
    private Collection<Persona> personaCollection;

    public Perfil() {
    }

    public Perfil(int idPerfil) {
        this.idPerfil = idPerfil;
    }

    public Perfil(int idPerfil, String nombrePerfil, String abreviacionPerfil) {
        this.idPerfil = idPerfil;
        this.nombrePerfil = nombrePerfil;
        this.abreviacionPerfil = abreviacionPerfil;
    }

    public int getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(int idPerfil) {
        this.idPerfil = idPerfil;
    }

    public String getNombrePerfil() {
        return nombrePerfil;
    }

    public void setNombrePerfil(String nombrePerfil) {
        this.nombrePerfil = nombrePerfil;
    }

    public String getAbreviacionPerfil() {
        return abreviacionPerfil;
    }

    public void setAbreviacionPerfil(String abreviacionPerfil) {
        this.abreviacionPerfil = abreviacionPerfil;
    }

    @XmlTransient
    public Collection<Competencia> getCompetenciaCollection() {
        return competenciaCollection;
    }

    public void setCompetenciaCollection(Collection<Competencia> competenciaCollection) {
        this.competenciaCollection = competenciaCollection;
    }

    @XmlTransient
    public Collection<Persona> getPersonaCollection() {
        return personaCollection;
    }

    public void setPersonaCollection(Collection<Persona> personaCollection) {
        this.personaCollection = personaCollection;
    }

    
    @Override
    public String toString() {
        return "Entities.Perfil[ idPerfil=" + idPerfil + " ]";
    }
    
}
