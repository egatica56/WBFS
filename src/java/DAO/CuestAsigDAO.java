/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entities.Competencia;
import Entities.CuestAsig;
import Entities.Cuestionario;
import Util.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
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
            String llamada = "{call PKG_CUESTIONARIO_ASIGNADO.SP_ASIGNAR_CUEST(?,?,?,?)}";
            CallableStatement cstmt = conexion.prepareCall(llamada);

           
            // cstmt.setInt(1, cuestionario.getIdCuest());
            cstmt.setString(1, cuestAsig.getRutJefe());
            cstmt.setDate(2,(Date)cuestAsig.getFechaInicio());
            cstmt.setDate(3,(Date)cuestAsig.getFechaTermino());
            //cstmt.setString(4,cuestAsig.getEstadoCuestionarioAsig());
            cstmt.setInt(4, cuestAsig.getCuestionario().getIdCuest());

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

  /*  public List<CuestAsig> listar_cuest_asig() throws SQLException {

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
                Cuestionario  cuestionario=new Cuestionario();
            
                CuestAsig cuestAsig = new CuestAsig();
                cuestionario.setIdCuest(rs.getInt("ID_CUEST"));
                cuestAsig.setIdCuestAsig(rs.getInt("ID_CUEST_ASIG"));
                cuestAsig.setRutJefe(rs.getString("RUT_JEFE"));
                cuestAsig.setFechaInicio ("FECHA_INICIO");
                cuestAsig.setFechaTermino("FECHA_TERMINO");
                cuestAsig.setCuestionario(cuestionario);
                

                listado.add(cuestionario);

            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            this.conexion.close();

        }
        return listado;

    }*/
    
    
    
}
