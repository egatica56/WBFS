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
@Table(name = "OPCION_RESPUESTA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OpcionRespuesta.findAll", query = "SELECT o FROM OpcionRespuesta o")
    , @NamedQuery(name = "OpcionRespuesta.findByIdOpcionRespuesta", query = "SELECT o FROM OpcionRespuesta o WHERE o.idOpcionRespuesta = :idOpcionRespuesta")
    , @NamedQuery(name = "OpcionRespuesta.findByPorcentajeRespuesta", query = "SELECT o FROM OpcionRespuesta o WHERE o.porcentajeRespuesta = :porcentajeRespuesta")
    , @NamedQuery(name = "OpcionRespuesta.findByTextoRespuesta", query = "SELECT o FROM OpcionRespuesta o WHERE o.textoRespuesta = :textoRespuesta")})
public class OpcionRespuesta implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_OPCION_RESPUESTA")
    private int idOpcionRespuesta;
    @Basic(optional = false)
    @Column(name = "PORCENTAJE_RESPUESTA")
    private int porcentajeRespuesta;
    @Basic(optional = false)
    @Column(name = "TEXTO_RESPUESTA")
    private String textoRespuesta;
    @JoinColumn(name = "ID_PREGUNTA", referencedColumnName = "ID_PREGUNTA")
    @ManyToOne(optional = false)
    private Pregunta pregunta;

    public OpcionRespuesta() {
    }

    public OpcionRespuesta(int idOpcionRespuesta) {
        this.idOpcionRespuesta = idOpcionRespuesta;
    }

    public OpcionRespuesta(int idOpcionRespuesta, int porcentajeRespuesta, String textoRespuesta) {
        this.idOpcionRespuesta = idOpcionRespuesta;
        this.porcentajeRespuesta = porcentajeRespuesta;
        this.textoRespuesta = textoRespuesta;
    }

    public int getIdOpcionRespuesta() {
        return idOpcionRespuesta;
    }

    public void setIdOpcionRespuesta(int idOpcionRespuesta) {
        this.idOpcionRespuesta = idOpcionRespuesta;
    }

    public int getPorcentajeRespuesta() {
        return porcentajeRespuesta;
    }

    public void setPorcentajeRespuesta(int porcentajeRespuesta) {
        this.porcentajeRespuesta = porcentajeRespuesta;
    }

    public String getTextoRespuesta() {
        return textoRespuesta;
    }

    public void setTextoRespuesta(String textoRespuesta) {
        this.textoRespuesta = textoRespuesta;
    }

    public Pregunta getPregunta() {
        return pregunta;
    }

    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }

 

    @Override
    public String toString() {
        return "Entities.OpcionRespuesta[ idOpcionRespuesta=" + idOpcionRespuesta + " ]";
    }
    
}
