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
@Table(name = "NIVEL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Nivel.findAll", query = "SELECT n FROM Nivel n")
    , @NamedQuery(name = "Nivel.findByIdNivel", query = "SELECT n FROM Nivel n WHERE n.idNivel = :idNivel")
    , @NamedQuery(name = "Nivel.findByNota", query = "SELECT n FROM Nivel n WHERE n.nota = :nota")
    , @NamedQuery(name = "Nivel.findByNombreNota", query = "SELECT n FROM Nivel n WHERE n.nombreNota = :nombreNota")})
public class Nivel implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_NIVEL")
    private int idNivel;
    @Basic(optional = false)
    @Column(name = "NOTA")
    private int nota;
    @Column(name = "NOMBRE_NOTA")
    private String nombreNota;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nivel")
    private Collection<Competencia> competenciaCollection;

    public Nivel() {
    }

    public Nivel(int idNivel) {
        this.idNivel = idNivel;
    }

    public Nivel(int idNivel, int nota) {
        this.idNivel = idNivel;
        this.nota = nota;
    }

    public int getIdNivel() {
        return idNivel;
    }

    public void setIdNivel(int idNivel) {
        this.idNivel = idNivel;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public String getNombreNota() {
        return nombreNota;
    }

    public void setNombreNota(String nombreNota) {
        this.nombreNota = nombreNota;
    }

    @XmlTransient
    public Collection<Competencia> getCompetenciaCollection() {
        return competenciaCollection;
    }

    public void setCompetenciaCollection(Collection<Competencia> competenciaCollection) {
        this.competenciaCollection = competenciaCollection;
    }


    @Override
    public String toString() {
        return "Entities.Nivel[ idNivel=" + idNivel + " ]";
    }
    
}
