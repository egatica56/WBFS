<%-- 
    Document   : Cuestionario
    Created on : 06-10-2018, 10:39:17
    Author     : EduardoGatica
--%>

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
        <title>Asignar Evaluaciones.</title>
    </head>
    <jsp:include page="Header.jsp"></jsp:include>    
        <body>

        <c:choose>
            <c:when test="${usuario.getTipoUsuario().getIdTipoUsuario()==2}">
                <h1 align="center" class="">Acá podrás Asignar las evaluaciones a los empleados</h1>
                <br>
                <div align center class="container-fluid">
                    <br>
                    <div align="center">
                        <strong>${mensaje}</strong>
                    </div>
                    <br>    

                    <form action="asignarEvaluacion" method="Post">
                        <div class="container" align="center">
                            <br>
                            <table align="center" style="width: 400px!important">
                                <tr align="left">
                                    <td>Rut Jefe</td>
                                    <td>
                                        <input type="hidden" name="txtRutJefe" id="txtRutJefe" value="${usuario.getUsername()}">
                                        ${usuario.getUsername()}
                                    </td>
                                </tr >
                                <c:forEach items="${usuarios}" var="usu">
                                    <tr align="left">
                                        <td>Usuario a asignar</td>
                                        <td> <input type="checkbox" class="checkbox" name="chkUsuario" id="chkUsuario" value="${usu.getUsername()}">${usu.getUsername()}  ${usu.getTipoUsuario().getNombreTipoUsuario()}</td>
                                    </tr>
                                </c:forEach>

                                <tr align="left">
                                    <td>Fecha Incio</td>
                                    <td><input type="text" class="form-control" name="txtFechaEvaluacion" id="txtFechaEvaluacion" pattern="[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])" placeholder="Ej:1994-10-01" required=""></td>
                                </tr>

                                <tr align="left">
                                    <td>Cuestionario Asociado</td>
                                    <td>
                                        <select name="cboCuestionario" class="form-control" id="cboCuestionario" required="">
                                            <option value="">Seleccionar</option>                       
                                            <c:forEach items="${cuestionarios}" var="cu">
                                                <c:if test="${cu.getCuestionario().getControlEstados().getIdEstado()==1}">
                                                <option value="${cu.getIdCuestAsig()}">Cuestionario número: ${cu.getIdCuestAsig()}</option>
                                                </c:if>
                                            </c:forEach>
                                                
                                        </select>
                                    <td>
                                </tr>
                                <tr align="left">
                                    <td align="center" colspan="2"><input class="btn btn-primary" type="submit" id="btnAsignarCuest" name="btnAsignarCuest" value="Guardar"></td>
                                </tr>
                            </table>
                        </div>

                    </form>
                </div>
            </c:when>       

            <c:when test="${usuario.getTipoUsuario().getIdTipoUsuario()==3}">
               <jsp:include page="Error.jsp"></jsp:include>
            </c:when>
             <c:when test="${usuario.getTipoUsuario().getIdTipoUsuario()==1}">
               <jsp:include page="Error.jsp"></jsp:include>
            </c:when>
        </c:choose>
    </body>



    <jsp:include page="Footer.jsp"></jsp:include>    
</html>
