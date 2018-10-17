package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class Header_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>JSP Page</title>\n");
      out.write("    </head>\n");
      out.write("    <header class=\"jumbotron\">\n");
      out.write("        \n");
      out.write("        <div class=\"container-fluid\" align=\"center\" style=\"background-color: green!important\">\n");
      out.write("        <h1>Hola ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${usuario.getPersona().getNombrePersona()}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write(" Usuario. sitio en construccion</h1>\n");
      out.write("        </div>\n");
      out.write("         <nav class=\"navbar navbar-expand-sm bg-dark navbar-dark\">\n");
      out.write("                <!-- Brand -->\n");
      out.write("                <a class=\"navbar-brand\" href=\"#\">Logo</a>\n");
      out.write("\n");
      out.write("                <!-- Links -->\n");
      out.write("                <ul class=\"navbar-nav\">\n");
      out.write("                   <!-- Dropdown -->\n");
      out.write("                    <li class=\"nav-item dropdown\">\n");
      out.write("                        <a class=\"nav-link dropdown-toggle\" href=\"#\" id=\"navbardrop\" data-toggle=\"dropdown\">\n");
      out.write("                            Cuestionarios\n");
      out.write("                        </a>\n");
      out.write("                        <div class=\"dropdown-menu\">\n");
      out.write("                            <a class=\"dropdown-item\" href=\"cuestionario\">Nuevo Cuestionario</a>\n");
      out.write("                            <a class=\"dropdown-item\" href=\"listarCuestionario\">Listar Cuestionarios</a>\n");
      out.write("                            <a class=\"dropdown-item\" href=\"asignarCuestionario\">Asignar Cuestionarios</a>\n");
      out.write("                            \n");
      out.write("                        </div>\n");
      out.write("                        \n");
      out.write("                       \n");
      out.write("                        \n");
      out.write("                    </li>\n");
      out.write("                    <li class=\"nav-item dropdown\">\n");
      out.write("                         <a class=\"nav-link dropdown-toggle\" href=\"#\" id=\"navbardrop\" data-toggle=\"dropdown\">\n");
      out.write("                            Preguntas\n");
      out.write("                        </a>\n");
      out.write("                        <div class=\"dropdown-menu\">\n");
      out.write("                            <a class=\"dropdown-item\" href=\"pregunta\">Agregar Preguntas</a>\n");
      out.write("                            <a class=\"dropdown-item\" href=\"listarPregunta\">Listar Preguntas</a>\n");
      out.write("                            \n");
      out.write("                        </div>\n");
      out.write("                        \n");
      out.write("                    </li>\n");
      out.write("                    \n");
      out.write("                    <li class=\"nav-item dropdown\">\n");
      out.write("                         <a class=\"nav-link dropdown-toggle\" href=\"#\" id=\"navbardrop\" data-toggle=\"dropdown\">\n");
      out.write("                            Respuestas\n");
      out.write("                        </a>\n");
      out.write("                        <div class=\"dropdown-menu\">\n");
      out.write("                            <a class=\"dropdown-item\" href=\"respuesta\">Agregar Respuestas</a>\n");
      out.write("                            <a class=\"dropdown-item\" href=\"listarRespuesta\">Listar Respuestas</a>\n");
      out.write("                            \n");
      out.write("                        </div>\n");
      out.write("                        \n");
      out.write("                    </li>\n");
      out.write("                    <li class=\"nav-item dropdown\">\n");
      out.write("                         <a class=\"nav-link dropdown-toggle\" href=\"#\" id=\"navbardrop\" data-toggle=\"dropdown\">\n");
      out.write("                            Funcionarios\n");
      out.write("                        </a>\n");
      out.write("                        <div class=\"dropdown-menu\">\n");
      out.write("                            <a class=\"dropdown-item\" href=\"listarFuncionario\">Listar Funcionarios</a>\n");
      out.write("                            \n");
      out.write("                            \n");
      out.write("                        </div>\n");
      out.write("                        \n");
      out.write("                    </li>\n");
      out.write("                    \n");
      out.write("                </ul>\n");
      out.write("            </nav> \n");
      out.write("        \n");
      out.write("        \n");
      out.write("    </header>\n");
      out.write("      \n");
      out.write("\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
