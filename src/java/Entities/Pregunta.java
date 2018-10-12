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
@Table(name = "PREGUNTA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pregunta.findAll", query = "SELECT p FROM Pregunta p")
    , @NamedQuery(name = "Pregunta.findByIdPregunta", query = "SELECT p FROM Pregunta p WHERE p.idPregunta = :idPregunta")
    , @NamedQuery(name = "Pregunta.findByTextoPregunta", query = "SELECT p FROM Pregunta p WHERE p.textoPregunta = :textoPregunta")
    , @NamedQuery(name = "Pregunta.findByPorcentajePregunta", query = "SELECT p FROM Pregunta p WHERE p.porcentajePregunta = :porcentajePregunta")
    , @NamedQuery(name = "Pregunta.findByEsCorrecta", query = "SELECT p FROM Pregunta p WHERE p.esCorrecta = :esCorrecta")})
public class Pregunta implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_PREGUNTA")
    private int idPregunta;
    @Basic(optional = false)
    @Column(name = "TEXTO_PREGUNTA")
    private String textoPregunta;
    @Basic(optional = false)
    @Column(name = "PORCENTAJE_PREGUNTA")
    private int porcentajePregunta;
    @Basic(optional = false)
    @Column(name = "ES_CORRECTA")
    private String esCorrecta;
    @JoinColumn(name = "ID_CUEST", referencedColumnName = "ID_CUEST")
    @ManyToOne(optional = false)
    private Cuestionario cuestionario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pregunta")
    private Collection<OpcionRespuesta> opcionRespuestaCollection;

    public Pregunta() {
    }

    public Pregunta(int idPregunta) {
        this.idPregunta = idPregunta;
    }

    public Pregunta(int idPregunta, String textoPregunta, int porcentajePregunta, String esCorrecta) {
        this.idPregunta = idPregunta;
        this.textoPregunta = textoPregunta;
        this.porcentajePregunta = porcentajePregunta;
        this.esCorrecta = esCorrecta;
    }

    public int getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(int idPregunta) {
        this.idPregunta = idPregunta;
    }

    public String getTextoPregunta() {
        return textoPregunta;
    }

    public void setTextoPregunta(String textoPregunta) {
        this.textoPregunta = textoPregunta;
    }

    public int getPorcentajePregunta() {
        return porcentajePregunta;
    }

    public void setPorcentajePregunta(int porcentajePregunta) {
        this.porcentajePregunta = porcentajePregunta;
    }

    public String getEsCorrecta() {
        return esCorrecta;
    }

    public void setEsCorrecta(String esCorrecta) {
        this.esCorrecta = esCorrecta;
    }

    public Cuestionario getCuestionario() {
        return cuestionario;
    }

    public void setCuestionario(Cuestionario cuestionario) {
        this.cuestionario = cuestionario;
    }

    @XmlTransient
    public Collection<OpcionRespuesta> getOpcionRespuestaCollection() {
        return opcionRespuestaCollection;
    }

    public void setOpcionRespuestaCollection(Collection<OpcionRespuesta> opcionRespuestaCollection) {
        this.opcionRespuestaCollection = opcionRespuestaCollection;
    }

    

    @Override
    public String toString() {
        return "Entities.Pregunta[ idPregunta=" + idPregunta + " ]";
    }
    
}
