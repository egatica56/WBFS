/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author EduardoGatica
 */
@Entity
@Table(name = "LOGS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Logs.findAll", query = "SELECT l FROM Logs l")
    , @NamedQuery(name = "Logs.findByIdLog", query = "SELECT l FROM Logs l WHERE l.idLog = :idLog")
    , @NamedQuery(name = "Logs.findByFechaIngeso", query = "SELECT l FROM Logs l WHERE l.fechaIngeso = :fechaIngeso")
    , @NamedQuery(name = "Logs.findByTiempoSesion", query = "SELECT l FROM Logs l WHERE l.tiempoSesion = :tiempoSesion")})
public class Logs implements Serializable {

    @Basic(optional = false)
    @Column(name = "FECHA_INGESO")
    @Temporal(TemporalType.TIMESTAMP)
    private String fechaIngeso;
    @Basic(optional = false)
    @Column(name = "TIEMPO_SESION")
    @Temporal(TemporalType.TIMESTAMP)
    private String tiempoSesion;

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_LOG")
    private int idLog;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne(optional = false)
    private Usuario usuario;

    public Logs() {
    }

    public Logs(int idLog) {
        this.idLog = idLog;
    }

    public Logs(int idLog, String fechaIngeso, String tiempoSesion) {
        this.idLog = idLog;
        this.fechaIngeso = fechaIngeso;
        this.tiempoSesion = tiempoSesion;
    }

    public int getIdLog() {
        return idLog;
    }

    public void setIdLog(int idLog) {
        this.idLog = idLog;
    }

    public String getFechaIngeso() {
        return fechaIngeso;
    }

    public void setFechaIngeso(String fechaIngeso) {
        this.fechaIngeso = fechaIngeso;
    }

    public String getTiempoSesion() {
        return tiempoSesion;
    }

    public void setTiempoSesion(String tiempoSesion) {
        this.tiempoSesion = tiempoSesion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }


    @Override
    public String toString() {
        return "Entities.Logs[ idLog=" + idLog + " ]";
    }

    
}
