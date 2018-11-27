<%-- 
    Document   : reporte-jefe-cuestionario
    Created on : 24-11-2018, 22:08:05
    Author     : Alejandro
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
        <!--link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" media="screen"/>
      <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js" type="text/javascript"></script-->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <!--datatable.net-->
        <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
        <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
        <script src="https://cdn.datatables.net/buttons/1.5.2/js/dataTables.buttons.min.js"></script>
        <script src="https://cdn.datatables.net/buttons/1.5.2/js/buttons.flash.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.36/pdfmake.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.36/vfs_fonts.js"></script>
        <script src="https://cdn.datatables.net/buttons/1.5.2/js/buttons.html5.min.js"></script>
        <script src="https://cdn.datatables.net/buttons/1.5.2/js/buttons.print.min.js"></script>
        <!--datatable.net-->

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Estatus de cuestionarios</title>
        <style>
            .asignada {
                color: blue;
            }      
            .respondida {
                color: green;
            }  
            .no_asignada {
                color: red;
            }  
        </style>
    </head>
    <jsp:include page="Header.jsp"></jsp:include>
        <br>
        <body>
        <c:choose>
            <c:when test="${usuario.getTipoUsuario().getIdTipoUsuario()!=3}">
                <div class="container" align="center">
                    <h1 align=" center">Estatus de Cuestionarios de Empleados</h1>
                    <br>
                    <h2>Acá podras visualizar el estado de los cuestionarios asignados a tus empleados.</h2>
                    <table class="table" id="reporteCuestionarios">
                        <thead>
                            <tr>
                                <th>Nombre</th>
                                    <c:forEach var="cuestAsig" items="${cuestionariosPorRutJefe}">
                                    <th>Cuest. ${cuestAsig.getIdCuestAsig()}</th>
                                    </c:forEach>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="e" items="${evaluacionXjefe}">
                                <tr>
                                    <td>${e.getPersona().getNombrePersona()}</td>
                                    <c:forEach var="cuestAsig" items="${cuestionariosPorRutJefe}">
                                        <td>
                                            <c:set var="flag" value="${e.getCuestAsig().getIdCuestAsig() == cuestAsig.getIdCuestAsig()}"></c:set>
                                            <c:set var="flagNota" value="${e.getNotaEvaluacion() > 0}"></c:set>
                                            <c:if test="${flag}">
                                                <!--<span class="glyphicon glyphicon-ok"></span>-->
                                                <span class="fa ${flagNota?'fa-check respondida':'fa-remove asignada'}"></span>
                                            </c:if>
                                            <c:if test="${!flag}">
                                                <span class="fa fa-ban" style="color:red"></span>
                                            </c:if>
                                        </td>
                                    </c:forEach>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="container" align="center">
                    <label for="" align="center">Simbologia</label>
                    <br>
                    <span class="fa fa-check respondida" align="center">Cuestionario Respondido</span>
                    <br>
                    <span class="fa fa-remove asignada" align="center">Cuestionario Asignado y Sin Responder</span>
                    <br>
                    <span class="fa fa-ban no_asignada" align="center">Cuestionario Sin Asignar</span>
                </div>
                <script>
                    $(document).ready(function () {
                        $('#reporteCuestionarios').DataTable({
                            dom: 'Bfrtip',
                            buttons: [
                                {extend: 'copy', text: 'Copiar al portapapeles'},
                                {extend: 'excel', text: 'Exportar a Excel', title: 'Reporte de Status de Cuestionarios.'},
                                {extend: 'csv', text: 'Exportar a CSV'},
                                {extend: 'pdf', text: 'Exportar a Pdf', download: '', title: 'Reporte de Empleados'},
                            ]


                        });

                    });




                </script>
                <style>
                    button
                    {   

                        display: inline-block;
                        font-weight: 400;
                        text-align: center;
                        white-space: nowrap;
                        vertical-align: middle;
                        -webkit-user-select: none;
                        -moz-user-select: none;
                        -ms-user-select: none;
                        user-select: none;
                        border: 1px solid transparent;
                        border-top-color: transparent;
                        border-right-color: transparent;
                        border-bottom-color: transparent;
                        border-left-color: transparent;
                        padding: .375rem .75rem;
                        font-size: 1rem;
                        line-height: 1.5;
                        border-radius: .25rem;
                        transition: color .15s ease-in-out,background-color .15s ease-in-out,border-color .15s ease-in-out,box-shadow .15s ease-in-out;
                        color: #fff;
                        background-color: #007bff;
                        border-color: #007bff;
                        border-top-color: rgb(0, 123, 255);
                        border-right-color: rgb(0, 123, 255);
                        border-bottom-color: rgb(0, 123, 255);
                        border-left-color: rgb(0, 123, 255);

                    }

                    input
                    {

                        display: block;
                        width: 100%;
                        height: calc(2.25rem + 2px);
                        padding: .375rem .75rem;
                        font-size: 1rem;
                        line-height: 1.5;
                        color: #495057;
                        background-color: #fff;
                        background-clip: padding-box;
                        border: 1px solid #ced4da;
                        border-radius: .25rem;
                        transition: border-color .15s ease-in-out,box-shadow .15s ease-in-out;
                    }

                </style>
            </body>
        </c:when>
        <c:when test="${usuario.getTipoUsuario().getIdTipoUsuario()==3}">
            <jsp:include page="Error.jsp"></jsp:include>
        </c:when>                   
    </c:choose>             
</html>
