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
@Table(name = "PERSONA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Persona.findAll", query = "SELECT p FROM Persona p")
    , @NamedQuery(name = "Persona.findByRutPersona", query = "SELECT p FROM Persona p WHERE p.rutPersona = :rutPersona")
    , @NamedQuery(name = "Persona.findByNombrePersona", query = "SELECT p FROM Persona p WHERE p.nombrePersona = :nombrePersona")
    , @NamedQuery(name = "Persona.findByApellidoPaterno", query = "SELECT p FROM Persona p WHERE p.apellidoPaterno = :apellidoPaterno")
    , @NamedQuery(name = "Persona.findByApellidoMaterno", query = "SELECT p FROM Persona p WHERE p.apellidoMaterno = :apellidoMaterno")
    , @NamedQuery(name = "Persona.findByDireccionPersona", query = "SELECT p FROM Persona p WHERE p.direccionPersona = :direccionPersona")
    , @NamedQuery(name = "Persona.findByFechaNacimiento", query = "SELECT p FROM Persona p WHERE p.fechaNacimiento = :fechaNacimiento")
    , @NamedQuery(name = "Persona.findByTelefonoFijo", query = "SELECT p FROM Persona p WHERE p.telefonoFijo = :telefonoFijo")
    , @NamedQuery(name = "Persona.findByTelefonoCelular", query = "SELECT p FROM Persona p WHERE p.telefonoCelular = :telefonoCelular")
    , @NamedQuery(name = "Persona.findByEmailPersona", query = "SELECT p FROM Persona p WHERE p.emailPersona = :emailPersona")
    , @NamedQuery(name = "Persona.findBySexoPersona", query = "SELECT p FROM Persona p WHERE p.sexoPersona = :sexoPersona")
    , @NamedQuery(name = "Persona.findByCargoPersona", query = "SELECT p FROM Persona p WHERE p.cargoPersona = :cargoPersona")
    , @NamedQuery(name = "Persona.findByAreaPersona", query = "SELECT p FROM Persona p WHERE p.areaPersona = :areaPersona")})
public class Persona implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "RUT_PERSONA")
    private String rutPersona;
    @Basic(optional = false)
    @Column(name = "NOMBRE_PERSONA")
    private String nombrePersona;
    @Basic(optional = false)
    @Column(name = "APELLIDO_PATERNO")
    private String apellidoPaterno;
    @Basic(optional = false)
    @Column(name = "APELLIDO_MATERNO")
    private String apellidoMaterno;
    @Basic(optional = false)
    @Column(name = "DIRECCION_PERSONA")
    private String direccionPersona;
    @Basic(optional = false)
    @Column(name = "FECHA_NACIMIENTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaNacimiento;
    @Basic(optional = false)
    @Column(name = "TELEFONO_FIJO")
    private String telefonoFijo;
    @Basic(optional = false)
    @Column(name = "TELEFONO_CELULAR")
    private String telefonoCelular;
    @Basic(optional = false)
    @Column(name = "EMAIL_PERSONA")
    private String emailPersona;
    @Basic(optional = false)
    @Column(name = "SEXO_PERSONA")
    private String sexoPersona;
    @Basic(optional = false)
    @Column(name = "CARGO_PERSONA")
    private String cargoPersona;
    @Basic(optional = false)
    @Column(name = "AREA_PERSONA")
    private String areaPersona;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "persona")
    private Collection<Evaluacion> evaluacionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "persona")
    private Collection<Usuario> usuarioCollection;
    @JoinColumn(name = "ID_COMUNA", referencedColumnName = "ID_COMUNA")
    @ManyToOne(optional = false)
    private Comuna comuna;
    @JoinColumn(name = "ID_PERFIL", referencedColumnName = "ID_PERFIL")
    @ManyToOne(optional = false)
    private Perfil perfil;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "persona")
    private Collection<Persona> personaCollection;
    @JoinColumn(name = "RUT_JEFE", referencedColumnName = "RUT_PERSONA")
    @ManyToOne(optional = false)
    private Persona persona;
    @JoinColumn(name = "ID_SEXO", referencedColumnName = "ID_SEXO")
    @ManyToOne(optional = false)
    private Sexo sexo;

    public Persona() {
    }

    public Persona(String rutPersona) {
        this.rutPersona = rutPersona;
    }

    public Persona(String rutPersona, String nombrePersona, String apellidoPaterno, String apellidoMaterno, String direccionPersona, Date fechaNacimiento, String telefonoFijo, String telefonoCelular, String emailPersona, String sexoPersona, String cargoPersona, String areaPersona) {
        this.rutPersona = rutPersona;
        this.nombrePersona = nombrePersona;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.direccionPersona = direccionPersona;
        this.fechaNacimiento = fechaNacimiento;
        this.telefonoFijo = telefonoFijo;
        this.telefonoCelular = telefonoCelular;
        this.emailPersona = emailPersona;
        this.sexoPersona = sexoPersona;
        this.cargoPersona = cargoPersona;
        this.areaPersona = areaPersona;
    }

    public String getRutPersona() {
        return rutPersona;
    }

    public void setRutPersona(String rutPersona) {
        this.rutPersona = rutPersona;
    }

    public String getNombrePersona() {
        return nombrePersona;
    }

    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getDireccionPersona() {
        return direccionPersona;
    }

    public void setDireccionPersona(String direccionPersona) {
        this.direccionPersona = direccionPersona;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTelefonoFijo() {
        return telefonoFijo;
    }

    public void setTelefonoFijo(String telefonoFijo) {
        this.telefonoFijo = telefonoFijo;
    }

    public String getTelefonoCelular() {
        return telefonoCelular;
    }

    public void setTelefonoCelular(String telefonoCelular) {
        this.telefonoCelular = telefonoCelular;
    }

    public String getEmailPersona() {
        return emailPersona;
    }

    public void setEmailPersona(String emailPersona) {
        this.emailPersona = emailPersona;
    }

    public String getSexoPersona() {
        return sexoPersona;
    }

    public void setSexoPersona(String sexoPersona) {
        this.sexoPersona = sexoPersona;
    }

    public String getCargoPersona() {
        return cargoPersona;
    }

    public void setCargoPersona(String cargoPersona) {
        this.cargoPersona = cargoPersona;
    }

    public String getAreaPersona() {
        return areaPersona;
    }

    public void setAreaPersona(String areaPersona) {
        this.areaPersona = areaPersona;
    }

    @XmlTransient
    public Collection<Evaluacion> getEvaluacionCollection() {
        return evaluacionCollection;
    }

    public void setEvaluacionCollection(Collection<Evaluacion> evaluacionCollection) {
        this.evaluacionCollection = evaluacionCollection;
    }

    @XmlTransient
    public Collection<Usuario> getUsuarioCollection() {
        return usuarioCollection;
    }

    public void setUsuarioCollection(Collection<Usuario> usuarioCollection) {
        this.usuarioCollection = usuarioCollection;
    }

    public Comuna getComuna() {
        return comuna;
    }

    public void setComuna(Comuna comuna) {
        this.comuna = comuna;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    @XmlTransient
    public Collection<Persona> getPersonaCollection() {
        return personaCollection;
    }

    public void setPersonaCollection(Collection<Persona> personaCollection) {
        this.personaCollection = personaCollection;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rutPersona != null ? rutPersona.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Persona)) {
            return false;
        }
        Persona other = (Persona) object;
        if ((this.rutPersona == null && other.rutPersona != null) || (this.rutPersona != null && !this.rutPersona.equals(other.rutPersona))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Persona[ rutPersona=" + rutPersona + " ]";
    }
    
}
