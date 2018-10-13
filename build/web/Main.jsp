<%-- 
    Document   : Demo
    Created on : 08-09-2018, 9:54:12
    Author     : EduardoGatica
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <title>Bienvenido a WBFS</title>
    </head>
    <header class="jumbotron">
        <div class="container" align="center">Login Ok Como Admin</div>
        <div class="container" align="center" style="background-color: green!important">
            <h1>Hola Manuel Esteban Usuario. sitio en construccion</h1>
        </div>
    </header>
       <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
                <!-- Brand -->
                <a class="navbar-brand" href="#">Logo</a>

                <!-- Links -->
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" href="#">Link 1</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Link 2</a>
                    </li>

                    <!-- Dropdown -->
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
                            Cuestionarios
                        </a>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="cuestionario">Nuevo Cuestionario</a>
                            <a class="dropdown-item" href="listarCuestionario">Listar Cuestionarios</a>
                            
                        </div>
                        
                       
                        
                    </li>
                    <li class="nav-item dropdown">
                         <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
                            Respuestas
                        </a>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="respuesta">Agregar Respuestas</a>
                            <a class="dropdown-item" href="listarRespuesta">Listar Respuestas</a>
                            
                        </div>
                        
                    </li>
                    
                </ul>
            </nav> 

    <body>
        <div class="container-fluid" align="center" style="background-color: green!important">
        <h1>Hola ${usuario.getPersona().getNombrePersona()} Usuario. sitio en construccion</h1>

        </div>
        <c:if test="${usuario.getTipoUsuario().getIdTipoUsuario()== 1}" >
           
            <div class="container-fluid">
                
                <a class="" href="cuestionario">Nuevo Cuestionario</a>      
            </div>
        </c:if>

        <c:if test="${usuario.getTipoUsuario().getIdTipoUsuario()== 2}" >
            <label for="">Label de ejemplo de jefe</label>
        <li>
            <ul>cuatro</ul>
            <ul>cinco</ul>
            <ul>seis</ul>
        </li>
    </c:if>

    <c:if test="${usuario.getTipoUsuario().getIdTipoUsuario()== 3}" >
        <label for="">Label de ejemplo de empleado</label>
        <li>
            <ul><a href="Cuestionario.jsp">Nuevo Cuestionario</a></ul>
            <ul>ocho</ul>
            <ul>nueve</ul>
        </li>
    </c:if>

    ${mensaje}
</body>
</html>
