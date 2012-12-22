/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jdbc.dao.DocumentoDao;
import jdbc.dao.RepositorioDao;
import jdbc.dao.UsuarioDao;
import jdbc.entity.Repositorio;
import jdbc.entity.Usuario;
import jdbc.model.MySql;
import org.apache.catalina.Session;
import org.apache.tomcat.util.threads.ResizableExecutor;

/**
 *
 * @author Raul
 */
public class Nuevo extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();
        RepositorioDao repoDao = new RepositorioDao();
        Usuario usuario = new Usuario();
        HttpSession Session = request.getSession();
        Repositorio repositorio = new Repositorio();
        

        String mensaje = "<h3>Añadido con exito</h3>";
        String destino = null;

        String operacion = request.getParameter("operaciones");


        switch (operacion) {

            case "nuevo1":

                usuario = (Usuario) Session.getAttribute("usuario");
                DocumentoDao.getDocumento(usuario);

                destino = "/nuevo.jsp";
                
                break;

            case "nuevo2":
                

                    if(!(boolean) Session.getAttribute("insertado")){
                        
                    
                
                String titulo = request.getParameter("titulo");
                String contenido = request.getParameter("contenido");
                String lenguaje = request.getParameter("lenguaje");
                String idUsuario = request.getParameter("usuario");
                String idDocumento = request.getParameter("documento");

                repositorio.setId(0);
                repositorio.setTitulo(titulo);
                repositorio.setContenido(contenido);
                repositorio.setIdUsuario(Integer.parseInt(idUsuario));
                repositorio.setIdLenguaje(Integer.parseInt(lenguaje));
                repositorio.setIdDocumento(Integer.parseInt(idDocumento));
                repositorio.setFecha(new Date());
                repoDao.nuevo(repositorio);
                Session.setAttribute("insertado", true);
                
                    }

                out.print(mensaje);
                
                 
                break;
                
        }


        ServletContext cont = getServletConfig().getServletContext();
        RequestDispatcher reqDispatcher = cont.getRequestDispatcher(destino);
        reqDispatcher.forward(request, response);

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(Nuevo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(Nuevo.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
