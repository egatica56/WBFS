/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import Entities.Pregunta;
import Util.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author EduardoGatica
 */
public class PreguntaDAO {
    
    private Connection conexion;

    public PreguntaDAO() {
    }

    public boolean agregarPregunta(Pregunta pregunta ) throws SQLException {

        try {
            //Cuestionario cu = new Cuestionario();
            //Competencia com = new Competencia();
            this.conexion = new Conexion().obtenerConexion();
            String llamada = "{call PKG_PREGUNTA_1.SP_AGREGAR_PREGUNTA(?,?,?)}";
            CallableStatement cstmt = conexion.prepareCall(llamada);

            // cstmt.setInt(1, cuestionario.getIdCuest());
            cstmt.setString(1, pregunta.getTextoPregunta());
            cstmt.setInt(2, pregunta.getPorcentajePregunta());
            cstmt.setString(3, pregunta.getEsCorrecta());
            cstmt.setInt(4, pregunta.getCuestionario().getIdCuest());

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

    public List<OpcionRespuesta> listar_respuesta() throws SQLException {

        List<OpcionRespuesta> listado = new ArrayList<OpcionRespuesta>();
        
        try {
            this.conexion = new Conexion().obtenerConexion();
            String llamada = "{call PKG_CUESTIONARIO_1.SP_LISTAR_RESPUESTA(?)}";
            CallableStatement cstmt = conexion.prepareCall(llamada);

            cstmt.registerOutParameter(1, OracleTypes.CURSOR);

            //ejecutamos la llamada al procedimiento almacenado
            cstmt.execute();

            ResultSet rs = (ResultSet) cstmt.getObject(1);

            while (rs.next()) {
                OpcionRespuesta op = new OpcionRespuesta();
                Pregunta pre = new Pregunta();
                pre.setIdPregunta(rs.getInt("ID_PREGUNTA"));
                pre.setTextoPregunta(rs.getString("TEXTO_PREGUNTA"));
                op.setIdOpcionRespuesta(rs.getInt("ID_OPCION_RESPUESTA"));
                op.setPorcentajeRespuesta(rs.getInt("PORCENTAJE_RESPUESTA"));
                op.setTextoRespuesta(rs.getString("TEXTO_RESPUESTA"));
                op.setPregunta(pre);

                listado.add(op);

            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            this.conexion.close();

        }
        return listado;

    }
    
}
