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
@WebServlet(name = "AgregarCuestionario", urlPatterns = {"/cuestionario"})
public class AgregarCuestionario extends HttpServlet {

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
            List<Competencia> listado = new CompetenciaDAO().listarCompetencias();
            request.setAttribute("competencias", listado);
            request.getRequestDispatcher("Cuestionario.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AgregarCuestionario.class.getName()).log(Level.SEVERE, null, ex);
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
            int total = porcEval + porcJefe;

            if (total == 100) {
                int idComp = Integer.parseInt(request.getParameter("cboCompetencia"));
                competencia.setIdComp(idComp);
                //cuestionario.setIdCuest(idCuest);
                cuestionario.setPorcentajeJefe(porcJefe);
                cuestionario.setPorcentajeAutoevaluacion(porcEval);
                cuestionario.setCompetencia(competencia);

                boolean resp = cuestionarioDAO.agregarCuestionario(cuestionario);
                if (resp == true) {
                    request.setAttribute("mensaje", "Cuestionario Agregado Correctamente");
                    

                    String fechaIngreso = request.getParameter("txtFechaInicio");
                    String fechaTermino = request.getParameter("txtFechaTermino");
                    String rutJefe = request.getParameter("txtRutJefe");
                    String estadoCuest = request.getParameter("cboEstado");
                    Cuestionario cuest = new CuestionarioDAO().buscarCuestionario(cuestionario.getIdCuest());

                    System.out.println("cuest: " + cuest.getIdCuest());

                    System.out.println("fecha inicio Rescatada  Web: " + fechaIngreso);
                    System.out.println("fecha termino Rescatada Web: " + fechaTermino);

                    CuestAsig cuestAsig = new CuestAsig();
                    CuestAsigDAO cuestAsigDAO = new CuestAsigDAO();

                    cuestAsig.setCuestionario(cuest);
                    cuestAsig.setFechaInicio(fechaIngreso);
                    cuestAsig.setFechaTermino(fechaTermino);
                    cuestAsig.setEstadoCuestionarioAsig(estadoCuest);
                    cuestAsig.setRutJefe(rutJefe);

                    boolean resp1 = cuestAsigDAO.agregarCuestAsig(cuestAsig);
                    if (resp1) {
                        System.out.println("Registro Agregado");
                        request.setAttribute("mensaje", "Cuestionario Asignado Correctamente");
                        List<Cuestionario> listCuest = new CuestionarioDAO().listar_cuestionario();
                        List<Competencia> listado = new CompetenciaDAO().listarCompetencias();
                        request.setAttribute("cuestionarios", listCuest);
                        request.setAttribute("competencias", listado);
                        request.getRequestDispatcher("Cuestionario.jsp").forward(request, response);
                    } else {
                        request.setAttribute("mensaje", "Error al Asignar Cuestionario");
                        System.out.println("Registro No Agregado");
                        List<Cuestionario> listCuest = new CuestionarioDAO().listar_cuestionario();
                        List<Competencia> listado = new CompetenciaDAO().listarCompetencias();
                        request.setAttribute("cuestionarios", listCuest);
                        request.setAttribute("competencias", listado);
                        request.getRequestDispatcher("Cuestionario.jsp").forward(request, response);
                    }

                }

            } else {
                request.setAttribute("mensaje", "Error al Agregar el Cuestionario. La suma de los porcentaje debe ser igual a 100");
                List<Cuestionario> listCuest = new CuestionarioDAO().listar_cuestionario();
                List<Competencia> listado = new CompetenciaDAO().listarCompetencias();
                request.setAttribute("cuestionarios", listCuest);
                request.setAttribute("competencias", listado);
                request.getRequestDispatcher("Cuestionario.jsp").forward(request, response);
            }

        } catch (Exception e) {
            request.setAttribute("mensaje", "Error al Agregar el Cuestionario: " + e.getMessage());
            try {
                List<Cuestionario> listCuest = new CuestionarioDAO().listar_cuestionario();
                List<Competencia> listado = new CompetenciaDAO().listarCompetencias();
                request.setAttribute("cuestionarios", listCuest);
                request.setAttribute("competencias", listado);
            } catch (SQLException ex) {
                Logger.getLogger(AgregarCuestionario.class.getName()).log(Level.SEVERE, null, ex);
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
