<%-- 
    Document   : PantallaEvaluacion
    Created on : 20-10-2018, 8:42:28
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
        <script src="js/ejemplo.js"></script>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <jsp:include page="Header.jsp"></jsp:include>
        <body>
            <form action="pintarEvaluacion" method="post">
                <div class="container">
                <h3>Persona : <small>${requestScope.persona.getNombrePersona()} ${param.rutP}</small></h3>
                <h3>Jefe : <small>${requestScope.jefe.getNombrePersona()}</small></h3>
                <h3>Competencia : <small>${requestScope.competencia.getNombreCompetencia()}</small></h3>
                <h3>ID Evaluación : <small>${param.idE}</small></h3>
                <h1><input type="hidden" name="idE" value="${param.idE}"></h1>
                <h3>Preguntas</h3>
                <hr>
                <c:set var="np" value="1"></c:set>

                <c:forEach var="p" items="${requestScope.preguntas}">

                    <h4>${np}. ¿${p.getTextoPregunta()}?</h4>
                    <c:forEach var="r" items="${p.getOpcionRespuestaCollection()}">

                        <div class="radio container">
                            <label><input type="radio" name="${p.getIdPregunta()}" value="${r.getIdOpcionRespuesta()}" required="">${r.getTextoRespuesta()}</label>
                        </div>
                    </c:forEach>
                    <c:set var="np" value="${np + 1}"></c:set>
                        <br>
                </c:forEach>

                <input class="btn btn-primary" type="submit" value="Enviar Cuestionario">
            </div>

        </form>

    </body>
</html>
