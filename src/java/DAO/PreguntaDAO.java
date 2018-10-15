/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entities.Cuestionario;
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

    public boolean agregarPregunta(Pregunta pregunta) throws SQLException {

        try {
            
            //Competencia com = new Competencia();
            this.conexion = new Conexion().obtenerConexion();
            String llamada = "{call PKG_PREGUNTA_1.SP_AGREGAR_PREGUNTA(?,?,?,?)}";
            CallableStatement cstmt = conexion.prepareCall(llamada);

            
            cstmt.setString(1, pregunta.getTextoPregunta());
            cstmt.setInt(2, pregunta.getPorcentajePregunta());
            cstmt.setString(3, pregunta.getEsCorrecta());
            cstmt.setInt(4,pregunta.getCuestionario().getIdCuest());
            

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

    public List<Pregunta> listar_pregunta() throws SQLException {

        List<Pregunta> listado = new ArrayList<Pregunta>();

        try {
            this.conexion = new Conexion().obtenerConexion();
            String llamada = "{call PKG_PREGUNTA_1.SP_LISTAR_PREGUNTA(?)}";
            CallableStatement cstmt = conexion.prepareCall(llamada);

            cstmt.registerOutParameter(1, OracleTypes.CURSOR);

            //ejecutamos la llamada al procedimiento almacenado
            cstmt.execute();

            ResultSet rs = (ResultSet) cstmt.getObject(1);

            while (rs.next()) {
                Cuestionario cu = new Cuestionario();
                Pregunta pre = new Pregunta();
                cu.setIdCuest(rs.getInt("ID_CUEST"));
                pre.setIdPregunta(rs.getInt("ID_PREGUNTA"));
                pre.setTextoPregunta(rs.getString("TEXTO_PREGUNTA"));
                pre.setEsCorrecta(rs.getString("ES_CORRECTA"));
                pre.setPorcentajePregunta(rs.getInt("PORCENTAJE_PREGUNTA"));
                pre.setCuestionario(cu);

                listado.add(pre);

            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            this.conexion.close();

        }
        return listado;

    }

}
