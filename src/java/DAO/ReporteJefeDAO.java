/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entities.ReporteJefe;
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
 * @author Francisco
 */
public class ReporteJefeDAO {
    
    private Connection conexion;

    public ReporteJefeDAO() {

    }
    public List<ReporteJefe> listarReporteJefe(String rut) throws SQLException {
        
        List<ReporteJefe> listado_reportes = new ArrayList<ReporteJefe>();
        

        try {
            this.conexion = new Conexion().obtenerConexion();
            String llamada = "{call PKG_REPORTE_FUNCIONARIO.REPORTE_FUNCIONARIO(?,?)}";
            CallableStatement cstmt = conexion.prepareCall(llamada);
            cstmt.setString(1, rut);
            cstmt.registerOutParameter(2, OracleTypes.CURSOR);
            //ejecutamos la llamada al procedimiento almacenado
            cstmt.execute();

            ResultSet rs = (ResultSet) cstmt.getObject(2);

            while (rs.next()) {
                ReporteJefe repJefe = new ReporteJefe();
                repJefe.setRutEvaluado(rs.getString("RUT_PERSONA"));
                repJefe.setPerfil(rs.getString("NOMBRE_PERFIL"));
                repJefe.setCompetencia(rs.getString("NOMBRE_COMPETENCIA"));
                repJefe.setNotaEsperada(rs.getInt("ID_NIVEL"));
                repJefe.setNota(rs.getInt("NOTA_EVALUACION"));
                repJefe.setNota(rs.getInt("BRECHA"));
              
                listado_reportes.add(repJefe);

            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            this.conexion.close();

        }
        return listado_reportes;

    }
}
