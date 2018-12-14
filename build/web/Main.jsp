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
        <title>Home WFBS</title>
    </head>


    <jsp:include page="Header.jsp"></jsp:include>

        <body>
            <br>
            <div align="center">
                <h1 align="center">${mensaje}</h1>
            </div>
            <br>
            <div class="page-header" align="centrer">
                <h1 align="center" >Acerca de la Agencia</h1>                
                <div class="container" align="center">
                <p>WFBS es una agencia de detectives dedicada a satisfacer todas las necesidades</p>
                
                <p>del area criminalistica. Por ello esta web tiene como fin evaluar a sus investigadores</p>

                <p>para lograr obtener unos profesionales de calidad y listos para responder ante cualquer situación.</p>
                </div>
            </div>
            
        </body>
    <jsp:include page="Footer.jsp"></jsp:include>    

</html>
