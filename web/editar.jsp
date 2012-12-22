<%-- 
    Document   : editar
    Created on : 23-nov-2012, 16:58:19
    Author     : vesprada
--%>


<%@page import="jdbc.dao.LenguajeDao"%>
<%@page import="jdbc.entity.Lenguaje"%>
<%@page import="jdbc.dao.DocumentoDao"%>
<%@page import="jdbc.entity.Documento"%>
<%@page import="java.util.LinkedList"%>
<%@page import="jdbc.entity.Repositorio"%>
<%@page import="jdbc.entity.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%

    HttpSession sesion = request.getSession();
    //si no se inicia sesión, redirecciona al index
    String s = "selected";
    Usuario oUsuario = (Usuario) sesion.getAttribute("usuario");

    Repositorio repositorio = (Repositorio) request.getAttribute("repositorio");

    if (oUsuario == null) {

        response.sendRedirect("index.jsp");

    } else {
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.css" />

        <script type="text/javascript" src="bootstrap/js/bootstrap.js"> </script>
        <script type="text/javascript" src="bootstrap/js/bootstrap.min.js" ></script>
        <script src="bootstrap/js/jquery.min.js"></script>
        <script src="bootstrap/js/bootstrap-tooltip.js"></script>
        <script src="bootstrap/js/bootstrap-popover.js"></script>

        <script type="text/javascript">
            
            function vacio(q) {  
                for ( i = 0; i < q.length; i++ ) {  
                    if ( q.charAt(i) != " ") {
                        
                        return true  ;
                    }  
                }  
                return false  ;
            }    
            
            function valida(dato) {                
          
                if( vacio(dato.titulo.value) == false ) {  
                    alert("!El campo titulo no puede estar vacio¡")  
                    document.validar.titulo.focus();
                    return false  ;                    
                }
                
                if( vacio(dato.contenido.value) == false ) {  
                    alert("!El campo contenido no puede estar vacio¡")  
                    document.validar.contenido.focus();
                    return false  ;                    
                }
               
                else {  
                    alert("OK, los valores introducidos son correctos.");              
                    return true;
                }    
                
            
            }
            
            $(function ()
            { $("#titulop").popover();
            });
            
            $(function ()
            { $("#contenidop").popover();
            });
            
            $(function ()
            { $("#lenguajep").popover();
            });
            
            $(function ()
            { $("#documentop").popover();
            });                   
        </script>

        <title>Editar</title>
    </head>
    <body>

        <form class="form-actions" action="/Proyecto/Editar" method ="POST" name="validar" style="width: 400px; margin: auto;" onSubmit="return valida(this)">
            <fieldset>
                <div>
                    <label for="titulo">Titulo:</label> 
                    <a id="titulop" rel="popover" data-content="Si cometiste un error, edita el titulo. No puedes dejarlo en blanco" 
                       data-original-title="Titulo"><input type="text"
                                                        name="titulo" value="<%=repositorio.getTitulo()%>" /></a>
                </div>

                <div>
                    <label for="contenido">Contenido:</label> 
                    <a id="contenidop" rel="popover" data-content="Si lo cambias introduce un contenido. No puedes dejarlo en blanco." 
                       data-original-title="Contenido"><input type="text"
                                                           name="contenido" value="<%=repositorio.getContenido()%>"/></a>




                </div>



                <div>
                    <label for="nombre">Lenguaje</label> 
                    <a id="lenguajep" rel="popover" data-content="Cambia el lenguaje, que has elegido anteriormente" 
                       data-original-title="Lenguaje">  <select name="lenguaje">

                            <%LinkedList<Lenguaje> lisLen = LenguajeDao.getLenguaje();

                                for (int i = 0; i < lisLen.size(); i++) {
                                    if (repositorio.getIdLenguaje() == lisLen.get(i).getId()) {

                                        out.print("<option value=" + lisLen.get(i).getId() + " selected>" + lisLen.get(i).getNombre() + "</option>");

                                    } else {

                                        out.print("<option value=" + lisLen.get(i).getId() + ">" + lisLen.get(i).getNombre() + "</option>");
                                    }

                                }

                            %>



                        </select></a>

                </div>

                <label for="documento">Documento</label> 
                <a id="documentop" rel="popover" data-content="Si te confundiste a poner tu documento, tranquil@ puedes cambiarlo" 
                   data-original-title="Documento"> <select name="documento">


                        <%LinkedList<Documento> lista = DocumentoDao.getDocumento(oUsuario);

                            for (int i = 0; i < lista.size(); i++) {
                                if (repositorio.getIdDocumento() == lista.get(i).getId()) {

                                    out.print("<option value=" + lista.get(i).getId() + " selected>" + lista.get(i).getTitulo() + "</option>");

                                } else {

                                    out.print("<option value=" + lista.get(i).getId() + ">" + lista.get(i).getTitulo() + "</option>");


                                }
                            }

                        %>
                    </select>     </a> 
                <input type="hidden"
                       id="usuario" name="usuario" value="<%=oUsuario.getId()%>" />

                <input type="hidden" name="id" value="<%=repositorio.getId()%>" />
                <input type="hidden" name="operacion" value="editar2" />

                <div>
                    <input type="submit" name="operacion" value="Aceptar edicion" class="btn  btn-success"/>
                </div>
            </fieldset>

        </form>

    </body>
</html>
<% }%>