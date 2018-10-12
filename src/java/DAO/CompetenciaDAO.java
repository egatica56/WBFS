/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Util.Conexion;
import Entities.Competencia;
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
public class CompetenciaDAO {



    private Connection conexion;

    public CompetenciaDAO() {

    }

    public List<Competencia> listarCompetencias() throws SQLException {
        
        List<Competencia> listado_competencia = new ArrayList<Competencia>();
        

        try {
            this.conexion = new Conexion().obtenerConexion();
            String llamada = "{call SP_LISTAR_COMPETENCIAS(?)}";
            CallableStatement cstmt = conexion.prepareCall(llamada);
            cstmt.registerOutParameter(1, OracleTypes.CURSOR);

            //ejecutamos la llamada al procedimiento almacenado
            cstmt.execute();

            ResultSet rs = (ResultSet) cstmt.getObject(1);

            while (rs.next()) {
                Competencia competencia = new Competencia();
                Nivel nivel =new Nivel();
                competencia.setIdComp(rs.getInt("ID_COMP"));
                competencia.setNombreCompetencia(rs.getString("NOMBRE_COMPETENCIA"));
                competencia.setDescripcionCompetencia(rs.getString("DESCRIPCION_COMPETENCIA"));
                competencia.setSiglaCompetencia(rs.getString("SIGLA_COMPETENCIA"));
                nivel.setIdNivel(rs.getInt("ID_NIVEL"));
                competencia.setNivel(nivel);

                listado_competencia.add(competencia);

            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            this.conexion.close();

        }
        return listado_competencia;

    }

}
