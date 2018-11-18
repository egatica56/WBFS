<%-- 
    Document   : Pregunta
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
        <title>Consulta de nota</title>
    </head>
    <jsp:include page="Header.jsp"></jsp:include>
        <body>
            <h1 align="center" class="">Aca podras consultar por la nota de tus evaluaciones.</h1>
            <br>
            <div align center class="container-fluid">
                <form action="consultarNota" method="post">
                    <div class="container-fluid" align="center">
                        <br>
                        <table>
                            <!--tr><td>Id Cuestionario</td>
                                <td><input disabled="" type="number" name="txtId" id="txtId" required="" ></td>
                            </tr>
                            <tr-->
                            <tr>
                                <td>Evaluacion Asociada</td>
                                <td>
                                    <select name="cboEvaluacion" class="form-control" id="cboEvaluacion" required="">
                                        <option  value="">Seleccionar</option>                       
                                    <c:forEach items="${evaluaciones}" var="ev">
                                        <option  value="${ev.getIdEvaluacion()}">Evaluación número: ${ev.getIdEvaluacion()}</option>
                                    </c:forEach>
                                </select>
                            <td>
                        </tr>
                        <tr><td><br></td>
                            <td><br></td></tr>
                        <tr>
                            <td align="center" colspan="2"><input class="btn btn-primary" type="submit" id="btnGuardarPreg" name="btnGuardarPreg" value="Consultar"></td>
                        </tr>

                    </table>
                </div>
                <div align="center">
                    <h1  align="center" for="">${mensaje}</h1>
                </div>
                
                <div align="center">
                    <h1  align="center" for="">${observacion}</h1>
                </div>

            </form>
        </div>
    </body>



    <jsp:include page="Footer.jsp"></jsp:include>    
</html>
