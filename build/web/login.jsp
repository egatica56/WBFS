<%-- 
    Document   : login
    Created on : 06-sep-2018, 19:39:03
    Author     : Eduardo Gatica
--%>

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
        
        <title>Login</title>
    </head>
    <body>
        <h1 align="center">Bienvenido a WBFS</h1>
        <h1 align="center">Ingresar Credenciales</h1>
        <div align="center" class="container">
            <div align="center" class="container" style="border: black solid;background-color: #d4d4d4 !important;width: 400px">
                <form action="login" method="POST">
                    <div class="container">
                        <p><span class="fa fa-user-o" style="font-size:48px;color:black;"></span></p>
                        <p> Nombre usuario</p>
                        <p> <input type="text" name="txtUsuario" id="txtUsuario"></p>
                    </div>

                    <div class="container">
                        <p><span class="fa fa-key" style="font-size:48px;color:black;"></span></p>
                        <p> Contraseña</p>
                        <p> <input type="password" name="txtPassword" id="txtPassword"></p>
                    </div>

                    <div class="container">   
                        <p  align="center" colspan="2"><input type="submit" value="Ingresar" name="btnLogin" id="btnLogin"></p> 
                    </div>                  
                </form>

                ${mensaje}
            </div>
    </body>
</html>
