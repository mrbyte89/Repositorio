<%-- 
    Document   : Nuevo
    Created on : 16-nov-2012, 23:55:33
    Author     : Raul
--%>

<%@page import="jdbc.dao.LenguajeDao"%>
<%@page import="jdbc.entity.Lenguaje"%>
<%@page import="jdbc.dao.DocumentoDao"%>
<%@page import="jdbc.entity.Documento"%>
<%@page import="java.util.LinkedList"%>
<%@page import="jdbc.dao.RepositorioDao"%>
<%@page import="jdbc.entity.Repositorio"%>
<%@page import="jdbc.entity.Repositorio"%>
<%@page import="jdbc.entity.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%

    HttpSession sesion = request.getSession();
    //si no se inicia sesión, redirecciona al index
    Usuario oUsuario = (Usuario) sesion.getAttribute("usuario");
    sesion.setAttribute("insertado", false);
    
    if (oUsuario == null) {

        response.sendRedirect("index.jsp");

    } else {
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.css" />
        <link rel="stylesheet" type="text/css" href="bootstrap/css/modal.css" />

        <title>Nuevo</title>

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
                    alert("!Campo titulo vacio.¡")  
                    document.validar.titulo.focus();
                    return false  ;                    
                }
                
                if( vacio(dato.contenido.value) == false ) {  
                    alert("!Campo contenido vacio¡")  
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

    </head>
    <body>
        <form action="/Proyecto/Nuevo?operaciones=nuevo2" name="validar" method ="POST" onSubmit="return valida(this)">
            <fieldset> 
               <div>
                    <label for="titulo">Titulo:</label>
                    <a id="titulop" rel="popover" data-content="Introduce el titulo al repositorio" 
                       data-original-title="Titulo"><input type="text" name="titulo"/></a>

                </div>
                <div>
                    <label for="contenido">Contenido:</label> 
                    <a id="contenidop" rel="popover" data-content="Introduce una descripcion al contenido del documento" 
                       data-original-title="Contenido"><input type="text" name="contenido" /></a>
                </div>

                <div>
                    <label for="lenguaje">Lenguaje</label> 
                  <a id="lenguajep" rel="popover" data-content="Selecciona el lenguaje de programacion." 
                       data-original-title="Lenguaje"> <select name="lenguaje"> 
                          <%LinkedList<Lenguaje> lisLen =  LenguajeDao.getLenguaje();

                            for (int i = 0; i < lisLen.size(); i++) {
                                out.print("<option value=" + lisLen.get(i).getId() + ">" + lisLen.get(i).getNombre() + "</option>");


                            }

                        %>

                    </select></a>
                </div>


                <div>
                    <label for="documento">Documento</label> 
                    <a id="documentop" rel="popover" data-content="It's so simple to create a tooltop for my website!" 
                       data-original-title="Twitter Bootstrap Popover">  <select name="documento">

                        <%LinkedList<Documento> lista = DocumentoDao.getDocumento(oUsuario);

                            for (int i = 0; i < lista.size(); i++) {
                                out.print("<option value=" + lista.get(i).getId() + ">" + lista.get(i).getTitulo() + "</option>");


                            }

                        %>

                    </select></a>
                </div>


                <input type="hidden"
                       id="usuario" name="usuario" value="<%=oUsuario.getId()%>" />
                

                <div>
                    <input type="submit" class="btn btn-primary btn-large" value="Añadir nuevo documento" />
                </div>
            </fieldset>

        </form>




    </body>
</html>
<% }%>
