/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet.Pregunta;



import DAO.CompetenciaDAO;
import DAO.CuestionarioDAO;
import DAO.PreguntaDAO;
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
@WebServlet(name = "AgregarPregunta", urlPatterns = {"/pregunta"})
public class AgregarPregunta extends HttpServlet {

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
            List<Cuestionario> listado=new CuestionarioDAO().listar_cuestionario();
            request.setAttribute("cuestionarios", listado);
            request.getRequestDispatcher("Pregunta.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AgregarPregunta.class.getName()).log(Level.SEVERE, null, ex);
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
             Pregunta pregunta= new Pregunta();
             Cuestionario cuestionario = new Cuestionario();
             PreguntaDAO preguntaDAO = new PreguntaDAO();
            Competencia competencia = new Competencia();
            int idCuest= Integer.parseInt(request.getParameter("cboCuestionario"));
            cuestionario.setIdCuest(idCuest);
            String pre = request.getParameter("txtPregunta");
            
            //cuestionario.setIdCuest(idCuest);
            pregunta.setTextoPregunta(pre);
            pregunta.setCuestionario(cuestionario);
            

            boolean resp = preguntaDAO.agregarPregunta(pregunta);
            if (resp == true) {
                request.setAttribute("mensaje", "Pregunta Agregada Correctamente");

                try {
                    List<Cuestionario> listado = new CuestionarioDAO().listar_cuestionario();
                    request.setAttribute("cuestionarios", listado);
                    request.getRequestDispatcher("Pregunta.jsp").forward(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(AgregarPregunta.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                request.setAttribute("mensaje", "Error al Agregar la Pregunta");
                try {
                    List<Cuestionario> listado = new CuestionarioDAO().listar_cuestionario();
                    request.setAttribute("cuestionarios", listado);
                    request.getRequestDispatcher("Pregunta.jsp").forward(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(AgregarPregunta.class.getName()).log(Level.SEVERE, null, ex);
                }

                request.getRequestDispatcher("Pregunta.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("mensaje", "Error al Agregar la pregunta : " + e.getMessage());
            try {
                List<Cuestionario> listado = new CuestionarioDAO().listar_cuestionario();
                request.setAttribute("cuestionarios", listado);
                request.getRequestDispatcher("Pregunta.jsp").forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(AgregarPregunta.class.getName()).log(Level.SEVERE, null, ex);
            }

            request.getRequestDispatcher("Pregunta.jsp").forward(request, response);
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
