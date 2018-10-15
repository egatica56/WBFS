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
        <title>Cuestionario</title>
    </head>
    <header class="jumbotron">
        <div>

        </div>
    </header>
    <body>
        <h1 align="center" class="">Aca podras Asignar  los cuestionarios previamente creados</h1>
        <div align center class="container-fluid">
            <form action="asignarCuestionario" method="Post">
                <div class="container" align="center">
                    <table>
                        <c:forEach items="${usuarios}" var="usu">
                            <tr>
                                <td>Usuario a asignar</td>
                                <td> <input type="checkbox" name="chkUsuario" id="chkUsuario" value="${usu.getUsername()}">${usu.getUsername()}</td>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td>Fecha Incio</td>
                            <td><input type="date" name="dtFechaIncio" id="dtFechaIncio" required=""></td>
                        </tr>
                        <tr>
                            <td>Fecha Termino</td>
                            <td><input type="date" name="dtFechaTermino" id="dtFechaTermino" required=""></td>
                        </tr>
                        <tr>
                            <td>Cuestionario Asociado</td>
                            <td>
                                <select name="cboCuestionario" id="cboCuestionario" required="">
                                    <option value="">Seleccionar</option>                       
                                    <c:forEach items="${cuestionarios}" var="cu">
                                        <option value="${cu.getIdCuest()}">Cuestionario número: ${cu.getIdCuest()} -  ${cu.getCompetencia().getNombreCompetencia()}</option>
                                    </c:forEach>
                                </select>
                            <td>
                        </tr>
                        <tr>
                            <td align="center" colspan="2"><input class="btn btn-primary" type="submit" id="btnAsignarCuest" name="btnAsignarCuest" value="Guardar"></td>
                        </tr>
                    </table>
                </div>
                <label for="">${mensaje}</label>
            </form>
        </div>
    </body>



    <!-- Footer -->
    <!-- Footer -->
    <footer class="page-footer font-small blue fixed-bottom">
        <div class="footer-copyright text-center py-3">© 2018 Copyright:
            <a href="https://mdbootstrap.com/bootstrap-tutorial/"> MDBootstrap.com</a>
        </div>
    </footer>
    <!-- Footer -->
</html>
