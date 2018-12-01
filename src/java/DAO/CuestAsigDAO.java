/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entities.Competencia;
import Entities.ControlEstados;
import Entities.CuestAsig;
import Entities.Cuestionario;
import Entities.OpcionRespuesta;
import Entities.Persona;
import Entities.Pregunta;
import Entities.Usuario;
import Util.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author EduardoGatica
 */
public class CuestAsigDAO {

    private Connection conexion;

    public CuestAsigDAO() {

    }

    public boolean agregarCuestAsig(CuestAsig cuestAsig) throws SQLException {

        try {
            //Cuestionario cu = new Cuestionario();
            //Competencia com = new Competencia();
            this.conexion = new Conexion().obtenerConexion();
            String llamada = "{call PKG_CUESTIONARIO_ASIGNADO.SP_ASIGNAR_CUEST(?,?,?,?,?)}";
            CallableStatement cstmt = conexion.prepareCall(llamada);

            /*java.util.Date fecha1=cuestAsig.getFechaInicio();         
            java.util.Date fecha2=cuestAsig.getFechaTermino();
            
              
            java.util.Date utilDate = fecha1;
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            System.out.println("utilDate Fecha Inicio:" + utilDate);
            System.out.println("sqlDate  Fecha Inicio:" + sqlDate);
            
            java.util.Date utilDate2 = fecha2;
            java.sql.Date sqlDate2 = new java.sql.Date(utilDate2.getTime());
            System.out.println("utilDate Fecha Termino:" + utilDate2);
            System.out.println("sqlDate  Fecha Termino:" + sqlDate2);*/
            cstmt.setString(1, cuestAsig.getRutJefe());
            cstmt.setString(2, cuestAsig.getFechaInicio());
            cstmt.setString(3, cuestAsig.getFechaTermino());
            cstmt.setString(4, cuestAsig.getEstadoCuestionarioAsig());
            cstmt.setInt(5, cuestAsig.getCuestionario().getIdCuest());

            //ejecutamos la llamada al procedimiento almacenado
            cstmt.execute();
            return true;

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;

        } finally {
            this.conexion.close();

        }

    }

    public List<CuestAsig> listar_cuest_asig() throws SQLException {

        List<CuestAsig> listado = new ArrayList<CuestAsig>();

        try {
            this.conexion = new Conexion().obtenerConexion();
            String llamada = "{call PKG_CUESTIONARIO_ASIGNADO.SP_LISTAR_CUEST_ASIG(?)}";
            CallableStatement cstmt = conexion.prepareCall(llamada);

            cstmt.registerOutParameter(1, OracleTypes.CURSOR);

            //ejecutamos la llamada al procedimiento almacenado
            cstmt.execute();

            ResultSet rs = (ResultSet) cstmt.getObject(1);

            while (rs.next()) {
                CuestAsig cuestAsig = new CuestAsig();
                Usuario usuario = new Usuario();
                Persona persona = new Persona();
                Cuestionario cuestionario = new Cuestionario();
                cuestionario.setIdCuest(rs.getInt("ID_CUEST"));
                persona.setNombrePersona("NOMBRE_PERSONA");
                persona.setApellidoPaterno("APELLIDO_PATERNO");
                persona.setRutPersona("RUT_JEFE");
                usuario.setPersona(persona);
                usuario.setUsername("USERNAME");
                cuestAsig.setRutJefe(persona.getRutPersona());

                cuestAsig.setIdCuestAsig(rs.getInt("ID_CUEST_ASIG"));
                cuestAsig.setRutJefe(rs.getString("USERNAME"));
                //cuestAsig.setFechaInicio (new java.util.Date("FECHA_INICIO"));
                //cuestAsig.setFechaTermino(new java.util.Date("FECHA_TERMINO"));
                cuestAsig.setCuestionario(cuestionario);

                listado.add(cuestAsig);

            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            this.conexion.close();

        }
        return listado;

    }

    public CuestAsig buscarCuestAsig(int idCuestAsig) throws SQLException {
        CuestAsig cuestAsig = new CuestAsig();
        Cuestionario cuestionario = new Cuestionario();
        Competencia competencia = new Competencia();
        Pregunta pregunta = new Pregunta();

        try {
            this.conexion = new Conexion().obtenerConexion();
            String llamada = "{call PKG_CUESTIONARIO_ASIGNADO.SP_BUSCAR(?,?)}";
            CallableStatement cstmt = conexion.prepareCall(llamada);
            cstmt.setInt(1, idCuestAsig);
            cstmt.registerOutParameter(2, OracleTypes.CURSOR);

            //ejecutamos la llamada al procedimiento almacenado
            cstmt.execute();

            ResultSet rs = (ResultSet) cstmt.getObject(2);

            while (rs.next()) {

                competencia.setIdComp(rs.getInt("ID_COMP"));
                competencia.setNombreCompetencia(rs.getString("NOMBRE_COMPETENCIA"));
                cuestionario.setCompetencia(competencia);
                cuestionario.setIdCuest(rs.getInt("ID_CUEST"));
                cuestionario.setPreguntaCollection(new PreguntaDAO().listarPreguntasXCuest(cuestionario.getIdCuest()));
                cuestAsig.setIdCuestAsig(rs.getInt("ID_CUEST_ASIG"));
                cuestAsig.setRutJefe(rs.getString("RUT_JEFE"));
                cuestAsig.setFechaInicio("FECHA_INICIO");
                cuestAsig.setFechaTermino("FECHA_TERMINO");
                cuestAsig.setCuestionario(cuestionario);

            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            this.conexion.close();

        }
        return cuestAsig;

    }

    public List<CuestAsig> cuestionariosPorRutJefe(String rutJefe) throws SQLException {

        List<CuestAsig> listado = new ArrayList<CuestAsig>();

        try {
            this.conexion = new Conexion().obtenerConexion();
            //       PreparedStatement ps = conexion.prepareStatement("select * from cuest_asig where rut_jefe = ? order by id_cuest_asig asc");
            //       ps.setString(1, rutJefe);
            String llamada = "{call PKG_REPORTE_FUNCIONARIO.REPORTE_JEFE_PENDIENTES(?,?)}";
            CallableStatement cstmt = conexion.prepareCall(llamada);
            cstmt.setString(1, rutJefe);
            cstmt.registerOutParameter(2, OracleTypes.CURSOR);

            //ejecutamos la llamada al procedimiento almacenado
            cstmt.execute();

            ResultSet rs = (ResultSet) cstmt.getObject(2);
            while (rs.next()) {
                CuestAsig cuestAsig = new CuestAsig();
                cuestAsig.setIdCuestAsig(rs.getInt("ID_CUEST_ASIG"));
                cuestAsig.setRutJefe(rs.getString("RUT_JEFE"));
                cuestAsig.setFechaInicio(rs.getString("FECHA_INICIO"));
                cuestAsig.setFechaTermino(rs.getString("FECHA_TERMINO"));
                cuestAsig.setEstadoCuestionarioAsig(rs.getString("ESTADO_CUESTIONARIO_ASIG"));
                cuestAsig.setControlEstados(new ControlEstados());
                listado.add(cuestAsig);
            }
        } catch (Exception ex) {
            System.out.println("Error : " + ex.getMessage());
        } finally {
            this.conexion.close();
        }
        return listado;
    }
}
