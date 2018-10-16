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
            String llamada = "{call PKG_CUESTIONARIO_ASIGNADO.SP_ASIGNAR_CUEST(?,?,?,?)}";
            CallableStatement cstmt = conexion.prepareCall(llamada);

            java.util.Date fecha1=cuestAsig.getFechaInicio();
            
            java.util.Date fecha2=cuestAsig.getFechaTermino();
            
            /*int anhoFechaInicio=cuestAsig.getFechaInicio().getDate();
            int mesFechaInicio=cuestAsig.getFechaInicio().getMonth();
            int diaFechaInicio=cuestAsig.getFechaInicio().getDay();
            
            System.out.println("año fecha inicio: "+anhoFechaInicio);
            System.out.println("mes fecha inicio: "+mesFechaInicio);
            System.out.println("dia fecha inicio: "+diaFechaInicio);
            
            int anhoFechaTermino=cuestAsig.getFechaTermino().getYear();
            int mesFechaTermino=cuestAsig.getFechaTermino().getMonth();
            int diaFechaTermino=cuestAsig.getFechaTermino().getDay();
            
            System.out.println("año fecha termino: "+anhoFechaTermino);
            System.out.println("mes fecha termino: "+mesFechaTermino);
            System.out.println("dia fecha termino: "+diaFechaTermino);
            
            */
            
            java.util.Date utilDate = fecha1;
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            System.out.println("utilDate Fecha Inicio:" + utilDate);
            System.out.println("sqlDate  Fecha Inicio:" + sqlDate);
            
            java.util.Date utilDate2 = fecha2;
            java.sql.Date sqlDate2 = new java.sql.Date(utilDate2.getTime());
            System.out.println("utilDate Fecha Termino:" + utilDate2);
            System.out.println("sqlDate  Fecha Termino:" + sqlDate2);
                   
            cstmt.setString(1, cuestAsig.getRutJefe());
            cstmt.setDate(2, sqlDate);
            cstmt.setDate(3, sqlDate2);
            //cstmt.setString(4, cuestAsig.getEstadoCuestionarioAsig());
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
