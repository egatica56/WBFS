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
@Table(name = "CUESTIONARIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cuestionario.findAll", query = "SELECT c FROM Cuestionario c")
    , @NamedQuery(name = "Cuestionario.findByIdCuest", query = "SELECT c FROM Cuestionario c WHERE c.idCuest = :idCuest")
    , @NamedQuery(name = "Cuestionario.findByPorcentajeJefe", query = "SELECT c FROM Cuestionario c WHERE c.porcentajeJefe = :porcentajeJefe")
    , @NamedQuery(name = "Cuestionario.findByPorcentajeAutoevaluacion", query = "SELECT c FROM Cuestionario c WHERE c.porcentajeAutoevaluacion = :porcentajeAutoevaluacion")})
public class Cuestionario implements Serializable {

    @Basic(optional = false)
    @Column(name = "PORCENTAJE_JEFE")
    private int porcentajeJefe;
    @Basic(optional = false)
    @Column(name = "PORCENTAJE_AUTOEVALUACION")
    private int porcentajeAutoevaluacion;
    @JoinColumn(name = "ID_ESTADO", referencedColumnName = "ID_ESTADO")
    @ManyToOne
    private ControlEstados controlEstados;

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_CUEST")
    private int idCuest;
    @JoinColumn(name = "ID_COMP", referencedColumnName = "ID_COMP")
    @ManyToOne(optional = false)
    private Competencia competencia;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cuestionario")
    private Collection<Pregunta> preguntaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cuestionario")
    private Collection<CuestAsig> cuestAsigCollection;

    public Cuestionario() {
    }

    public Cuestionario(int idCuest) {
        this.idCuest = idCuest;
    }

    public Cuestionario(int idCuest, int porcentajeJefe, int porcentajeAutoevaluacion) {
        this.idCuest = idCuest;
        this.porcentajeJefe = porcentajeJefe;
        this.porcentajeAutoevaluacion = porcentajeAutoevaluacion;
    }

    public int getIdCuest() {
        return idCuest;
    }

    public void setIdCuest(int idCuest) {
        this.idCuest = idCuest;
    }

    public int getPorcentajeJefe() {
        return porcentajeJefe;
    }

    public void setPorcentajeJefe(int porcentajeJefe) {
        this.porcentajeJefe = porcentajeJefe;
    }

    public int getPorcentajeAutoevaluacion() {
        return porcentajeAutoevaluacion;
    }

    public void setPorcentajeAutoevaluacion(int porcentajeAutoevaluacion) {
        this.porcentajeAutoevaluacion = porcentajeAutoevaluacion;
    }

    public Competencia getCompetencia() {
        return competencia;
    }

    public void setCompetencia(Competencia competencia) {
        this.competencia = competencia;
    }

    @XmlTransient
    public Collection<Pregunta> getPreguntaCollection() {
        return preguntaCollection;
    }

    public void setPreguntaCollection(Collection<Pregunta> preguntaCollection) {
        this.preguntaCollection = preguntaCollection;
    }

    @XmlTransient
    public Collection<CuestAsig> getCuestAsigCollection() {
        return cuestAsigCollection;
    }

    public void setCuestAsigCollection(Collection<CuestAsig> cuestAsigCollection) {
        this.cuestAsigCollection = cuestAsigCollection;
    }


    @Override
    public String toString() {
        return "Entities.Cuestionario[ idCuest=" + idCuest + " ]";
    }

   

    public ControlEstados getControlEstados() {
        return controlEstados;
    }

    public void setControlEstados(ControlEstados controlEstados) {
        this.controlEstados = controlEstados;
    }

    
}
