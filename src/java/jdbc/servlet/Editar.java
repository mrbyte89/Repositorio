/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jdbc.dao.RepositorioDao;
import jdbc.entity.Repositorio;
import jdbc.entity.Usuario;

/**
 *
 * @author vesprada
 */
public class Editar extends HttpServlet {

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
        Repositorio repositorio = new Repositorio();
        String destino = null;
        PrintWriter out = response.getWriter();
        String operacion = request.getParameter("operacion");
        String id = request.getParameter("id");
        RepositorioDao repositorioDao = new RepositorioDao();
        int identificador = Integer.parseInt(id);


        switch (operacion) {
            case "borrar":

                repositorioDao.borrar(identificador);
                destino = "/bienvenido.jsp";
                break;


            case "editar":

                repositorio.setId(identificador);
                repositorio = repositorioDao.getEditar(repositorio);

                destino = "/editar.jsp";

                request.setAttribute("repositorio", repositorio);
                break;

            case "editar2":

                repositorio.setId(identificador);
                repositorio.setTitulo(request.getParameter("titulo"));
                repositorio.setContenido(request.getParameter("contenido"));
                repositorio.setIdLenguaje(Integer.parseInt(request.getParameter("lenguaje")));
                repositorio.setIdDocumento(Integer.parseInt(request.getParameter("documento")));

                Usuario oUsuario = (Usuario) request.getSession().getAttribute("usuario");
                repositorio.setIdUsuario(oUsuario.getId());

                repositorioDao.Editar(repositorio);

                destino = "/bienvenido.jsp";
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
            Logger.getLogger(Editar.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Editar.class.getName()).log(Level.SEVERE, null, ex);
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
