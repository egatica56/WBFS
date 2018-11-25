<%-- 
    Document   : ListadoCuestionario
    Created on : 12-10-2018, 10:39:17
    Author     : EduardoGatica
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listado de Evaluaciones</title>
    </head>
    <jsp:include page="Header.jsp"></jsp:include>
        <body>

        <c:if  test="${usuario.getTipoUsuario().getIdTipoUsuario()==2}">
            <h1 align="center" class="">Acá podrás visualizar las evaluaciones previamente asignadas a tus empleados</h1>
        </c:if>    
        <c:if  test="${usuario.getTipoUsuario().getIdTipoUsuario()==3}">
            <h1 align="center" class="">Acá podrás visualizar Tus evaluaciones asignadas</h1>
        </c:if>    
        
        <div align center class="container-fluid">
            <br>
            <div class="container" align="center">
                ${mensaje}
            </div>
            <br>              
            <form action="pintarEvaluacion" method="get">
                <div class="container" align="center">
                    <table class="table">
                        <tr align="center">
                            <th align="center">ID Evaluacion</th>
                            <th align="center">Rut Jefe</th>
                            <th align="center">Persona a Evaluar</th>
                            <th align="center">Fecha Evaluacion</th>
                            <th align="center">Id Cuestionario Asignado</th>
                            <th align="center" colspan="2">Opciones</th>
                        </tr>
                        <!--comentario-->
                        <c:forEach items="${evaluaciones}" var="evaluacion">

                            <c:if test="${evaluacion.getControlEstados().getIdEstado()==1}">

                                <tr align="center">    
                                    <td><input type="hidden" value="${evaluacion.getIdEvaluacion()}" name="txtIdEvaluacion" id="txtIdEvaluacion">${evaluacion.getIdEvaluacion()}</td>
                                    <td><input type="hidden" value="${evaluacion.getIdEvaluacion()}" name="txtRutJefe" id="txtRutJefe">${evaluacion.getRutJefe()}</td>

                                    <td align="center">${evaluacion.getPersona().getNombrePersona()}</td>
                                    <td align="center">${evaluacion.getFechaEvaluacion()}</td>
                                    <td align="center">${evaluacion.getCuestAsig().getIdCuestAsig()}</td>

                                    <c:choose>
                                        <c:when test="${usuario.getTipoUsuario().getIdTipoUsuario()==2}">
                                            <td align="center">
                                                <a class="btn btn-primary" href="pintarEvaluacion?accion=verCuestionario&idC=${evaluacion.getCuestAsig().getIdCuestAsig()}&rutP=${evaluacion.getPersona().getRutPersona()}&idE=${evaluacion.getIdEvaluacion()}&rutJ=${evaluacion.getRutJefe()}">Responder</a>
                                                <a class="btn btn-primary" href="eliminarCuestionario?id=${evaluacion.getIdEvaluacion()}">Eliminar</a>
                                            </td>
                                        </c:when>
                                        <c:when test="${usuario.getTipoUsuario().getIdTipoUsuario()==3}">
                                            <td align="center">
                                                <a class="btn btn-primary" href="pintarEvaluacion?accion=verCuestionario&idC=${evaluacion.getCuestAsig().getIdCuestAsig()}&rutP=${evaluacion.getPersona().getRutPersona()}&idE=${evaluacion.getIdEvaluacion()}&rutJ=${evaluacion.getRutJefe()}">Responder</a>

                                            </td>
                                        </c:when>
                                    </c:choose>
                                </tr>
                            </c:if>
                        </c:forEach>

                    </table>
                </div>

            </form>
        </div>
    </body>

    <jsp:include page="Footer.jsp"></jsp:include>    

</html>