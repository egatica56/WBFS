<%-- 
    Document   : Respuesta
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
            <h1 align="center" class="">Aca podras crear las respuestas para las preguntas</h1>
            <br>
            <br>
            <div align center class="container-fluid">
                <form action="respuesta" method="post">
                    <div class="container-fluid" align="center">
                        <table>
                            <!--tr><td>Id Cuestionario</td>
                                <td><input disabled="" type="number" name="txtId" id="txtId" required="" ></td>
                            </tr>
                            <tr-->
                            <tr>
                            <td>Porcentaje Respuesta</td>
                            <td><input type="number" name="txtPorcentajeRespuesta" class="form-control" id="txtPorcentajeRespuesta" min="0" max="100" required=""></td>
                            </tr>
                            <tr>
                                <td>
                                    <br>
                                </td>
                                <td>
                                    <br>
                                </td>
                            </tr>
                            <tr>
                                <td>Texto Respuesta</td>
                                <td><input type="text" name="txtRespuesta" class="form-control" id="txtRespuesta"  required=""></td>
                            </tr>
                            <tr>
                                <td>
                                    <br>
                                </td>
                                <td>
                                    <br>
                                </td>
                            </tr>

                            <tr>
                                <td>Pregunta Asociada</td>
                                <td>
                                    <select name="cboPregunta" class="form-control" id="cboPregunta" required="">
                                        <option value="">Seleccionar</option>                       
                                    <c:forEach items="${preguntas}" var="pre">
                                        <option value="${pre.getIdPregunta()}">${pre.getTextoPregunta()}</option>
                                    </c:forEach>
                                </select>
                            <td>
                        </tr>
                        <tr>
                                <td>
                                    <br>
                                </td>
                                <td>
                                    <br>
                                </td>
                            </tr>
                        <tr>
                            <td align="center" colspan="2"><input class="btn btn-primary" type="submit" id="btnGuardarRespuesta" name="btnGuardarRespuesta" value="Guardar"></td>
                        </tr>

                    </table>
                </div>
                <label for="">${mensaje}</label>
            </form>
        </div>
    </body>



    <!-- Footer -->
    <!-- Footer -->
    <jsp:include page="Footer.jsp"></jsp:include>    
</html>
