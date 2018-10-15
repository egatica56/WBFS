/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet.Respuesta;

import DAO.CompetenciaDAO;
import DAO.CuestionarioDAO;
import DAO.PreguntaDAO;
import DAO.RespuestaDAO;
import Entities.Competencia;
import Entities.Cuestionario;
import Entities.OpcionRespuesta;
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
            List<Pregunta> listado = new PreguntaDAO().listar_pregunta();
            request.setAttribute("preguntas", listado);
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
            Pregunta pregunta = new Pregunta();
            OpcionRespuesta respuesta = new OpcionRespuesta();
            RespuestaDAO respuestaDAO = new RespuestaDAO();

            // int idCuest = Integer.parseInt(request.getParameter("txtId"));
            String resp = request.getParameter("txtRespuesta");
            int porcResp = Integer.parseInt(request.getParameter("txtPorcentajeRespuesta"));
            int idPregunta = Integer.parseInt(request.getParameter("cboPregunta"));
            pregunta.setIdPregunta(idPregunta);
            //cuestionario.setIdCuest(idCuest);
            respuesta.setPregunta(pregunta);
            respuesta.setTextoRespuesta(resp);
            respuesta.setPorcentajeRespuesta(porcResp);

            boolean re = respuestaDAO.agregarRespuesta(respuesta);
            if (re == true) {
                request.setAttribute("mensaje", "Respuesta Agregada Correctamente");

                try {
                    List<Pregunta> listado = new PreguntaDAO().listar_pregunta();
                    request.setAttribute("preguntas", listado);
                    request.getRequestDispatcher("Respuesta.jsp").forward(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(AgregarRespuesta.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                request.setAttribute("mensaje", "Error al Agregar la respuesta");
                try {
                    List<Pregunta> listado = new PreguntaDAO().listar_pregunta();
                    request.setAttribute("preguntas", listado);
                    request.getRequestDispatcher("Respuesta.jsp").forward(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(AgregarRespuesta.class.getName()).log(Level.SEVERE, null, ex);
                }

                request.getRequestDispatcher("Respuesta.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("mensaje", "Error al Agregar la Respuesta: " + e.getMessage());
            try {
                List<Pregunta> listado = new PreguntaDAO().listar_pregunta();
                request.setAttribute("preguntas", listado);
                request.getRequestDispatcher("Respuesta.jsp").forward(request, response);
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
