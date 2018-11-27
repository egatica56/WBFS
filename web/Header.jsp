<%-- 
    Document   : Header
    Created on : 17-10-2018, 19:30:00
    Author     : EduardoGatica
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>


<header class="container-fluid">

    <div class="container-fluid" align="center" style="background-color: #4038BE!important">
        <h1>Bienvenido ${usuario.getPersona().getNombrePersona()}.</h1>
    </div>
    
        <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
            <!-- Brand -->
            <a class="navbar-brand" href="#">Logo</a>

            <!-- Links -->
            <c:if test="${usuario.getTipoUsuario().getIdTipoUsuario()==1}">
                <ul class="navbar-nav">
                    <!-- Dropdown -->
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
                            Cuestionarios
                        </a>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="cuestionario">Nuevo Cuestionario</a>
                            <a class="dropdown-item" href="listarCuestionario">Listar Cuestionarios creados</a> 
                            <!--a class="dropdown-item" href="asignarCuestionario">Asignar Cuestionarios</a-->
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
                            <a class="dropdown-item" href="listarEvaluacion"> Listar Evaluaciones</a>
                        </div>

                    </li>

                </ul>
            </c:if>

            <c:if test="${usuario.getTipoUsuario().getIdTipoUsuario()==2}">
                <ul class="navbar-nav">
                    <!-- Dropdown -->
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
                            Cuestionarios
                        </a>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="cuestionario">Nuevo Cuestionario</a>
                            <a class="dropdown-item" href="listarCuestionario">Listar Cuestionarios creados</a> 
                            <!--a class="dropdown-item" href="asignarCuestionario">Asignar Cuestionarios</a-->
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
                            <a class="dropdown-item" href="listarEvaluacion"> Listar Evaluaciones</a>
                        </div>

                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
                            Notas
                        </a>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="consultarNota">Consultar Nota</a>
                        </div>

                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
                            Reportes
                        </a>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="reportejefe">Consultar empleados</a>
                               <a class="dropdown-item" href="reporte-jefe"> Consultar Estado Evaluaciones</a>

                        </div>

                    </li>

                </ul>
            </c:if>


            <c:if test="${usuario.getTipoUsuario().getIdTipoUsuario()==3}">
                <ul class="navbar-nav">
                    <!-- Dropdown -->
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
                            Evaluaciones
                        </a>
                        <div class="dropdown-menu">

                            <a class="dropdown-item" href="listarEvaluacion"> Listar Evaluaciones</a>
                        </div>

                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
                            Notas
                        </a>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="consultarNota">Consultar Nota</a>
                        </div>

                    </li>
                </ul>
            </c:if>
            <a class="nav-link" href="Main.jsp">Volver</a>
            <a class="nav-link" href="cerrar-sesion">Cerrar Sesion</a>

        </nav>
        <br>

</header>



