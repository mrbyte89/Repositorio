<%-- 
    Document   : index
    Created on : 22-oct-2012, 16:03:42
    Author     : vesprada
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Respositorio</title>
        <link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.css" />
        <link rel="stylesheet" type="text/css" href="bootstrap/css/base.css" />

    </head>
    <body >
        
        <div class="container">
        <div class="navbar navbar-inverse navbar-static-top">
            <div class="navbar-inner">
                <a class="brand" href="#">Moodle</a>
            </div>
        </div>
        <div class="formulario">
            <form class="form-horizontal" action="/Proyecto/Login" method ="POST" style="width: 400px; margin: auto;">
                <div class="control-group">
                    <label class="control-label">Usuario:</label>
                    <div class="controls">
                        <input type="text" name="usuario"/>     
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" >Password:</label>
                    <div class="controls">
                        <input type="password" name="clave" value="1234"/>
                    </div>
                </div>
                <div class="control-group">
                    <div class="controls">
                        <input class="btn btn-primary" type="submit" value="Entrar"/>
                    </div>
                </div>

            </form>
        </div>

        <div class="texto">
            <div class="hero-unit">
                <h1>Moodle</h1>
                <p>Repositorio de codigo CIPFP Ausias March</p>
            </div>
        </div>
        </div>
        <div id="pie">Repositorio - Ausias March 2012 Raúl Aguilar</div>
    </body>
</html>
