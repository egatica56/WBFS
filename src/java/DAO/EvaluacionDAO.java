/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entities.Evaluacion;
import Entities.Persona;
import Entities.TipoUsuario;
import Entities.Usuario;
import Util.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import jdk.nashorn.internal.codegen.types.Type;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author EduardoGatica
 */
public class EvaluacionDAO {

    public EvaluacionDAO() {
    }
    private Connection conexion;

    public boolean agregarEvaluacion(Evaluacion evaluacion) throws SQLException {

        try {
            //Cuestionario cu = new Cuestionario();
            //Competencia com = new Competencia();
            this.conexion = new Conexion().obtenerConexion();
            String llamada = "{call PKG_EVALUACION_SP_AGREGAR_EVALUACION(?,?,?,?,?)}";
            CallableStatement cstmt = conexion.prepareCall(llamada);

            // cstmt.setInt(1, cuestionario.getIdCuest());
            cstmt.setString(1, evaluacion.getRutJefe());
            cstmt.setString(2, evaluacion.getFechaEvaluacion());
            cstmt.setInt(3, evaluacion.getCuestAsig().getIdCuestAsig());
            cstmt.setString(4, evaluacion.getPersona().getRutPersona());
            cstmt.registerOutParameter(5, Types.INTEGER);
            //ejecutamos la llamada al procedimiento almacenado
            cstmt.execute();
            int indice = cstmt.getInt(5);
            
            return true;

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;

        } finally {
            this.conexion.close();

        }

    }

    public List<Usuario> listarPersonasPorJefe(String rut) throws SQLException {
        List<Usuario> listado = new ArrayList<Usuario>();

        try {
            this.conexion = new Conexion().obtenerConexion();
            String llamada = "{call SP_LISTAR_PERSONA_JEFE(?,?)}";
            CallableStatement cstmt = conexion.prepareCall(llamada);

            cstmt.setString(1, rut);
            cstmt.registerOutParameter(2, OracleTypes.CURSOR);

            //ejecutamos la llamada al procedimiento almacenado
            cstmt.execute();

            ResultSet rs = (ResultSet) cstmt.getObject(2);

            while (rs.next()) {
                TipoUsuario tipoUsuario = new TipoUsuario();
                Persona persona = new Persona();
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("ID_USUARIO"));
                usuario.setUsername(rs.getString("USERNAME"));
                usuario.setPassword(rs.getString("PASSWORD"));
                tipoUsuario.setIdTipoUsuario(rs.getInt("ID_TIPO_USUARIO"));
                tipoUsuario.setNombreTipoUsuario(rs.getString("NOMBRE_TIPO_USUARIO"));
                persona.setNombrePersona(rs.getString("NOMBRE_PERSONA"));
                persona.setRutPersona(rs.getString("RUT_PERSONA"));
                persona.setApellidoPaterno(rs.getString("APELLIDO_PATERNO"));
                tipoUsuario.setNombreTipoUsuario(rs.getString("NOMBRE_TIPO_USUARIO"));
                usuario.setTipoUsuario(tipoUsuario);
                usuario.setPersona(persona);
                listado.add(usuario);
                System.out.println("INFO: " + tipoUsuario.getIdTipoUsuario());

            }
            for (Usuario u : listado) {
                System.out.println("usuario: " + u.getTipoUsuario().getIdTipoUsuario());

            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            this.conexion.close();

        }
        return listado;

    }

}
