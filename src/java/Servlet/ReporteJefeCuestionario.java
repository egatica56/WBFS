/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import DAO.CuestAsigDAO;
import DAO.EvaluacionDAO;
import Entities.CuestAsig;
import Entities.Evaluacion;
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
 * @author Alejandro
 */
@WebServlet(name = "ReporteJefeCuestionario", urlPatterns = {"/reporte-jefe"})
public class ReporteJefeCuestionario extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      CuestAsigDAO cuestAsigDao = new CuestAsigDAO();
      List<CuestAsig> cuestionariosPorRutJefe = cuestAsigDao.cuestionariosPorRutJefe("18882760-8");
      EvaluacionDAO evaluacionDao = new EvaluacionDAO();
      List<Evaluacion> evaluacionXjefe = evaluacionDao.listarEvaluacionXJefe("18882760-8");
      evaluacionXjefe.get(0).getNotaEvaluacion();

      cuestionariosPorRutJefe.forEach(e -> {
        System.out.println("CuestPorRut : " + e.getIdCuestAsig());
      });
      evaluacionXjefe.forEach(e -> {
        System.out.println("getNotaEvaluacion : " + e.getNotaEvaluacion());
      });

      cuestionariosPorRutJefe.get(0).getIdCuestAsig();
      request.setAttribute("evaluacionXjefe", evaluacionXjefe);
      request.setAttribute("cuestionariosPorRutJefe", cuestionariosPorRutJefe);
      request.getRequestDispatcher("reporte-jefe-cuestionario.jsp").forward(request, response);
    } catch (SQLException ex) {
      Logger.getLogger(ReporteJefeCuestionario.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  }
}
