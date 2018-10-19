/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet.Cuestionario;

import DAO.CompetenciaDAO;
import DAO.CuestAsigDAO;
import DAO.CuestionarioDAO;
import DAO.EvaluacionDAO;
import DAO.UsuarioDAO;
import Entities.Competencia;
import Entities.CuestAsig;
import Entities.Cuestionario;
import Entities.Evaluacion;
import Entities.Persona;
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
import org.apache.catalina.Session;
import org.apache.catalina.ant.SessionsTask;
import org.apache.naming.java.javaURLContextFactory;

/**
 *
 * @author EduardoGatica
 */
@WebServlet(name = "AsignarEvaluacion", urlPatterns = {"/asignarEvaluacion"})
public class AsignarEvaluacion extends HttpServlet {

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
            List<CuestAsig> cuestAsig = new CuestAsigDAO().listar_cuest_asig();
            request.setAttribute("cuestionarios", cuestAsig);
            Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
            String rutJefe = usuario.getUsername();
            System.out.println("Rut Jefe a usar en el listado: " + rutJefe);
            List<Usuario> listado = new EvaluacionDAO().listarPersonasPorJefe(rutJefe);
            request.setAttribute("usuarios", listado);
            request.getRequestDispatcher("AsignarEvaluacion.jsp").forward(request, response);
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

        try {

            
            String fechaEvaluacion = request.getParameter("txtFechaEvaluacion");
            System.out.println("fecha Evaluacion Rescatada  Web: " + fechaEvaluacion);
            String rutJefe = request.getParameter("txtRutJefe");
            int idCuestAsig = Integer.parseInt(request.getParameter("cboCuestionario"));
            System.out.println("Id_cuestionario asignado: " + idCuestAsig);
            
            Evaluacion evaluacion = new Evaluacion();
            EvaluacionDAO evaluacionDAO = new EvaluacionDAO();
            CuestAsig cuestAsig = new CuestAsig();
            CuestAsigDAO cuestAsigDAO = new CuestAsigDAO();
            
            String[] us = request.getParameterValues("chkUsuario");

            for (String u : us) {
                Persona persona = new Persona();
                persona.setRutPersona(u);
                System.out.println("rutPersona: " + u);
                cuestAsig.setIdCuestAsig(idCuestAsig);
                evaluacion.setFechaEvaluacion(fechaEvaluacion);
                evaluacion.setRutJefe(rutJefe);
                evaluacion.setCuestAsig(cuestAsig);
                evaluacion.setPersona(persona);
                boolean resp = evaluacionDAO.agregarEvaluacion(evaluacion);
                if (resp) {
                    System.out.println("Registro Agregado");
                } else {
                    System.out.println("Registro No Agregado");
                }
                

                try {
                    request.setAttribute("mensaje", "Evaluacion Asignada Correctamente");
                    List<CuestAsig> cuAsig = new CuestAsigDAO().listar_cuest_asig();
                    request.setAttribute("cuestionarios", cuAsig);
                    List<Usuario> listado = new EvaluacionDAO().listarPersonasPorJefe(rutJefe);
                    request.setAttribute("usuarios", listado);
                    request.getRequestDispatcher("AsignarEvaluacion.jsp").forward(request, response);
                } catch (SQLException ex) {
                    request.setAttribute("mensaje", "Error al asignar cuestionarios: " + ex.getMessage());
                    List<CuestAsig> cuAsig = new CuestAsigDAO().listar_cuest_asig();
                    request.setAttribute("cuestionarios", cuAsig);
                    List<Usuario> listado = new EvaluacionDAO().listarPersonasPorJefe(rutJefe);
                    request.setAttribute("usuarios", listado);
                    request.getRequestDispatcher("AsignarEvaluacion.jsp").forward(request, response);
                    Logger.getLogger(AsignarEvaluacion.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        } catch (SQLException ex) {

            Logger.getLogger(AsignarEvaluacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /* boolean resp = cuestAsigDAO.agregarCuestAsig(cuestAsig);
            if (resp == true) {
                request.setAttribute("mensaje", "Cuestionario Asignado Correctamente");

                try {
                    List<Cuestionario> cuestionario = new CuestionarioDAO().listar_cuestionario();
                    request.setAttribute("cuestionarios", cuestionario);
                    List<Usuario> listado = new UsuarioDAO().listar_usuario();
                    request.setAttribute("usuarios", listado);
                    request.getRequestDispatcher("AsignarCuestionario.jsp").forward(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(AsignarCuestionario.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                request.setAttribute("mensaje", "Error al Asignar el Cuestionario");
                try {
                    List<Cuestionario> cuestionario = new CuestionarioDAO().listar_cuestionario();
                    request.setAttribute("cuestionarios", cuestionario);
                    List<Usuario> listado = new UsuarioDAO().listar_usuario();
                    request.setAttribute("usuarios", listado);
                    request.getRequestDispatcher("AsignarCuestionario.jsp").forward(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(AsignarCuestionario.class.getName()).log(Level.SEVERE, null, ex);
                }

                request.getRequestDispatcher("Cuestionario.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("mensaje", "Error al Asignar el Cuestionario: " + e.getMessage());
            try {
                List<Cuestionario> cuestionario = new CuestionarioDAO().listar_cuestionario();
                request.setAttribute("cuestionarios", cuestionario);
                List<Usuario> listado = new UsuarioDAO().listar_usuario();
                request.setAttribute("usuarios", listado);
                request.getRequestDispatcher("AsignarCuestionario.jsp").forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(AsignarCuestionario.class.getName()).log(Level.SEVERE, null, ex);
            }

            request.getRequestDispatcher("AsignarCuestionario.jsp").forward(request, response);
        }*/
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
