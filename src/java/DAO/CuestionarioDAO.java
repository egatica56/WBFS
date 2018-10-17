/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Util.Conexion;
import Entities.Competencia;
import Entities.Cuestionario;
import Entities.Nivel;
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
public class CuestionarioDAO {

    private Connection conexion;

    public CuestionarioDAO() {

    }

    public boolean agregarCuestionario(Cuestionario cuestionario) throws SQLException {

        try {
            //Cuestionario cu = new Cuestionario();
            //Competencia com = new Competencia();
            this.conexion = new Conexion().obtenerConexion();
            String llamada = "{call PKG_CUESTIONARIO_1.SP_AGREGAR_CUESTIONARIO(?,?,?)}";
            CallableStatement cstmt = conexion.prepareCall(llamada);

            // cstmt.setInt(1, cuestionario.getIdCuest());
            cstmt.setInt(1, cuestionario.getPorcentajeJefe());
            cstmt.setInt(2, cuestionario.getPorcentajeAutoevaluacion());
            cstmt.setInt(3, cuestionario.getCompetencia().getIdComp());

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

    public List<Cuestionario> listar_cuestionario() throws SQLException {

        List<Cuestionario> listado = new ArrayList<Cuestionario>();

        try {
            this.conexion = new Conexion().obtenerConexion();
            String llamada = "{call PKG_CUESTIONARIO_1.SP_LISTAR_CUESTIONARIO(?)}";
            CallableStatement cstmt = conexion.prepareCall(llamada);

            cstmt.registerOutParameter(1, OracleTypes.CURSOR);

            //ejecutamos la llamada al procedimiento almacenado
            cstmt.execute();

            ResultSet rs = (ResultSet) cstmt.getObject(1);

            while (rs.next()) {
                Competencia competencia = new Competencia();
                Cuestionario cuestionario = new Cuestionario();
                competencia.setIdComp(rs.getInt("ID_COMP"));
                competencia.setNombreCompetencia(rs.getString("NOMBRE_COMPETENCIA"));
                cuestionario.setIdCuest(rs.getInt("ID_CUEST"));
                cuestionario.setPorcentajeAutoevaluacion(rs.getInt("PORCENTAJE_AUTOEVALUACION"));
                cuestionario.setPorcentajeJefe(rs.getInt("PORCENTAJE_JEFE"));
                cuestionario.setCompetencia(competencia);

                listado.add(cuestionario);

            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            this.conexion.close();

        }
        return listado;

    }

    public Cuestionario buscarCuestionario(int idCuestionario) throws SQLException {
        Cuestionario cuestionario = new Cuestionario();
        Competencia competencia = new Competencia();
        try {

            this.conexion = new Conexion().obtenerConexion();
            String llamada = "{call PKG_CUESTIONARIO_1.SP_BUSCAR_CUESTIONARIO(?,?)}";
            CallableStatement cstmt = conexion.prepareCall(llamada);
            cstmt.setInt(1, idCuestionario);
            cstmt.registerOutParameter(2, OracleTypes.CURSOR);

            //ejecutamos la llamada al procedimiento almacenado
            cstmt.execute();

            ResultSet rs = (ResultSet)cstmt.getObject(2);

            while (rs.next()) {
                

                competencia.setIdComp(rs.getInt("ID_COMP"));
                competencia.setNombreCompetencia(rs.getString("NOMBRE_COMPETENCIA"));
                cuestionario.setIdCuest(rs.getInt("ID_CUEST"));
                cuestionario.setPorcentajeAutoevaluacion(rs.getInt("PORCENTAJE_AUTOEVALUACION"));
                cuestionario.setPorcentajeJefe(rs.getInt("PORCENTAJE_JEFE"));
                cuestionario.setCompetencia(competencia);

            }
            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            this.conexion.close();

        }
        return cuestionario;
    }

}
