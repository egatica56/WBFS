/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entities.CuestAsig;
import Entities.Cuestionario;
import Entities.Evaluacion;
import Entities.OpcionRespuesta;
import Entities.Persona;
import Entities.Pregunta;
import Entities.TipoUsuario;
import Entities.Usuario;
import Util.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.AbstractList;
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
            String llamada = "{call PKG_EVALUACION_1.SP_ADD_EVAL(?,?,?,?,?)}";
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

    public List<Evaluacion> listarEvaluacionXJefe(String rut) throws SQLException {

        List<Evaluacion> listado = new ArrayList<Evaluacion>();

        try {
            this.conexion = new Conexion().obtenerConexion();
            String llamada = "{call PKG_EVALUACION_1.SP_LISTAR_EVALUACION(?,?)}";
            CallableStatement cstmt = conexion.prepareCall(llamada);

            cstmt.setString(1, rut);
            cstmt.registerOutParameter(2, OracleTypes.CURSOR);

            //ejecutamos la llamada al procedimiento almacenado
            cstmt.execute();

            ResultSet rs = (ResultSet) cstmt.getObject(2);

            while (rs.next()) {
                CuestAsig cuestAsig = new CuestAsig();
                Evaluacion evaluacion = new Evaluacion();
                TipoUsuario tipoUsuario = new TipoUsuario();
                Persona persona = new Persona();
                Usuario usuario = new Usuario();
                cuestAsig.setIdCuestAsig(rs.getInt("ID_CUEST_ASIG"));
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
                evaluacion.setRutJefe(rs.getString("RUT_JEFE"));
                evaluacion.setIdEvaluacion(rs.getInt("ID_EVALUACION"));
                evaluacion.setFechaEvaluacion(rs.getString("FECHA_EVALUACION"));
                evaluacion.setPersona(persona);
                evaluacion.setCuestAsig(cuestAsig);
                listado.add(evaluacion);
                System.out.println("INFO: " + tipoUsuario.getIdTipoUsuario());

            }
            for (Evaluacion u : listado) {
                System.out.println("usuario: " + u.getPersona().getNombrePersona());

            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            this.conexion.close();

        }
        return listado;

    }

    public Evaluacion validarNotaJefe(int idEvaluacion, String rutJefe) throws SQLException {
        Evaluacion evaluacion = new Evaluacion();
        Persona persona = new Persona();
        try {
            this.conexion = new Conexion().obtenerConexion();
            String llamada = "{call PKG_EVALUACION_1.SP_BUSCAR_NOTA_JE(?,?,?)}";
            CallableStatement cstmt = conexion.prepareCall(llamada);

            cstmt.setInt(1, idEvaluacion);
            cstmt.setString(2, rutJefe);
            cstmt.registerOutParameter(3, OracleTypes.CURSOR);

            //ejecutamos la llamada al procedimiento almacenado
            cstmt.execute();

            ResultSet rs = (ResultSet) cstmt.getObject(3);

            while (rs.next()) {
                persona.setRutPersona(rs.getString("RUT_PERSONA"));
                evaluacion.setPersona(persona);
                evaluacion.setRutJefe(rs.getString("RUT_JEFE"));
                evaluacion.setIdEvaluacion(rs.getInt("ID_EVALUACION"));
                evaluacion.setFechaEvaluacion(rs.getString("FECHA_EVALUACION"));
                evaluacion.setNotaJefe(rs.getInt("NOTA_JEFE"));

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            this.conexion.close();

        }
        System.out.println("Nota Jefe en daoEvaluacion: " + evaluacion.getNotaJefe());
        return evaluacion;

    }

    public Evaluacion validarNotaEmp(int idEvaluacion, String rutEmpleado) throws SQLException {
        Evaluacion evaluacion = null;
        Persona persona = new Persona();
        try {
            this.conexion = new Conexion().obtenerConexion();
            String llamada = "{call PKG_EVALUACION_1.SP_BUSCAR_NOTA_EM(?,?,?)}";
            CallableStatement cstmt = conexion.prepareCall(llamada);
            evaluacion = new Evaluacion();
            cstmt.setInt(1, idEvaluacion);
            cstmt.setString(2, rutEmpleado);
            cstmt.registerOutParameter(3, OracleTypes.CURSOR);

            //ejecutamos la llamada al procedimiento almacenado
            cstmt.execute();

            ResultSet rs = (ResultSet) cstmt.getObject(3);

            while (rs.next()) {
                persona.setRutPersona(rs.getString("RUT_PERSONA"));
                evaluacion.setPersona(persona);
                evaluacion.setRutJefe(rs.getString("RUT_JEFE"));
                evaluacion.setIdEvaluacion(rs.getInt("ID_EVALUACION"));
                evaluacion.setFechaEvaluacion(rs.getString("FECHA_EVALUACION"));
                evaluacion.setNotaFuncionario(rs.getInt("NOTA_FUNCIONARIO"));

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            this.conexion.close();

        }
        System.out.println("Nota Empleado en daoEvaluacion: " + evaluacion.getNotaFuncionario());
        return evaluacion.getNotaEvaluacion() == 0 ? evaluacion : null;

    }

    public List<Evaluacion> listarEvaluacionXEmp(String rut) throws SQLException {

        List<Evaluacion> listado = new ArrayList<Evaluacion>();

        try {
            this.conexion = new Conexion().obtenerConexion();
            String llamada = "{call SP_LISTAR_EVAL_EMP(?,?)}";
            CallableStatement cstmt = conexion.prepareCall(llamada);

            cstmt.setString(1, rut);
            cstmt.registerOutParameter(2, OracleTypes.CURSOR);

            //ejecutamos la llamada al procedimiento almacenado
            cstmt.execute();

            ResultSet rs = (ResultSet) cstmt.getObject(2);

            while (rs.next()) {
                CuestAsig cuestAsig = new CuestAsig();
                Evaluacion evaluacion = new Evaluacion();
                TipoUsuario tipoUsuario = new TipoUsuario();
                Persona persona = new Persona();
                Usuario usuario = new Usuario();
                cuestAsig.setIdCuestAsig(rs.getInt("ID_CUEST_ASIG"));
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
                evaluacion.setRutJefe(rs.getString("RUT_JEFE"));
                evaluacion.setIdEvaluacion(rs.getInt("ID_EVALUACION"));
                evaluacion.setFechaEvaluacion(rs.getString("FECHA_EVALUACION"));
                evaluacion.setPersona(persona);
                evaluacion.setCuestAsig(cuestAsig);
                listado.add(evaluacion);
                System.out.println("INFO: " + tipoUsuario.getIdTipoUsuario());

            }
            for (Evaluacion u : listado) {
                System.out.println("usuario: " + u.getPersona().getNombrePersona());

            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            this.conexion.close();

        }
        return listado;

    }

    public List<Evaluacion> generarCuestionario(String rut, int idEval) throws SQLException {

        List<Evaluacion> listado = new ArrayList<Evaluacion>();

        try {
            this.conexion = new Conexion().obtenerConexion();
            String llamada = "{call SP_PINTAR_CUESTIONARIO(?,?,?)}";
            CallableStatement cstmt = conexion.prepareCall(llamada);
            cstmt.setInt(1, idEval);
            cstmt.setString(2, rut);
            cstmt.registerOutParameter(3, OracleTypes.CURSOR);

            //ejecutamos la llamada al procedimiento almacenado
            cstmt.execute();

            ResultSet rs = (ResultSet) cstmt.getObject(2);

            while (rs.next()) {
                Evaluacion evaluacion = new Evaluacion();
                CuestAsig cuestAsig = new CuestAsig();
                Cuestionario cuestionario = new Cuestionario();
                Pregunta pregunta = new Pregunta();
                OpcionRespuesta respuesta = new OpcionRespuesta();

                Persona persona = new Persona();
                cuestAsig.setRutJefe("RUT_JEFE");
                cuestAsig.setIdCuestAsig(rs.getInt("ID_CUEST_ASIG"));
                evaluacion.setCuestAsig(cuestAsig);

                persona.setNombrePersona(rs.getString("NOMBRE_PERSONA"));
                persona.setRutPersona(rs.getString("RUT_PERSONA"));
                persona.setApellidoPaterno(rs.getString("APELLIDO_PATERNO"));

                evaluacion.setRutJefe(rs.getString("RUT_JEFE"));
                evaluacion.setIdEvaluacion(rs.getInt("ID_EVALUACION"));
                evaluacion.setFechaEvaluacion(rs.getString("FECHA_EVALUACION"));
                evaluacion.setPersona(persona);
                evaluacion.setCuestAsig(cuestAsig);
                pregunta.setIdPregunta(rs.getInt("ID_PREGUNTA"));
                pregunta.setTextoPregunta("TEXTO_PREGUNTA");
                respuesta.setPregunta(pregunta);
                respuesta.setTextoRespuesta("TEXTO_RESPUESTA");

                listado.add(evaluacion);

            }
            for (Evaluacion u : listado) {
                System.out.println("usuario: " + u.getPersona().getNombrePersona());

            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            this.conexion.close();

        }

        return listado;

    }

    public boolean actualizarNotaJefe(int notaJefe, int idEvaluacion, String rutJefe) throws SQLException {
        //Evaluacion evaluacion=new Evaluacion();
        try {

            this.conexion = new Conexion().obtenerConexion();
            String llamada = "{call PKG_EVALUACION_1.SP_ACTUALIZAR_NOTA_JE(?,?,?)}";
            CallableStatement cstmt = conexion.prepareCall(llamada);

            cstmt.setInt(1, idEvaluacion);
            cstmt.setInt(2, notaJefe);
            cstmt.setString(3, rutJefe);

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

    public boolean actualizarNotaEmpleado(int notaEmpleado, int idEvaluacion, String rutEmpleado) throws SQLException {
        try {

            this.conexion = new Conexion().obtenerConexion();
            String llamada = "{call PKG_EVALUACION_1.SP_ACTUALIZAR_NOTA_EM(?,?,?)}";
            CallableStatement cstmt = conexion.prepareCall(llamada);

            cstmt.setInt(1, idEvaluacion);
            cstmt.setInt(2, notaEmpleado);
            cstmt.setString(3, rutEmpleado);

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

    public boolean actualizarNotaFinal(int nota, int idEvaluacion) throws SQLException {
        try {

            this.conexion = new Conexion().obtenerConexion();
            String llamada = "{call PKG_EVALUACION_1.SP_ACTUALIZAR_NOTA_FI(?,?)}";
            CallableStatement cstmt = conexion.prepareCall(llamada);

            cstmt.setInt(1, idEvaluacion);
            cstmt.setInt(2, nota);

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

    public boolean actualizarBrecha(int brecha, int idEvaluacion) throws SQLException {
        try {
            this.conexion = new Conexion().obtenerConexion();
            String llamada = "{call PKG_EVALUACION_1.SP_ACTUALIZAR_BRECHA(?,?)}";
            CallableStatement cstmt = conexion.prepareCall(llamada);

            cstmt.setInt(1, idEvaluacion);
            cstmt.setInt(2, brecha);

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

    public Evaluacion buscarEvaluacion(int idEvaluacion) throws SQLException {
        Evaluacion evaluacion = new Evaluacion();
        Persona persona = new Persona();
        Cuestionario cu = new Cuestionario();
        CuestAsig cuA = new CuestAsig();
        try {
            this.conexion = new Conexion().obtenerConexion();
            String llamada = "{call PKG_EVALUACION_1.SP_BUSCAR_EVALUACION(?,?)}";
            CallableStatement cstmt = conexion.prepareCall(llamada);
            cstmt.setInt(1, idEvaluacion);
            cstmt.registerOutParameter(2, OracleTypes.CURSOR);

            //ejecutamos la llamada al procedimiento almacenado
            cstmt.execute();

            ResultSet rs = (ResultSet) cstmt.getObject(2);

            while (rs.next()) {

                cu.setIdCuest(rs.getInt("ID_CUEST"));
                cuA.setCuestionario(cu);
                persona.setRutPersona(rs.getString("RUT_PERSONA"));
                evaluacion.setRutJefe(rs.getString("RUT_JEFE"));
                evaluacion.setNotaEvaluacion(rs.getInt("NOTA_EVALUACION"));
                evaluacion.setBrecha(rs.getInt("BRECHA"));
                evaluacion.setCuestAsig(cuA);
                evaluacion.setPersona(persona);

            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            this.conexion.close();

        }

        return evaluacion;

    }

    public Evaluacion buscarNotaEvaluacion(int idEvaluacion) throws SQLException {
        Evaluacion evaluacion = new Evaluacion();

        try {
            this.conexion = new Conexion().obtenerConexion();
            String llamada = "{call PKG_EVALUACION_1.SP_BUSCAR_NOTA_FINAL(?,?)}";
            CallableStatement cstmt = conexion.prepareCall(llamada);
            cstmt.setInt(1, idEvaluacion);
            cstmt.registerOutParameter(2, OracleTypes.CURSOR);

            //ejecutamos la llamada al procedimiento almacenado
            cstmt.execute();

            ResultSet rs = (ResultSet) cstmt.getObject(2);

            while (rs.next()) {

                evaluacion.setNotaEvaluacion(rs.getInt("NOTA_EVALUACION"));

            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            this.conexion.close();

        }

        return evaluacion;

    }

    public Cuestionario obtenerPorcentajes(int idEvaluacion) throws SQLException {
        Evaluacion evaluacion = new Evaluacion();
        CuestAsig cuestAsig = new CuestAsig();
        Cuestionario cuestionario = new Cuestionario();
        try {
            this.conexion = new Conexion().obtenerConexion();
            String llamada = "{call CALCULAR_NOTA_1(?,?)}";
            CallableStatement cstmt = conexion.prepareCall(llamada);
            cstmt.setInt(1, idEvaluacion);
            cstmt.registerOutParameter(2, OracleTypes.CURSOR);

            //ejecutamos la llamada al procedimiento almacenado
            cstmt.execute();

            ResultSet rs = (ResultSet) cstmt.getObject(2);

            while (rs.next()) {

                cuestionario.setPorcentajeAutoevaluacion(rs.getInt("PORCENTAJE_AUTOEVALUACION"));
                cuestionario.setPorcentajeJefe(rs.getInt("PORCENTAJE_JEFE"));
                cuestAsig.setCuestionario(cuestionario);
                cuestAsig.setIdCuestAsig(rs.getInt("ID_CUEST_ASIG"));
                evaluacion.setCuestAsig(cuestAsig);
                evaluacion.setIdEvaluacion(rs.getInt("ID_EVALUACION"));
                evaluacion.setCuestAsig(cuestAsig);

            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            this.conexion.close();

        }
        System.out.println("porc jefe: " + cuestionario.getPorcentajeJefe());
        System.out.println("porc eval: " + cuestionario.getPorcentajeAutoevaluacion());
        return cuestionario;
    }

}
