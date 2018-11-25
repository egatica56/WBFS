<%-- 
    Document   : ListadoCuestionario
    Created on : 12-10-2018, 10:39:17
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
        <title>Listado de Funcionarios</title>
    </head>
    <jsp:include page="Header.jsp"></jsp:include>
        <body>
            <h1 align="center" class="">Acá podrás visualizar a los funcionarios y sus cuestionarios asignados en el sistema</h1>
            <br>
            <div align center class="container-fluid">
                <br>
                <div align="center">
                    ${mensaje}
                </div>
                <form action="cuestionario/listar" method="get">
                    <div class="container-fluid" align="center">
                        <table class="table">
                            <tr>
                                <th>Nombre Funcionario</th>
                                <th>Rut Funcionario</th>
                                <c:forEach items="${cuestAsign}" var="cuestAsig">
                                <th>Cuestionario numero: ${cuestAsig.getIdCuestAsig()}</th>
                                </c:forEach>
                            <th>Opciones</th>
                        </tr>
                        <!--comentario-->
                        <c:forEach items="${cuestionarios}" var="cuestionario">
                            <tr>    
                                <td>${cuestionario.getIdCuest()}</td>
                                <td>${cuestionario.getPorcentajeJefe()}</td>
                                <td>${cuestionario.getPorcentajeAutoevaluacion()}</td>
                                <td>${cuestionario.getCompetencia().getNombreCompetencia()}</td>
                                <td>
                                    <a href="eliminarCuestionario?id=${cuestionario.getIdCuest()}">Eliminar</a>
                                    <a href="modificarCuestionario?id=${cuestionario.getIdCuest()}">Modificar</a>
                                </td>
                            </tr>   
                        </c:forEach>

                    </table>
                </div>

            </form>
        </div>
    </body>

    <jsp:include page="Footer.jsp"></jsp:include>    
</html>