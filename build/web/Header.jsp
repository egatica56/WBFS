<%-- 
    Document   : Header
    Created on : 17-10-2018, 19:30:00
    Author     : EduardoGatica
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <header class="container">

        <div class="container-fluid" align="center" style="background-color: green!important">
            <h1>Bienvenido ${usuario.getPersona().getNombrePersona()} Usuario. sitio en construccion</h1>
        </div>

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
                        <a class="dropdown-item" href="listarCuestionario">Listar Cuestionarios creados</a>                          
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
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
                        Evaluaciones
                    </a>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="asignarEvaluacion">Asignar Evaluaciones</a>
                        <a class="dropdown-item" href="listarFuncionario">Listar Funcionarios</a>


                    </div>

                </li>

            </ul>
        </nav> 


    </header>


</html>
