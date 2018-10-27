/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet.Cuestionario;

import DAO.CuestAsigDAO;
import DAO.CuestionarioDAO;
import DAO.EvaluacionDAO;
import DAO.PersonaDAO;
import DAO.PreguntaDAO;
import DAO.RespuestaDAO;
import Entities.Competencia;
import Entities.CuestAsig;
import Entities.Cuestionario;
import Entities.Evaluacion;
import Entities.OpcionRespuesta;
import Entities.Persona;
import Entities.Pregunta;
import Entities.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import oracle.jdbc.proxy.annotation.Pre;
import org.apache.catalina.Session;

/**
 *
 * @author EduardoGatica
 */
@WebServlet(name = "PintarEvaluacion", urlPatterns = {"/pintarEvaluacion"})
public class PintarEvaluacion extends HttpServlet {

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

        String accion = request.getParameter("accion");
        if (accion == null) {
//            List<Evaluacion> lstEvaluaciones = new EvaluacionDAO().listarEvaluacionXJefe(accion)
            //          request.setAttribute("lstEvaluaciones", lstEvaluaciones);
            request.getRequestDispatcher("PantallaEvaluacion.jsp").forward(request, response);
        }
        /*switch (accion) {
            case "verCuestionario":
                verCuestionario(request, response);
                break;
            case "calcularCuestionario":
                calcularCuestionario(request, response);
                break;    
        } */
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
            String idC = request.getParameter("idC"); //id cuestionario asignado
            String rutP = request.getParameter("rutP"); //rut persona
            String idE = request.getParameter("idE"); //rut persona
            System.out.println("Id Evaluacion para validar nota: " + idE);
            Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
            int rol = usuario.getTipoUsuario().getIdTipoUsuario();
            String rut=usuario.getUsername();
            if (rol == 2) {

                Evaluacion eval = new EvaluacionDAO().validarNotaJefe(Integer.parseInt(idE), usuario.getUsername());
                if (eval.getNotaJefe()!= 0) {
                    request.setAttribute("mensaje", "Esta encuesta ya se ha respondido. intenta con otra");
                    List<Evaluacion> evaluacion = new EvaluacionDAO().listarEvaluacionXJefe(rut);
                    request.setAttribute("evaluaciones", evaluacion);
                    request.getRequestDispatcher("ListadoEvaluacion.jsp").forward(request, response);
                } else {
                    CuestAsig cuestAsig = new CuestAsigDAO().buscarCuestAsig(Integer.parseInt(idC)); // aplicar metodo buscar en el cuestasig
                    Competencia competencia = cuestAsig.getCuestionario().getCompetencia();
                    Persona jefe = new PersonaDAO().buscarPersona(cuestAsig.getRutJefe());// crear persona dao y metodos asociados package etc en bd
                    Persona persona = new PersonaDAO().buscarPersona(rutP);
                    Collection<Pregunta> preguntas = cuestAsig.getCuestionario().getPreguntaCollection();

                    request.setAttribute("cuestAsig", cuestAsig);
                    request.setAttribute("competencia", competencia);
                    request.setAttribute("jefe", jefe);
                    request.setAttribute("persona", persona);
                    request.setAttribute("preguntas", preguntas);

                    request.getRequestDispatcher("PantallaEvaluacion.jsp").forward(request, response);
                }
            }
            if (rol == 3) {
                Evaluacion evalemp = new EvaluacionDAO().validarNotaEmp(Integer.parseInt(idE), usuario.getUsername());
                System.out.println("Nota Empleado: "+evalemp.getNotaFuncionario() );
                if (evalemp.getNotaFuncionario()!= 0) {
                    request.setAttribute("mensaje", "Esta encuesta ya se ha respondido. intenta con otra");
                    List<Evaluacion> evaluacion = new EvaluacionDAO().listarEvaluacionXEmp(rut);
                    request.setAttribute("evaluaciones", evaluacion);
                    request.getRequestDispatcher("ListadoEvaluaciones.jsp").forward(request, response);
                } else {
                    CuestAsig cuestAsig = new CuestAsigDAO().buscarCuestAsig(Integer.parseInt(idC)); // aplicar metodo buscar en el cuestasig
                    Competencia competencia = cuestAsig.getCuestionario().getCompetencia();
                    Persona jefe = new PersonaDAO().buscarPersona(cuestAsig.getRutJefe());// crear persona dao y metodos asociados package etc en bd
                    Persona persona = new PersonaDAO().buscarPersona(rutP);
                    Collection<Pregunta> preguntas = cuestAsig.getCuestionario().getPreguntaCollection();

                    request.setAttribute("cuestAsig", cuestAsig);
                    request.setAttribute("competencia", competencia);
                    request.setAttribute("jefe", jefe);
                    request.setAttribute("persona", persona);
                    request.setAttribute("preguntas", preguntas);

                    request.getRequestDispatcher("PantallaEvaluacion.jsp").forward(request, response);

                }

            } else {
                request.setAttribute("mensaje", "Los administradores no pueden responder su encuesta");
                request.getRequestDispatcher("ListadoEvaluaciones.jsp").forward(request, response);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
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
            Enumeration<String> preguntas = request.getParameterNames();
            int puntajeFinal = 0;
            while (preguntas.hasMoreElements()) {
                String idRespuesta = preguntas.nextElement();
                puntajeFinal += new RespuestaDAO().buscarRespuesta(Integer.parseInt(request.getParameter(idRespuesta))).getPorcentajeRespuesta();
            }

            int nota = 0;
            if (0 < puntajeFinal && puntajeFinal < 11) {
                nota = 1;
            } else if (puntajeFinal >= 11 || puntajeFinal == 20) {
                nota = 2;
            } else if (puntajeFinal >= 21 || puntajeFinal == 30) {
                nota = 3;
            } else if (puntajeFinal >= 31 || puntajeFinal == 40) {
                nota = 4;
            } else if (puntajeFinal >= 41 || puntajeFinal == 50) {
                nota = 5;
            }

            Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
            String rut = usuario.getUsername();
            int rol = usuario.getTipoUsuario().getIdTipoUsuario();
            int idEvaluacion = Integer.parseInt(request.getParameter("idE"));
            if (rol == 2) {
                System.out.println("rut: jefe: " + rut);
                System.out.println("Rol Persona: " + rol);
                System.out.println("Id Evaluacion: " + idEvaluacion);

                boolean resp = new EvaluacionDAO().actualizarNotaJefe(nota, idEvaluacion, rut);
                if (resp) {
                    System.out.println("Ingreso Ok");
                    request.setAttribute("Mensaje", "Evaluacion Evaluada correctamente. Nota Obtenida: " + nota);

                } else {
                    System.out.println("no Ok");
                    request.setAttribute("Mensaje", "Error al intentar evaluar.");

                }
                request.getRequestDispatcher("ListadoEvaluaciones.jsp").forward(request, response);

            } else if (rol == 3) {
                System.out.println("rut: empleado: " + rut);
                System.out.println("Rol Persona: " + rol);
                System.out.println("Id Evaluacion: " + idEvaluacion);
                boolean resp = new EvaluacionDAO().actualizarNotaEmpleado(nota, idEvaluacion, rut);
                if (resp) {
                    System.out.println("Ingreso Ok");
                    request.setAttribute("Mensaje", "Evaluacion Evaluada correctamente. Nota Obtenida: " + nota);

                } else {
                    System.out.println("no Ok");
                    request.setAttribute("Mensaje", "Error al intentar evaluar.");

                }
                //request.getRequestDispatcher("ListadoEvaluaciones.jsp").forward(request, response);
            } else {

            }
            response.getWriter().println("Puntaje final : " + puntajeFinal);
            System.out.println("Puntaje Final: " + puntajeFinal);
            System.out.println("Nota Final: " + nota);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(PintarEvaluacion.class.getName()).log(Level.SEVERE, null, ex);
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

    /*private void verCuestionario(HttpServletRequest request, HttpServletResponse response) {
        try {
            String idC = request.getParameter("idC"); //id cuestionario asignado
            String rutP = request.getParameter("rutP"); //rut persona
            
            
            CuestAsig cuestAsig = new CuestAsigDAO().buscarCuestAsig(Integer.parseInt(idC)); // aplicar metodo buscar en el cuestasig
            Competencia competencia = cuestAsig.getCuestionario().getCompetencia();
            Persona jefe = new PersonaDAO().buscarPersona(cuestAsig.getRutJefe());// crear persona dao y metodos asociados package etc en bd
            Persona persona = new PersonaDAO().buscarPersona(rutP);
            Collection<Pregunta> preguntas = cuestAsig.getCuestionario().getPreguntaCollection();

            request.setAttribute("cuestAsig", cuestAsig);
            request.setAttribute("competencia", competencia);
            request.setAttribute("jefe", jefe);
            request.setAttribute("persona", persona);
            request.setAttribute("preguntas", preguntas);

            request.getRequestDispatcher("PantallaEvaluacion.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }*/
    private void calcularCuestionario(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
