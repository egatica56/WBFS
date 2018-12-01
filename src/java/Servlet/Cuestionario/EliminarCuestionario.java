/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet.Cuestionario;

import DAO.CuestionarioDAO;
import DAO.EvaluacionDAO;
import Entities.Cuestionario;
import Entities.Evaluacion;
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
@WebServlet(name = "EliminarCuestionario", urlPatterns = {"/eliminarCuestionario"})
public class EliminarCuestionario extends HttpServlet {

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
            System.out.println("idCuestionario: " + idCuestionario);

            CuestionarioDAO cuest = new CuestionarioDAO();
            boolean resp = cuest.eliminarCuestionario(idCuestionario);

            if (resp) {
                System.out.println("Cuestionario Eliminado Correctamente");
                request.setAttribute("mensaje", "Cuestionario eliminado correctamente.");

                try {
                    List<Cuestionario> cuestionario = new CuestionarioDAO().listar_cuestionario();
                    request.setAttribute("cuestionarios", cuestionario);
                    request.getRequestDispatcher("ListadoCuestionario.jsp").forward(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(ListarCuestionario.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                System.out.println("Cuestionario No Eliminado Correctamente");
                request.setAttribute("mensaje", "Error al intentar eliminar cuestionario.");

                
                try {
                    List<Cuestionario> cuestionario = new CuestionarioDAO().listar_cuestionario();
                    request.setAttribute("cuestionarios", cuestionario);
                    request.getRequestDispatcher("ListadoCuestionario.jsp").forward(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(ListarCuestionario.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } catch (Exception e) {
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
