/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet.Cuestionario;

import DAO.ObservacionDAO;
import DAO.CompetenciaDAO;
import DAO.CuestAsigDAO;
import DAO.CuestionarioDAO;
import DAO.EvaluacionDAO;
import DAO.UsuarioDAO;
import Entities.Competencia;
import Entities.CuestAsig;
import Entities.Cuestionario;
import Entities.Evaluacion;
import Entities.Nivel;
import Entities.Observacion;
import Entities.Persona;
import Entities.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author EduardoGatica
 */
@WebServlet(name = "ConsultarNota", urlPatterns = {"/consultarNota"})
public class ConsultarNota extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        try {

            Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
            String rutJefe = usuario.getUsername();
            int rol = usuario.getTipoUsuario().getIdTipoUsuario();
            System.out.println("Rut  a usar en el listado: " + rutJefe);

            if (rol == 2) {
                List<Evaluacion> evaluacion = new EvaluacionDAO().listarEvaluacionXJefe(rutJefe);
                request.setAttribute("evaluaciones", evaluacion);
            } else if (rol == 3) {
                List<Evaluacion> evaluacion = new EvaluacionDAO().listarEvaluacionXEmp(rutJefe);
                request.setAttribute("evaluaciones", evaluacion);
            }

            //List<Usuario> listado = new EvaluacionDAO().listarPersonasPorJefe(rutJefe);
            //request.setAttribute("usuarios", listado);
            request.getRequestDispatcher("ConsultarNota.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AsignarEvaluacion.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        String rutJefe = usuario.getUsername();
        int rol = usuario.getTipoUsuario().getIdTipoUsuario();
        System.out.println("Rut  a usar en el listado: " + rutJefe);
        int evaluacion = Integer.parseInt(request.getParameter("cboEvaluacion"));

        try {
            
            Evaluacion nota = new EvaluacionDAO().buscarEvaluacion(evaluacion);
            int idCuestionario = nota.getCuestAsig().getCuestionario().getIdCuest();
            Cuestionario cu= new CuestionarioDAO().buscarCuestionario(idCuestionario);
            Competencia comp = new CompetenciaDAO().buscarCompetencia(cu.getCompetencia().getIdComp());
            int not = nota.getNotaEvaluacion();
            System.out.println("Nota Evaluacion en consultarNota.Java: " + not);
            int brecha = nota.getBrecha();
            System.out.println("Brecha Evaluacion en consultarNota.Java: " + brecha);
            int idCompetencia=comp.getIdComp();
            System.out.println("idCompetencia Evaluacion en consultarNota.Java: " + idCompetencia);
            if (not > 0) {
                request.setAttribute("mensaje", "La nota de la evaluación numero "+ nota.getIdEvaluacion() +" del Empleado: "+nota.getPersona().getNombrePersona()+" "+nota.getPersona().getApellidoPaterno() +" es: <br>" + "<h1>" + not + "</h1>");
                System.out.println("la nota de la evaluacion es: " + not);

                if (brecha >= -2) {
                 //llamo un metodo para llamar la observacion positiva       
                    Observacion ob= new ObservacionDAO().obtenerObsPos(idCompetencia);
                    String observacion= ob.getMensajePuntajeSuperior();
                    request.setAttribute("observacion", "Observacion: "+observacion);
                } else {
                    Observacion ob= new ObservacionDAO().obtenerObsNeg(idCompetencia);    
                    String observacion= ob.getMensajePuntajeInferior();
                    request.setAttribute("observacion", "Observacion: "+observacion);
                }

                try {

                    if (rol == 2) {
                        List<Evaluacion> evaluacion1 = new EvaluacionDAO().listarEvaluacionXJefe(rutJefe);
                        request.setAttribute("evaluaciones", evaluacion1);
                    } else if (rol == 3) {
                        List<Evaluacion> evaluacion1 = new EvaluacionDAO().listarEvaluacionXEmp(rutJefe);
                        request.setAttribute("evaluaciones", evaluacion1);
                    }
                    request.getRequestDispatcher("ConsultarNota.jsp").forward(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(AsignarEvaluacion.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                request.setAttribute("mensaje", "Aún no puedes consultar tu nota. debes esperar a que tu contraparte responda la evaluacion.numero: "+nota.getIdEvaluacion() +" <br>" + "<h1>" + "Actualmente la nota de la evaluacion del empleado "+nota.getPersona().getNombrePersona()+" "+nota.getPersona().getApellidoPaterno() + " es:" + "</h1>" + "<br>" + "<h1>" + not + "</h1>");
                System.out.println("aun no puedes consultar tu nota. debes esperar a que tu contraparte responda la evaluacion es: " + not);

                try {

                    if (rol == 2) {
                        List<Evaluacion> evaluacion1 = new EvaluacionDAO().listarEvaluacionXJefe(rutJefe);
                        request.setAttribute("evaluaciones", evaluacion1);
                    } else if (rol == 3) {
                        List<Evaluacion> evaluacion1 = new EvaluacionDAO().listarEvaluacionXEmp(rutJefe);
                        request.setAttribute("evaluaciones", evaluacion1);
                    }
                    request.getRequestDispatcher("ConsultarNota.jsp").forward(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(AsignarEvaluacion.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(ConsultarNota.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
