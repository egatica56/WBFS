/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet.Respuesta;


import DAO.CompetenciaDAO;
import DAO.CuestionarioDAO;
import Entities.Competencia;
import Entities.Cuestionario;
import Entities.Pregunta;
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
@WebServlet(name = "AgregarRespuesta", urlPatterns = {"/respuesta"})
public class AgregarRespuesta extends HttpServlet {

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
            List<Pregunta> listado=new PreguntaDAO().listarCompetencias();
            request.setAttribute("competencias", listado);
            request.getRequestDispatcher("Respuesta.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AgregarRespuesta.class.getName()).log(Level.SEVERE, null, ex);
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
        
         try {
             Cuestionario cuestionario = new Cuestionario();
             CuestionarioDAO cuestionarioDAO = new CuestionarioDAO();
            Competencia competencia = new Competencia();
           // int idCuest = Integer.parseInt(request.getParameter("txtId"));
            int porcEval = Integer.parseInt(request.getParameter("txtPorcentajeEvaluado"));
            int porcJefe = Integer.parseInt(request.getParameter("txtPorcentajeJefe"));
            int idComp = Integer.parseInt(request.getParameter("cboCompetencia"));
            competencia.setIdComp(idComp);
            //cuestionario.setIdCuest(idCuest);
            cuestionario.setPorcentajeJefe(porcJefe);
            cuestionario.setPorcentajeAutoevaluacion(porcEval);
            cuestionario.setCompetencia(competencia);

            boolean resp = cuestionarioDAO.agregarCuestionario(cuestionario);
            if (resp == true) {
                request.setAttribute("mensaje", "Cuestionario Agregado Correctamente");

                try {
                    List<Competencia> listado = new CompetenciaDAO().listarCompetencias();
                    request.setAttribute("competencias", listado);
                    request.getRequestDispatcher("Cuestionario.jsp").forward(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(AgregarRespuesta.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                request.setAttribute("mensaje", "Error al Agregar el Cuestionario");
                try {
                    List<Competencia> listado = new CompetenciaDAO().listarCompetencias();
                    request.setAttribute("competencias", listado);
                    request.getRequestDispatcher("Cuestionario.jsp").forward(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(AgregarRespuesta.class.getName()).log(Level.SEVERE, null, ex);
                }

                request.getRequestDispatcher("Cuestionario.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("mensaje", "Error al Agregar el Cuestionario: " + e.getMessage());
            try {
                List<Competencia> listado = new CompetenciaDAO().listarCompetencias();
                request.setAttribute("competencias", listado);
                request.getRequestDispatcher("Cuestionario.jsp").forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(AgregarRespuesta.class.getName()).log(Level.SEVERE, null, ex);
            }

            request.getRequestDispatcher("Cuestionario.jsp").forward(request, response);
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
