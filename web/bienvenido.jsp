<%-- 
    Document   : bienvenido
    Created on : 05-nov-2012, 17:56:59
    Author     : vesprada
--%>

<%@page import="java.util.LinkedList"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="jdbc.entity.*"%>
<%@page import="jdbc.dao.*"%>



<!DOCTYPE html>
<%

    HttpSession sesion = request.getSession();
    //si no se inicia sesión, redirecciona al index
    Usuario oUsuario = (Usuario) sesion.getAttribute("usuario");
    if (oUsuario == null) {

        response.sendRedirect("index.jsp");

    } else {
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.css" />
        <link rel="stylesheet" type="text/css" href="bootstrap/css/indice.css" />
        <script src="bootstrap/js/jquery.min.js"></script>
        <script src="bootstrap/js/bootstrap-tooltip.js"></script>
        <script src="bootstrap/js/bootstrap-popover.js"></script>
        <script src="bootstrap/js/bootstrap-modal.js"></script>


        <title>Inicio</title>

        <script>
            function ajustar() {
                document.getElementById("prueba").style.height = window.frames.prueba.document.body.style.
                    height;
            }
            function ini() {
                document.getElementById("prueba").height = document.frames.prueba.document.body.offsetHeight + document.frames.prueba.document.body.scrollHeight;
            }

            function getWindowData(n,i){
                var ifr=document.getElementById(i).contentWindow.document || document.getElementById(i).contentDocument;
                var widthViewport,heightViewport,xScroll,yScroll,widthTotal,heightTotal;
                if (typeof window.frames[n].innerWidth != 'undefined'){
                    widthViewport= window.frames[n].innerWidth;
                    heightViewport= window.frames[n].innerHeight;
                }else if(typeof ifr.documentElement != 'undefined' && typeof ifr.documentElement.clientWidth !='undefined' && ifr.documentElement.clientWidth != 0){
                    widthViewport=ifr.documentElement.clientWidth;
                    heightViewport=ifr.documentElement.clientHeight;
                }else{
                    widthViewport= ifr.getElementsByTagName('body')[0].clientWidth;
                    heightViewport=ifr.getElementsByTagName('body')[0].clientHeight;
                }
                xScroll=window.frames[n].pageXOffset || (ifr.documentElement.scrollLeft+ifr.body.scrollLeft);
                yScroll=window.frames[n].pageYOffset || (ifr.documentElement.scrollTop+ifr.body.scrollTop);
                widthTotal=Math.max(ifr.documentElement.scrollWidth,ifr.body.scrollWidth,widthViewport);
                heightTotal=Math.max(ifr.documentElement.scrollHeight,ifr.body.scrollHeight,heightViewport);
                return [widthViewport,heightViewport,xScroll,yScroll,widthTotal,heightTotal];
            } 
            function resizeIframe(ID,NOMBRE){
                document.getElementById(ID).height=null;
                document.getElementById(ID).width=null;
                window.location='#';//necesario para safari
                var m=getWindowData(NOMBRE,ID); 
                document.getElementById(ID).height=m[5];
                document.getElementById(ID).width=m[4]+22;
            } 
            function addEvent(obj, evType, fn, useCapture){

                if (obj.addEventListener){
                    obj.addEventListener(evType, fn, useCapture);
    
                } else if (obj.attachEvent){
                    obj.attachEvent("on"+evType, fn);
   
                } else {
                    obj['on'+evType]=fn;
                }
            }
            window.onload=function(){
                resizeIframe('pp','pp');
                addEvent(document.getElementById('pp'), 'load', function(){resizeIframe('pp','pp');}, false);
            }
        </script>

        <script type='text/javascript'>
            $(document).ready(function () {
                if ($("[rel=tooltip]").length) {
                    $("[rel=tooltip]").tooltip();
                } });
            
        </script>

    </head>
    <body>
        <div class="contenedor"">
            <div class="navbar navbar-inverse navbar-static-top">
                <div class="navbar-inner">
                    <a class="brand" href="#">Moodle</a>
                    <div class="brand" id="logeado">Bienvenido, <%=oUsuario.getNombre()%> <%=oUsuario.getApe1()%> 
                        <a href="Login?accion=cerrar" class="btn-mini btn-danger">Cerrar Sesión</a></div>
                </div>
            </div>





            <div class="container">

                <a data-toggle="modal" href="#new" id="nuevo"  class="btn btn-info boton"> Nuevo </a>
                <br />  <br />  <br />
                <%
                    LinkedList<Repositorio> lista = RepositorioDao.getRepositorio(oUsuario);

                    for (int i = 0; i < lista.size(); i++) {
                        DocumentoDao documentoDao = new DocumentoDao();
                        LenguajeDao lenguajeDao = new LenguajeDao();
                        Lenguaje lenguaje = new Lenguaje(lista.get(i).getIdLenguaje());
                        Documento documento = new Documento(lista.get(i).getIdDocumento());
                        out.print("<table class=\"table table-striped\" >");
                        out.print("<tr background=\"#ccffcc\">");
                        out.print("<th  colspan=\"2\"> <div rel=\"tooltip\" title=\"Titulo\" align=\"left\">" + lista.get(i).getTitulo().toUpperCase() + "</th>");
                        out.print("<td></td>");
                        out.print("<td></td>");
                        out.print("<td id=\"fecha\" colspan=\"2\"> <div title=\"Fecha de envio\" rel=\"tooltip\" align=\"right\">" + lista.get(i).getFecha() + "</td>");
                        out.print("</tr>");
                        out.print("<tr>");
                        out.print("<td id=\"lenguaje\"> <div title=\"Lenguaje del codigo\" rel=\"tooltip\" align=\"left\">" + lenguajeDao.Mostrar(lenguaje).getNombre().toUpperCase() + "</td>");
                        out.print("<td id=\"contenido\" colspan=5> <div title=\"Descripcion del codigo\" rel=\"tooltip\" align=\"center\">" + lista.get(i).getContenido() + "</td>");
                        out.print("</tr>");
                        out.print("<tr>");
                        out.print("<td id=\"documento\" colspan=\"6\"> <div title=\"Documento subido\" rel=\"tooltip\" align=\"center\">" + documentoDao.Mostrar(documento).getTitulo() + "</td>");
                        out.print("</tr>");
                        out.print("<tr>");
                        out.print("<td colspan=\"4\">&nbsp;</td>");
                        out.print("</tr>");
                        out.print("</table>");
                        out.print("<div title=\"Editar\" class=\"editar\" align=\"center\"> <a class=\"btn btn-warning\" "
                                + "  href=\"/Proyecto/Editar?id=" + lista.get(i).getId()
                                + "&documento=" + lista.get(i).getIdDocumento() + "&operacion=editar\"> Editar </a></div>");
                        
                        out.print("<div title=\"Borrar\" class=\"borrar\" align=\"center\"><a  class=\"btn btn-danger\" href=\""
                                + "/Proyecto/Editar?id=" + lista.get(i).getId() + "&operacion=borrar\">Borrar</a></div>");

                    }
                %>
            </div>


        </div>


        <div id="new" href="/Proyecto/Nuevo?operaciones=nuevo1" class="modal hide fade in" style="display: none; ">
            <div class="modal-header">
                <a class="close" data-dismiss="modal">×</a>
                <h3>Nuevo</h3>
            </div>
            <div class="modal-body">
                <iframe id="newIframe" name="iframeNuevo" src="nuevo.jsp" frameborder="0" width="500px" height="330px" onload="ini()" border="0" target="Iframe"> </iframe>		        
            </div>
            <div class="modal-footer">
                <a href="bienvenido.jsp" class="btn">Cerrar</a>
            </div>
        </div>



    </body>
</html>

<% }%>
