/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet.Cuestionario;

import DAO.CompetenciaDAO;
import DAO.CuestAsigDAO;
import DAO.CuestionarioDAO;
import DAO.UsuarioDAO;
import Entities.Competencia;
import Entities.CuestAsig;
import Entities.Cuestionario;
import Entities.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.catalina.Session;

/**
 *
 * @author EduardoGatica
 */
@WebServlet(name = "modificarCuestionario", urlPatterns = {"/modificarCuestionario"})
public class modificarCuestionario extends HttpServlet {

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
            int idCuestionario = Integer.parseInt(request.getParameter("id"));
            Cuestionario cuestionario = new CuestionarioDAO().buscarCuestionario(idCuestionario);
            request.setAttribute("cuestionario", cuestionario);
            request.getRequestDispatcher("ModificarCuestionario.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(modificarCuestionario.class.getName()).log(Level.SEVERE, null, ex);
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
            
            int idCuest = Integer.parseInt(request.getParameter("txtIdCuestionario"));
            int porcEval = Integer.parseInt(request.getParameter("txtPorcentajeEvaluado"));
            int porcJefe = Integer.parseInt(request.getParameter("txtPorcentajeJefe"));
            int total = porcEval + porcJefe;
        try {
            
            

            if (total == 100) {
                boolean resp = new CuestionarioDAO().modificarCuestionario(idCuest, porcEval, porcJefe);
                if (resp == true) {
                    request.setAttribute("mensaje", "Cuestionario Modificado Correctamente");

                    List<Cuestionario> listCuest = new CuestionarioDAO().listar_cuestionario();
                    request.setAttribute("cuestionarios", listCuest);
                    request.getRequestDispatcher("ListadoCuestionario.jsp").forward(request, response);

                } else {
                    request.setAttribute("mensaje", "Error al Modificar el Cuestionario. La suma de los porcentaje debe ser igual a 100");
                    int idCuestionario = Integer.parseInt(request.getParameter("id"));
                    Cuestionario cuestionario = new CuestionarioDAO().buscarCuestionario(idCuestionario);
                    request.setAttribute("cuestionario", cuestionario);
                    request.getRequestDispatcher("ModificarCuestionario.jsp").forward(request, response);
                }

            } else {
                request.setAttribute("mensaje", "Error al Modificar el Cuestionario. La suma de los porcentaje debe ser igual a 100");
                int idCuestionario = Integer.parseInt(request.getParameter("id"));
                Cuestionario cuestionario = new CuestionarioDAO().buscarCuestionario(idCuestionario);
                request.setAttribute("cuestionario", cuestionario);
                request.getRequestDispatcher("ModificarCuestionario.jsp").forward(request, response);

            }

        } catch (Exception e) {
            request.setAttribute("mensaje", "Error al Modificar el Cuestionario: " + e.getMessage());
            try {
                List<Cuestionario> listCuest = new CuestionarioDAO().listar_cuestionario();
                request.setAttribute("cuestionarios", listCuest);

            } catch (SQLException ex) {
                Logger.getLogger(modificarCuestionario.class.getName()).log(Level.SEVERE, null, ex);
            }

            request.getRequestDispatcher("ListadoCuestionario.jsp").forward(request, response);
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
