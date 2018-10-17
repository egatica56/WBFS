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
import jdk.nashorn.internal.objects.NativeString;
import org.apache.naming.java.javaURLContextFactory;

/**
 *
 * @author EduardoGatica
 */
@WebServlet(name = "AsignarCuestionario", urlPatterns = {"/asignarCuestionario"})
public class AsignarCuestionario extends HttpServlet {

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
            List<Cuestionario> cuestionario = new CuestionarioDAO().listar_cuestionario();
            request.setAttribute("cuestionarios", cuestionario);
            List<Usuario> listado = new UsuarioDAO().listar_usuario();
            request.setAttribute("usuarios", listado);
            request.getRequestDispatcher("AsignarCuestionario.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AsignarCuestionario.class.getName()).log(Level.SEVERE, null, ex);
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

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String fechaIngreso = request.getParameter("txtFechaInicio");
            System.out.println("fecha inicio Rescatada  Web: " + fechaIngreso);

            String fechaTermino = request.getParameter("txtFechaTermino");
            System.out.println("fecha termino Rescatada Web: " + fechaTermino);

            CuestAsig cuestAsig = new CuestAsig();
            CuestAsigDAO cuestAsigDAO = new CuestAsigDAO();
            Cuestionario cuest = new Cuestionario();
            String rutJefe = request.getParameter("txtRutJefe");
            int idCuest = Integer.parseInt(request.getParameter("cboCuestionario"));
            String estado = request.getParameter("cboEstado");

            cuest.setIdCuest(idCuest);
            cuestAsig.setCuestionario(cuest);
            cuestAsig.setFechaInicio(fechaIngreso);
            cuestAsig.setFechaTermino(fechaTermino);
            cuestAsig.setEstadoCuestionarioAsig(estado);
            cuestAsig.setRutJefe(rutJefe);

            boolean resp = cuestAsigDAO.agregarCuestAsig(cuestAsig);
            if (resp) {
                System.out.println("Registro Agregado");
                request.setAttribute("mensaje", "Cuestionario asignado Correctaente");
                try {
                    List<Cuestionario> cuestionario = new CuestionarioDAO().listar_cuestionario();
                    request.setAttribute("cuestionarios", cuestionario);
                    request.getRequestDispatcher("AsignarCuestionario.jsp").forward(request, response);
                } catch (SQLException ex) {
                    request.setAttribute("mensaje", "Error al asignar cuestionarios: " + ex.getMessage());
                    List<Cuestionario> cuestionario = new CuestionarioDAO().listar_cuestionario();
                    request.setAttribute("cuestionarios", cuestionario);
                    request.getRequestDispatcher("AsignarCuestionario.jsp").forward(request, response);
                    Logger.getLogger(AsignarCuestionario.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                System.out.println("Registro No Agregado");
                request.setAttribute("mensaje", "Error al asignar el cuestionario");
                try {
                    List<Cuestionario> cuestionario = new CuestionarioDAO().listar_cuestionario();
                    request.setAttribute("cuestionarios", cuestionario);
                    request.getRequestDispatcher("AsignarCuestionario.jsp").forward(request, response);
                } catch (SQLException ex) {
                    request.setAttribute("mensaje", "Error al asignar cuestionarios: " + ex.getMessage());
                    List<Cuestionario> cuestionario = new CuestionarioDAO().listar_cuestionario();
                    request.setAttribute("cuestionarios", cuestionario);
                    request.getRequestDispatcher("AsignarCuestionario.jsp").forward(request, response);
                    Logger.getLogger(AsignarCuestionario.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            

        } catch (SQLException ex) {
            request.setAttribute("mensaje", "Error al asignar cuestionarios: " + ex.getMessage());
            Logger.getLogger(AsignarCuestionario.class.getName()).log(Level.SEVERE, null, ex);
            request.getRequestDispatcher("AsignarCuestionario.jsp").forward(request, response);
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
