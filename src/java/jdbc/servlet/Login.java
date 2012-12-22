/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.text.StyleContext;
import jdbc.dao.RepositorioDao;
import jdbc.dao.UsuarioDao;
import jdbc.entity.Repositorio;
import jdbc.entity.Usuario;
import jdbc.model.MySql;
import org.apache.catalina.Session;

/**
 *
 * @author vesprada
 */
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

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
            throws ServletException, IOException {

//        Repositorio repositorio = new Repositorio();
//        Usuario usuario = new Usuario();
//        UsuarioDao daoU = new UsuarioDao();
//        PrintWriter out = response.getWriter();
//        HttpSession Session = request.getSession();
//        String destino = null;
//        try {
//            String user = request.getParameter("usuario");
//            String pass = request.getParameter("clave");
//            String operacion = request.getParameter("op");
//            usuario.setLogin(user);
//            usuario.setPassword(pass);
//
//
//
//            usuario = daoU.validaUsuario(usuario);
//
//
//            repositorio.setIdUsuario(usuario.getId());
//
//
//            if (usuario.getId() != 0) {
//                Session.setAttribute("usuario", usuario);
//                destino = "/bienvenido.jsp";
//
//            } else {
//                destino = "/index.jsp";
//            }
//
//
//
//
//
//            ServletContext cont = getServletConfig().getServletContext();
//            RequestDispatcher reqDispatcher = cont.getRequestDispatcher(destino);
//            reqDispatcher.forward(request, response);
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        } finally {
//            out.close();
//        }
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
        processRequest(request, response);
        
        if (request.getParameter("accion").equalsIgnoreCase("cerrar")) {
            request.getSession().invalidate();
        }

        ServletContext cont = getServletConfig().getServletContext();
        RequestDispatcher reqDispatcher = cont.getRequestDispatcher("/index.jsp");
        reqDispatcher.forward(request, response);
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

        Repositorio repositorio = new Repositorio();
        Usuario usuario = new Usuario();
        UsuarioDao daoU = new UsuarioDao();
        PrintWriter out = response.getWriter();
        HttpSession Session = request.getSession();
        String destino = null;
        try {
            String user = request.getParameter("usuario");
            String pass = request.getParameter("clave");
            usuario.setLogin(user);
            usuario.setPassword(pass);



            usuario = daoU.validaUsuario(usuario);


            repositorio.setIdUsuario(usuario.getId());


            if (usuario.getId() != 0) {
                Session.setAttribute("usuario", usuario);
                destino = "/bienvenido.jsp";

            } else {
                destino = "/index.jsp";
            }





            ServletContext cont = getServletConfig().getServletContext();
            RequestDispatcher reqDispatcher = cont.getRequestDispatcher(destino);
            reqDispatcher.forward(request, response);

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            out.close();
        }
        processRequest(request, response);
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
