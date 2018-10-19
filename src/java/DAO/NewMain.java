/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entities.Competencia;
import Entities.CuestAsig;
import Entities.Cuestionario;
import Entities.OpcionRespuesta;
import Entities.Pregunta;
import Entities.Usuario;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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

            }
            CuestionarioDAO cuestionarioDAO = new CuestionarioDAO();

            cuestionarioDAO.listar_cuestionario();

            for (Cuestionario c : cuestionarioDAO.listar_cuestionario()) {
                System.out.println("id Competencia: " + c.getCompetencia().getIdComp());
                System.out.println("nombre Competencia: " + c.getCompetencia().getNombreCompetencia());
            }

            PreguntaDAO preguntaDAO = new PreguntaDAO();
            Cuestionario cue=new Cuestionario();
            Pregunta pre = new Pregunta();
            cue.setIdCuest(5);
            pre.setTextoPregunta("AAAAAA");
            pre.setEsCorrecta("SI");
            pre.setPorcentajePregunta(10);
            pre.setCuestionario(cue);

            boolean resp = preguntaDAO.agregarPregunta(pre);

            if (resp) {
                System.out.println("Agregado");
            } else {
                System.out.println("No agregado");
            }
            
            PreguntaDAO preguntaDAO = new PreguntaDAO();
            
             for (Pregunta c : preguntaDAO.listar_pregunta()) {
                System.out.println("id Competencia: " + c.getTextoPregunta());
                System.out.println("nombre Competencia: " + c.getPorcentajePregunta());
            }
            
            
            RespuestaDAO respuestaDAO = new RespuestaDAO();
            OpcionRespuesta res=new OpcionRespuesta();
            Pregunta pre = new Pregunta();
            pre.setIdPregunta(5);
            res.setPorcentajeRespuesta(100);
            res.setTextoRespuesta("AAAAAAABC");
            res.setPregunta(pre);
            

            boolean resp = respuestaDAO.agregarRespuesta(res) ;

            if (resp) {
                System.out.println("Agregado");
            } else {
                System.out.println("No agregado");
            }
            
            
             UsuarioDAO preguntaDAO = new UsuarioDAO();
            
             for (Usuario c : preguntaDAO.listar_usuario()) {
                 System.out.println("id_usuario "+ c.getIdUsuario());
                
                
            }

            CuestAsigDAO cuest = new CuestAsigDAO();
            CuestAsig cu = new CuestAsig();
            Cuestionario cue = new Cuestionario();
            cue.setIdCuest(5);
            cu.setCuestionario(cue);
            cu.setFechaInicio(new java.util.Date("2018/10/31"));
            cu.setFechaTermino(new java.util.Date("2018/10/25"));
            cu.setRutJefe("189090-2");            

            boolean resp = cuest.agregarCuestAsig(cu);

            if (resp) {

                System.out.println("Agregado");
            } else {
                System.out.println("No agregado");
            }

            java.util.Date utilDate = new java.util.Date("2018/10/31");
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            System.out.println("utilDate:" + utilDate);
            System.out.println("sqlDate:" + sqlDate);

        }

            CuestAsigDAO cudao = new CuestAsigDAO();

            for (CuestAsig a : cudao.listar_cuest_asig()) {
                System.out.println("ID_CUEST_ASIGN" + a.getIdCuestAsig());
                System.out.println("id cuestionario_asignado: "+ a.getCuestionario().getIdCuest());
                System.out.println("persona" + a.getRutJefe());
            }

        }
            
            CuestionarioDAO cuesDAO= new CuestionarioDAO();
           
           Cuestionario k=new Cuestionario();
           Competencia c=new Competencia(1);
           k.setPorcentajeAutoevaluacion(10);
           k.setPorcentajeJefe(10);
           k.setCompetencia(c);
           
           
           boolean Respo=cuesDAO.agregarCuestionario(k);
            if (Respo) {
                System.out.println("Creado");
            }else{
                System.out.println("No Creado");
            }
           
           
            
            Cuestionario cue=cuesDAO.buscarCuestionario(27);
            System.out.println("id cuestionario: "+ cue.getIdCuest());
            System.out.println("porcentaje autoevaluacion: "+ cue.getPorcentajeAutoevaluacion());
            System.out.println("porcentaje jefe: "+ cue.getPorcentajeJefe());
        }*/
            
            EvaluacionDAO evalDAO=new EvaluacionDAO();
            
            evalDAO.listarPersonasPorJefe("18882760-8");
            
            for (Usuario u : evalDAO.listarPersonasPorJefe("18882760-8")) {
                
                System.out.println("usuario: "+u.getUsername());
                System.out.println("Nombre Persona: "+u.getPersona().getNombrePersona());
            }
            
            
        }
    }
}
