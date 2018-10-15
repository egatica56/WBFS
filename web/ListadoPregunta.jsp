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
        <title>Listado de Cuestionarios</title>
    </head>
    <header class="jumbotron">
        <div>

        </div>
    </header>
    <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
                <!-- Brand -->
                <a class="navbar-brand" href="#">Logo</a>

                <!-- Links -->
                <ul class="navbar-nav">
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
                            Preguntas
                        </a>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="pregunta">Agregar Preguntas</a>
                            <a class="dropdown-item" href="listarPregunta">Listar Preguntas</a>
                            
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
        <h1 align="center" class="">Aca podras visualizar las preguntas que existen actualmente en el sistema</h1>
        <div align center class="container-fluid">
            <form action="listarPregunta" method="get">
                <div class="container-fluid" align="center">
                    <table class="table">
                        <tr>
                            <th>ID Pregunta</th>
                            <th>Texto Pregunta</th>
                            <th>Porcentaje Pregunta</th>
                            <th>Cuestionario Asociado</th>
                            <th>Opciones</th>
                        </tr>
                        <!--comentario-->
                        <c:forEach items="${preguntas}" var="pregunta">
                            <tr>    
                                <td>${pregunta.getIdPregunta()}</td>
                                <td>${pregunta.getTextoPregunta()}</td>
                                <td>${pregunta.getPorcentajePregunta()}</td>
                                <td>Cuestionario asociado: ${pregunta.getCuestionario().getIdCuest()}</td>
                                <td>
                                    <a href="eliminarCuestionario?id=${cuestionario.getIdCuest()}">Eliminar</a>
                                    <a href="modificarCuestionario?id=${cuestionario.getIdCuest()}">Modificar</a>
                                </td>
                            </tr>   
                        </c:forEach>

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