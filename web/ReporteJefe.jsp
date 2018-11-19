<%-- 
    Document   : ReporteJefe
    Created on : 18-11-2018, 1:01:23
    Author     : Francisco
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"/>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>

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

        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cuestionario</title>
    </head>
    <jsp:include page="Header.jsp"></jsp:include>
        <body>
            <div class="container">
                <h1>Reporte Funcionarios</h1>
                <table id="reporte" class="table table-condensed table-striped">
                    <thead>
                        <tr>
                            <th>Rut</th>
                            <th>Perfil</th>
                            <th>Competencia</th>
                            <th>Nota Esperada</th>
                            <th>Nota</th>
                            <th>Brecha</th>
                        </tr>
                    </thead>
                <c:forEach var="r" items="${usuarios}">
                    <tr>
                        <td>${r.getRutEvaluado()}</td>
                        <td>${r.getPerfil()}</td>
                        <td>${r.getCompetencia()}</td>
                        <td>${r.getNotaEsperada()}</td>
                        <td>${r.getNota()}</td>
                        <td>${r.getBrecha()}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <script>
            $(document).ready(function () {
                $('#reporte').DataTable({
                    dom: 'Bfrtip',
                    buttons: [
                        {extend: 'copy', text: 'Copiar al portapapeles'},
                        {extend: 'excel', text: 'Exportar a Excel', title: 'Reporte de Empleados'},
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
</html>
