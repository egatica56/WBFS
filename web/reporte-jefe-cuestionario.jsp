<%-- 
    Document   : reporte-jefe-cuestionario
    Created on : 24-11-2018, 22:08:05
    Author     : Alejandro
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" media="screen"/>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js" type="text/javascript"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
    <style>
      .asignada {
        color: blue;
      }      
      .respondida {
        color: green;
      }      
    </style>
  </head>
  <body>
    <div class="container">
      <h1>Reporte</h1>
      <table class="table">
        <thead>
          <tr>
            <th>Nombre</th>
              <c:forEach var="cuestAsig" items="${cuestionariosPorRutJefe}">
              <th>Cuest. ${cuestAsig.getIdCuestAsig()}</th>
              </c:forEach>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="e" items="${evaluacionXjefe}">
            <tr>
              <td>${e.getPersona().getNombrePersona()}</td>
              <c:forEach var="cuestAsig" items="${cuestionariosPorRutJefe}">
                <td>
                  <c:set var="flag" value="${e.getCuestAsig().getIdCuestAsig() == cuestAsig.getIdCuestAsig()}"></c:set>
                  <c:set var="flagNota" value="${e.getNotaEvaluacion() > 0}"></c:set>
                  <c:if test="${flag}">
                    <!--<span class="glyphicon glyphicon-ok"></span>-->
                    <span class="glyphicon ${flagNota?'glyphicon glyphicon-ok-circle respondida':'glyphicon glyphicon-remove-circle asignada'}"></span>
                  </c:if>
                  <c:if test="${!flag}">
                    <span class="glyphicon glyphicon-ban-circle" style="color:red"></span>
                  </c:if>
                </td>
              </c:forEach>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </div>
  </body>
</html>
