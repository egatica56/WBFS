/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author EduardoGatica
 */
@Entity
@Table(name = "OBSERVACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Observacion.findAll", query = "SELECT o FROM Observacion o")
    , @NamedQuery(name = "Observacion.findByIdObservacion", query = "SELECT o FROM Observacion o WHERE o.idObservacion = :idObservacion")
    , @NamedQuery(name = "Observacion.findByMensajePuntajeSuperior", query = "SELECT o FROM Observacion o WHERE o.mensajePuntajeSuperior = :mensajePuntajeSuperior")
    , @NamedQuery(name = "Observacion.findByMensajePuntajeInferior", query = "SELECT o FROM Observacion o WHERE o.mensajePuntajeInferior = :mensajePuntajeInferior")})
public class Observacion implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_OBSERVACION")
    private int idObservacion;
    @Basic(optional = false)
    @Column(name = "MENSAJE_PUNTAJE_SUPERIOR")
    private String mensajePuntajeSuperior;
    @Basic(optional = false)
    @Column(name = "MENSAJE_PUNTAJE_INFERIOR")
    private String mensajePuntajeInferior;
    @JoinColumn(name = "ID_COMP", referencedColumnName = "ID_COMP")
    @ManyToOne(optional = false)
    private Competencia competencia;

    public Observacion() {
    }

    public Observacion(int idObservacion) {
        this.idObservacion = idObservacion;
    }

    public Observacion(int idObservacion, String mensajePuntajeSuperior, String mensajePuntajeInferior) {
        this.idObservacion = idObservacion;
        this.mensajePuntajeSuperior = mensajePuntajeSuperior;
        this.mensajePuntajeInferior = mensajePuntajeInferior;
    }

    public int getIdObservacion() {
        return idObservacion;
    }

    public void setIdObservacion(int idObservacion) {
        this.idObservacion = idObservacion;
    }

    public String getMensajePuntajeSuperior() {
        return mensajePuntajeSuperior;
    }

    public void setMensajePuntajeSuperior(String mensajePuntajeSuperior) {
        this.mensajePuntajeSuperior = mensajePuntajeSuperior;
    }

    public String getMensajePuntajeInferior() {
        return mensajePuntajeInferior;
    }

    public void setMensajePuntajeInferior(String mensajePuntajeInferior) {
        this.mensajePuntajeInferior = mensajePuntajeInferior;
    }

    public Competencia getCompetencia() {
        return competencia;
    }

    public void setCompetencia(Competencia competencia) {
        this.competencia = competencia;
    }

    

    @Override
    public String toString() {
        return "Entities.Observacion[ idObservacion=" + idObservacion + " ]";
    }
    
}
