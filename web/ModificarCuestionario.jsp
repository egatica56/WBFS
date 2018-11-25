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
    <jsp:include page="Header.jsp"></jsp:include>
        <body>
            <h1 align="center" class="">Acá podrás modificar los porcentajes de evaluacion de los cuestionarios existentes</h1>
            <br>
            <br>
            <div align center class="form-group">
                <br>
                <div align="center">
                    <label for="">${mensaje}</label>
                </div>         
                <br>
                <form action="modificarCuestionario" method="post">
                    <div class="container-fluid" align="center">
                        <table align ="center" style="width: 400px!important">

                            <tr>
                                <td><input type="hidden" name="txtIdCuestionario" value="${cuestionario.getIdCuest()}"></td>
                            <td>Id del cuestionario a modificar ${cuestionario.getIdCuest()}</td>
                        </tr>   
                        <tr>
                            <td>Porcentaje Jefe</td>
                            <td><input type="number" class="form-control" name="txtPorcentajeJefe" id="txtPorcentajeJefe" min="0" max="100"   required="" value="${cuestionario.getPorcentajeJefe()}"></td>
                        </tr>
                        <tr>
                            <td>Porcentaje Evaluado</td>
                            <td><input type="number" class="form-control" name="txtPorcentajeEvaluado" id="txtPorcentajeEvaluado" min="0" max="100"  required="" value="${cuestionario.getPorcentajeAutoevaluacion()}"></td>
                        </tr>

                    </table>
                    <br>

                    <div align="center">
                        <label for=""><input class="btn btn-primary" type="submit" id="btnAsignarCuest" name="btnAsignarCuest" value="Guardar"></label> 
                    </div>   
                </div>
            </form>
        </div>
    </body>



    <jsp:include page="Footer.jsp"></jsp:include>    
</html>
