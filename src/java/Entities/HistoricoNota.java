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
@Table(name = "HISTORICO_NOTA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HistoricoNota.findAll", query = "SELECT h FROM HistoricoNota h")
    , @NamedQuery(name = "HistoricoNota.findByIdHistorico", query = "SELECT h FROM HistoricoNota h WHERE h.idHistorico = :idHistorico")
    , @NamedQuery(name = "HistoricoNota.findByNotaJefe", query = "SELECT h FROM HistoricoNota h WHERE h.notaJefe = :notaJefe")
    , @NamedQuery(name = "HistoricoNota.findByNotaFuncionario", query = "SELECT h FROM HistoricoNota h WHERE h.notaFuncionario = :notaFuncionario")
    , @NamedQuery(name = "HistoricoNota.findByNotaFinal", query = "SELECT h FROM HistoricoNota h WHERE h.notaFinal = :notaFinal")
    , @NamedQuery(name = "HistoricoNota.findByBrecha", query = "SELECT h FROM HistoricoNota h WHERE h.brecha = :brecha")
    , @NamedQuery(name = "HistoricoNota.findByRut", query = "SELECT h FROM HistoricoNota h WHERE h.rut = :rut")
    , @NamedQuery(name = "HistoricoNota.findByRutJefe", query = "SELECT h FROM HistoricoNota h WHERE h.rutJefe = :rutJefe")})
public class HistoricoNota implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_HISTORICO")
    private int idHistorico;
    @Column(name = "NOTA_JEFE")
    private int notaJefe;
    @Column(name = "NOTA_FUNCIONARIO")
    private int notaFuncionario;
    @Column(name = "NOTA_FINAL")
    private int notaFinal;
    @Column(name = "BRECHA")
    private int brecha;
    @Column(name = "RUT")
    private String rut;
    @Column(name = "RUT_JEFE")
    private String rutJefe;
    @JoinColumn(name = "ID_EVALUACION", referencedColumnName = "ID_EVALUACION")
    @ManyToOne
    private Evaluacion evaluacion;

    public HistoricoNota() {
    }

    public HistoricoNota(int idHistorico) {
        this.idHistorico = idHistorico;
    }

    public int getIdHistorico() {
        return idHistorico;
    }

    public void setIdHistorico(int idHistorico) {
        this.idHistorico = idHistorico;
    }

    public int getNotaJefe() {
        return notaJefe;
    }

    public void setNotaJefe(int notaJefe) {
        this.notaJefe = notaJefe;
    }

    public int getNotaFuncionario() {
        return notaFuncionario;
    }

    public void setNotaFuncionario(int notaFuncionario) {
        this.notaFuncionario = notaFuncionario;
    }

    public int getNotaFinal() {
        return notaFinal;
    }

    public void setNotaFinal(int notaFinal) {
        this.notaFinal = notaFinal;
    }

    public int getBrecha() {
        return brecha;
    }

    public void setBrecha(int brecha) {
        this.brecha = brecha;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getRutJefe() {
        return rutJefe;
    }

    public void setRutJefe(String rutJefe) {
        this.rutJefe = rutJefe;
    }

    public Evaluacion getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(Evaluacion evaluacion) {
        this.evaluacion = evaluacion;
    }

    
    @Override
    public String toString() {
        return "Entities.HistoricoNota[ idHistorico=" + idHistorico + " ]";
    }
    
}
