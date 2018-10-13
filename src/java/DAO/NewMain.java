/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entities.Competencia;
import Entities.Cuestionario;
import Entities.Usuario;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author EduardoGatica
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        {
           /* UsuarioDAO dao = new UsuarioDAO();
            Usuario usuario = dao.login("11899186-9", "1234");
            System.out.println("NOMBRE_USUARIO: " + usuario.getUsername());
            System.out.println("CONTRASEÑA: " + usuario.getPassword());
            System.out.println("ID_USUARIO: " + usuario.getIdUsuario());
            System.out.println("RUT_PERSONA: " + usuario.getPersona().getRutPersona());
            System.out.println("RUT_PERSONA: " + usuario.getPersona().getNombrePersona());
            System.out.println("APELLIDO PATERNO: " + usuario.getPersona().getApellidoPaterno());

            /*CompetenciaDAO competencia = new CompetenciaDAO();

            List<Competencia> listaCompetencia = competencia.listarCompetencias();
            for (Competencia c : listaCompetencia) {
                System.out.println("id Competencia " + c.getIdComp());
                System.out.println("Nombre Competencia " + c.getNombreCompetencia());
                System.out.println("nivel " + c.getNivel().getIdNivel());

            }*/
            CuestionarioDAO cuestionarioDAO = new CuestionarioDAO();

            cuestionarioDAO.listar_cuestionario();

            for (Cuestionario c : cuestionarioDAO.listar_cuestionario()) {
                System.out.println("id Competencia: "+c.getCompetencia().getIdComp());
                System.out.println("nombre Competencia: "+c.getCompetencia().getNombreCompetencia());
            }

        }
    }

}


