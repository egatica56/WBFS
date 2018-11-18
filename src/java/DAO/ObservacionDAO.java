/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entities.Competencia;
import Entities.Observacion;
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
public class ObservacionDAO {

    public ObservacionDAO() {

    }

    private Connection conexion;

    public Observacion obtenerObsPos(int idCompetencia) throws SQLException {
        Observacion observacion = new Observacion();
        Competencia comp = new Competencia();

        try {
            this.conexion = new Conexion().obtenerConexion();
            String llamada = "{call PKG_OBSERVACION_1.SP_SEL_OBS_POS(?,?)}";
            CallableStatement cstmt = conexion.prepareCall(llamada);
            cstmt.setInt(1, idCompetencia);
            cstmt.registerOutParameter(2, OracleTypes.CURSOR);

            //ejecutamos la llamada al procedimiento almacenado
            cstmt.execute();

            ResultSet rs = (ResultSet) cstmt.getObject(2);

            while (rs.next()) {
                comp.setIdComp(rs.getInt("ID_COMP"));
                observacion.setIdObservacion(rs.getInt("ID_OBSERVACION"));
                observacion.setMensajePuntajeSuperior(rs.getString("MENSAJE_PUNTAJE_SUPERIOR"));
                observacion.setCompetencia(comp);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            this.conexion.close();

        }

        return observacion;

    }

    public Observacion obtenerObsNeg(int idCompetencia) throws SQLException {
        Observacion observacion = new Observacion();
        Competencia comp = new Competencia();

        try {
            this.conexion = new Conexion().obtenerConexion();
            String llamada = "{call PKG_OBSERVACION_1.SP_SEL_OBS_NEG(?,?)}";
            CallableStatement cstmt = conexion.prepareCall(llamada);
            cstmt.setInt(1, idCompetencia);
            cstmt.registerOutParameter(2, OracleTypes.CURSOR);

            //ejecutamos la llamada al procedimiento almacenado
            cstmt.execute();

            ResultSet rs = (ResultSet) cstmt.getObject(2);

            while (rs.next()) {
                comp.setIdComp(rs.getInt("ID_COMP"));
                observacion.setIdObservacion(rs.getInt("ID_OBSERVACION"));
                observacion.setMensajePuntajeSuperior(rs.getString("MENSAJE_PUNTAJE_INFERIOR"));
                observacion.setCompetencia(comp);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            this.conexion.close();

        }

        return observacion;

    }

}
