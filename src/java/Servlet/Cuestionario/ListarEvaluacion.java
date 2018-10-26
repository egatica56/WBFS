/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet.Cuestionario;

import DAO.CuestAsigDAO;
import DAO.EvaluacionDAO;
import Entities.CuestAsig;
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
@WebServlet(name = "ListarEvaluacion", urlPatterns = {"/listarEvaluacion"})
public class ListarEvaluacion extends HttpServlet {

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
            int rol= usuario.getTipoUsuario().getIdTipoUsuario();
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
            request.getRequestDispatcher("ListadoEvaluaciones.jsp").forward(request, response);
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
