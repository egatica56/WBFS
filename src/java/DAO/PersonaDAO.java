/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entities.ControlEstados;
import Entities.Persona;
import Entities.TipoUsuario;
import Entities.Usuario;
import Util.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author EduardoGatica
 */
public class PersonaDAO {

    public PersonaDAO() {
    }
    private Connection conexion;

    public Persona buscarPersona(String rut) throws SQLException {

        Persona persona = new Persona();

        try {
            this.conexion = new Conexion().obtenerConexion();
            String llamada = "{call PKG_PERSONA_1.SP_BUSCAR_PERSONA(?,?)}";
            CallableStatement cstmt = conexion.prepareCall(llamada);
            cstmt.setString(1, rut);
            cstmt.registerOutParameter(2, OracleTypes.CURSOR);

            //ejecutamos la llamada al procedimiento almacenado
            cstmt.execute();

            ResultSet rs = (ResultSet) cstmt.getObject(2);

            while (rs.next()) {
                persona.setNombrePersona(rs.getString("NOMBRE_PERSONA"));
                persona.setRutPersona(rs.getString("RUT_PERSONA"));
                persona.setApellidoPaterno(rs.getString("APELLIDO_PATERNO"));
                persona.setApellidoMaterno(rs.getString("APELLIDO_MATERNO"));
                 persona.setEmailPersona(rs.getString("EMAIL_PERSONA"));
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            this.conexion.close();

        }
        return persona;

    }

}
