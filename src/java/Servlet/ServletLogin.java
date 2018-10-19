/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import DAO.CompetenciaDAO;
import DAO.UsuarioDAO;
import Entities.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author EduardoGatica
 */
@WebServlet(name = "ServletLogin", urlPatterns = {"/login"})
public class ServletLogin extends HttpServlet {

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

        request.getRequestDispatcher("login.jsp").forward(request, response);
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

        String nombreUsuario = request.getParameter("txtUsuario");
        String password = request.getParameter("txtPassword");

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario;

        try {
            usuario = usuarioDAO.login(nombreUsuario, password);

            if (usuario.getPassword() != null) {
                //obtnenemos la sesion
                HttpSession sesion = request.getSession();
                sesion.setAttribute("usuario", usuario);

                if (usuario.getTipoUsuario().getIdTipoUsuario() == 1) {
                    request.setAttribute("mensaje", "Login Ok Como Admin");
                    request.setAttribute("usuario", usuario);
                    request.getRequestDispatcher("Main.jsp").forward(request, response);
                    //response.sendRedirect("Demo.jsp");
                    return;

                } else if ((usuario.getTipoUsuario().getIdTipoUsuario() == 2)) {
                    request.setAttribute("mensaje", "Login Ok Como Jefe");
                    request.setAttribute("usuario", usuario);
                    request.getRequestDispatcher("Main.jsp").forward(request, response);
                    return;
                    //request.getRequestDispatcher("login.jsp").forward(request, response);

                } else if ((usuario.getTipoUsuario().getIdTipoUsuario() == 3)) {
                    request.setAttribute("mensaje", "Login Ok Como Empleado");
                    request.setAttribute("usuario", usuario);
                    request.getRequestDispatcher("Main.jsp").forward(request, response);
                    return;
                    //request.getRequestDispatcher("login.jsp").forward(request, response);

                } else {
                    request.setAttribute("mensaje", "Error no existe el empleado.");
                    request.getRequestDispatcher("login.jsp").forward(request, response);

                }

            } else {

                request.setAttribute("mensaje", "Credenciales Invalidas");
                request.getRequestDispatcher("login.jsp").forward(request, response);
                return;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServletLogin.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("mensaje", "Error. usuario inexistente");
            request.getRequestDispatcher("login.jsp").forward(request, response);
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
