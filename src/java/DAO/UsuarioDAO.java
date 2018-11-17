/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Util.Conexion;
import Entities.ControlEstados;
import Entities.Persona;
import Entities.TipoUsuario;
import Entities.Usuario;
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
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.

Active Record -> Herencia nutrir todos los datos clase abstracta
tipos genericos-> un objeto toma cuaquier forma.



import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Duoc
 */
public class UsuarioDAO {

    private Connection conexion;

    public UsuarioDAO() {

    }

    public Usuario login(String nombreUsuario, String password) throws SQLException {
        Usuario usuario = new Usuario();
        TipoUsuario tipoUsuario = new TipoUsuario();
        ControlEstados control = new ControlEstados();
        Persona persona = new Persona();

        try {
            this.conexion = new Conexion().obtenerConexion();
            String llamada = "{call SP_LOGIN(?,?,?)}";
            CallableStatement cstmt = conexion.prepareCall(llamada);
            cstmt.setString(1, nombreUsuario);
            cstmt.setString(2, password);
            cstmt.registerOutParameter(3, OracleTypes.CURSOR);

            //ejecutamos la llamada al procedimiento almacenado
            cstmt.execute();

            ResultSet rs = (ResultSet) cstmt.getObject(3);

            while (rs.next()) {
                usuario.setIdUsuario(rs.getInt("ID_USUARIO"));
                usuario.setUsername(rs.getString("USERNAME"));
                usuario.setPassword(rs.getString("PASSWORD"));
                tipoUsuario.setIdTipoUsuario(rs.getInt("ID_TIPO_USUARIO"));
                tipoUsuario.setNombreTipoUsuario(rs.getString("NOMBRE_TIPO_USUARIO"));
                persona.setNombrePersona(rs.getString("NOMBRE_PERSONA"));
                persona.setRutPersona(rs.getString("RUT_PERSONA"));
                persona.setApellidoPaterno(rs.getString("APELLIDO_PATERNO"));
                control.setIdEstado(rs.getInt("ID_ESTADO"));
                           
                usuario.setControlEstados(control);
                usuario.setTipoUsuario(tipoUsuario);
                usuario.setPersona(persona);

            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            this.conexion.close();

        }
        return usuario;

    }

    public List<Usuario> listar_usuario() throws SQLException {
        List<Usuario> listado = new ArrayList<Usuario>();

        try {
            this.conexion = new Conexion().obtenerConexion();
            String llamada = "{call SP_LISTAR_PERSONA(?)}";
            CallableStatement cstmt = conexion.prepareCall(llamada);

            cstmt.registerOutParameter(1, OracleTypes.CURSOR);

            //ejecutamos la llamada al procedimiento almacenado
            cstmt.execute();

            ResultSet rs = (ResultSet) cstmt.getObject(1);

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

}
