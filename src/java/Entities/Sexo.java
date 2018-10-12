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
@Table(name = "SEXO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sexo.findAll", query = "SELECT s FROM Sexo s")
    , @NamedQuery(name = "Sexo.findByIdSexo", query = "SELECT s FROM Sexo s WHERE s.idSexo = :idSexo")
    , @NamedQuery(name = "Sexo.findByNombreSexo", query = "SELECT s FROM Sexo s WHERE s.nombreSexo = :nombreSexo")})
public class Sexo implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_SEXO")
    private int idSexo;
    @Basic(optional = false)
    @Column(name = "NOMBRE_SEXO")
    private String nombreSexo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sexo")
    private Collection<Persona> personaCollection;

    public Sexo() {
    }

    public Sexo(int idSexo) {
        this.idSexo = idSexo;
    }

    public Sexo(int idSexo, String nombreSexo) {
        this.idSexo = idSexo;
        this.nombreSexo = nombreSexo;
    }

    public int getIdSexo() {
        return idSexo;
    }

    public void setIdSexo(int idSexo) {
        this.idSexo = idSexo;
    }

    public String getNombreSexo() {
        return nombreSexo;
    }

    public void setNombreSexo(String nombreSexo) {
        this.nombreSexo = nombreSexo;
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
        return "Entities.Sexo[ idSexo=" + idSexo + " ]";
    }
    
}
