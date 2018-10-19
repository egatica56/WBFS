/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.util.Collection;
import java.lang.String;
import javax.persistence.Basic;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author EduardoGatica
 */
@Entity
@Table(name = "EVALUACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Evaluacion.findAll", query = "SELECT e FROM Evaluacion e")
    , @NamedQuery(name = "Evaluacion.findByIdEvaluacion", query = "SELECT e FROM Evaluacion e WHERE e.idEvaluacion = :idEvaluacion")
    , @NamedQuery(name = "Evaluacion.findByRutJefe", query = "SELECT e FROM Evaluacion e WHERE e.rutJefe = :rutJefe")
    , @NamedQuery(name = "Evaluacion.findByNotaEvaluacion", query = "SELECT e FROM Evaluacion e WHERE e.notaEvaluacion = :notaEvaluacion")
    , @NamedQuery(name = "Evaluacion.findByFechaEvaluacion", query = "SELECT e FROM Evaluacion e WHERE e.fechaEvaluacion = :fechaEvaluacion")})
public class Evaluacion implements Serializable {

    @Basic(optional = false)
    @Column(name = "NOTA_EVALUACION")
    private int notaEvaluacion;
    @OneToMany(mappedBy = "evaluacion")
    private Collection<HistoricoNota> historicoNotaCollection;

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_EVALUACION")
    private int idEvaluacion;
    @Basic(optional = false)
    @Column(name = "RUT_JEFE")
    private String rutJefe;
    @Basic(optional = false)
    @Column(name = "FECHA_EVALUACION")
    @Temporal(TemporalType.TIMESTAMP)
    private String fechaEvaluacion;
    @JoinColumn(name = "ID_CUEST_ASIG", referencedColumnName = "ID_CUEST_ASIG")
    @ManyToOne(optional = false)
    private CuestAsig cuestAsig;
    @JoinColumn(name = "RUT_PERSONA", referencedColumnName = "RUT_PERSONA")
    @ManyToOne(optional = false)
    private Persona persona;

    public Evaluacion() {
    }

    public Evaluacion(int idEvaluacion) {
        this.idEvaluacion = idEvaluacion;
    }

    public Evaluacion(int idEvaluacion, String rutJefe, int notaEvaluacion, String fechaEvaluacion) {
        this.idEvaluacion = idEvaluacion;
        this.rutJefe = rutJefe;
        this.notaEvaluacion = notaEvaluacion;
        this.fechaEvaluacion = fechaEvaluacion;
    }

    public int getIdEvaluacion() {
        return idEvaluacion;
    }

    public void setIdEvaluacion(int idEvaluacion) {
        this.idEvaluacion = idEvaluacion;
    }

    public String getRutJefe() {
        return rutJefe;
    }

    public void setRutJefe(String rutJefe) {
        this.rutJefe = rutJefe;
    }

    public int getNotaEvaluacion() {
        return notaEvaluacion;
    }

    public void setNotaEvaluacion(int notaEvaluacion) {
        this.notaEvaluacion = notaEvaluacion;
    }

    public String getFechaEvaluacion() {
        return fechaEvaluacion;
    }

    public void setFechaEvaluacion(String fechaEvaluacion) {
        this.fechaEvaluacion = fechaEvaluacion;
    }

    public CuestAsig getCuestAsig() {
        return cuestAsig;
    }

    public void setCuestAsig(CuestAsig cuestAsig) {
        this.cuestAsig = cuestAsig;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    
    @Override
    public String toString() {
        return "Entities.Evaluacion[ idEvaluacion=" + idEvaluacion + " ]";
    }

    
}
