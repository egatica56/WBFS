/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author EduardoGatica
 */
@Entity
@Table(name = "CUEST_ASIG")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CuestAsig.findAll", query = "SELECT c FROM CuestAsig c")
    , @NamedQuery(name = "CuestAsig.findByIdCuestAsig", query = "SELECT c FROM CuestAsig c WHERE c.idCuestAsig = :idCuestAsig")
    , @NamedQuery(name = "CuestAsig.findByRutJefe", query = "SELECT c FROM CuestAsig c WHERE c.rutJefe = :rutJefe")
    , @NamedQuery(name = "CuestAsig.findByFechaInicio", query = "SELECT c FROM CuestAsig c WHERE c.fechaInicio = :fechaInicio")
    , @NamedQuery(name = "CuestAsig.findByFechaTermino", query = "SELECT c FROM CuestAsig c WHERE c.fechaTermino = :fechaTermino")
    , @NamedQuery(name = "CuestAsig.findByEstadoCuestionarioAsig", query = "SELECT c FROM CuestAsig c WHERE c.estadoCuestionarioAsig = :estadoCuestionarioAsig")})
public class CuestAsig implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_CUEST_ASIG")
    private int idCuestAsig;
    @Basic(optional = false)
    @Column(name = "RUT_JEFE")
    private String rutJefe;
    @Basic(optional = false)
    @Column(name = "FECHA_INICIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;
    @Basic(optional = false)
    @Column(name = "FECHA_TERMINO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaTermino;
    @Basic(optional = false)
    @Column(name = "ESTADO_CUESTIONARIO_ASIG")
    private String estadoCuestionarioAsig;
    @JoinColumn(name = "ID_CUEST", referencedColumnName = "ID_CUEST")
    @ManyToOne(optional = false)
    private Cuestionario cuestionario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cuestAsig")
    private Collection<Evaluacion> evaluacionCollection;

    public CuestAsig() {
    }

    public CuestAsig(int idCuestAsig) {
        this.idCuestAsig = idCuestAsig;
    }

    public CuestAsig(int idCuestAsig, String rutJefe, Date fechaInicio, Date fechaTermino, String estadoCuestionarioAsig) {
        this.idCuestAsig = idCuestAsig;
        this.rutJefe = rutJefe;
        this.fechaInicio = fechaInicio;
        this.fechaTermino = fechaTermino;
        this.estadoCuestionarioAsig = estadoCuestionarioAsig;
    }

    public int getIdCuestAsig() {
        return idCuestAsig;
    }

    public void setIdCuestAsig(int idCuestAsig) {
        this.idCuestAsig = idCuestAsig;
    }

    public String getRutJefe() {
        return rutJefe;
    }

    public void setRutJefe(String rutJefe) {
        this.rutJefe = rutJefe;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaTermino() {
        return fechaTermino;
    }

    public void setFechaTermino(Date fechaTermino) {
        this.fechaTermino = fechaTermino;
    }

    public String getEstadoCuestionarioAsig() {
        return estadoCuestionarioAsig;
    }

    public void setEstadoCuestionarioAsig(String estadoCuestionarioAsig) {
        this.estadoCuestionarioAsig = estadoCuestionarioAsig;
    }

    public Cuestionario getCuestionario() {
        return cuestionario;
    }

    public void setCuestionario(Cuestionario cuestionario) {
        this.cuestionario = cuestionario;
    }

    @XmlTransient
    public Collection<Evaluacion> getEvaluacionCollection() {
        return evaluacionCollection;
    }

    public void setEvaluacionCollection(Collection<Evaluacion> evaluacionCollection) {
        this.evaluacionCollection = evaluacionCollection;
    }


    @Override
    public String toString() {
        return "Entities.CuestAsig[ idCuestAsig=" + idCuestAsig + " ]";
    }
    
}
