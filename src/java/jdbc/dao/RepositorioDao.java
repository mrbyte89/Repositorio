/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc.dao;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.LinkedList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import jdbc.entity.Repositorio;
import javax.servlet.http.HttpServletResponse;
import jdbc.entity.Usuario;
import jdbc.model.MySql;
import jdbc.servlet.Login;

/**
 *
 * @author vesprada
 */
public class RepositorioDao {

    public Connection conexion;

    public static LinkedList<Repositorio> getRepositorio(Usuario usuario) {

        LinkedList<Repositorio> listaRepo = new LinkedList<>();


        try {

            MySql.conexion();


            String sql = " SELECT ID, TITULO, CONTENIDO, ID_USUARIO, ID_LENGUAJE, ID_DOCUMENTO, "
                    + " FECHA FROM REPOSITORIO WHERE ID_USUARIO = '" + usuario.getId() + "' ORDER BY ID DESC ";

            ResultSet rs = MySql.get(sql);


            while (rs.next()) {
                Repositorio repositorio = new Repositorio();

                repositorio.setId(rs.getInt("ID"));
                repositorio.setTitulo(rs.getString("TITULO"));
                repositorio.setContenido(rs.getString("CONTENIDO"));
                repositorio.setIdUsuario(rs.getInt("ID_USUARIO"));
                repositorio.setIdLenguaje(rs.getInt("ID_LENGUAJE"));
                repositorio.setIdDocumento(rs.getInt("ID_DOCUMENTO"));
                repositorio.setFecha(rs.getDate("FECHA"));

                listaRepo.add(repositorio);

            }


            MySql.desconexion();




        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaRepo;

    }

    public void nuevo(Repositorio repositorio) throws Exception {
        try {


            MySql.conexion();
            MySql.initTrans();
            if (repositorio.getId() == 0) {
                repositorio.setId(MySql.insertOne("repositorio"));
            }
            MySql.updateOne(repositorio.getId(), "repositorio", "titulo", repositorio.getTitulo());
            MySql.updateOne(repositorio.getId(), "repositorio", "contenido", repositorio.getContenido());
            MySql.updateOne(repositorio.getId(), "repositorio", "id_usuario", Integer.toString(repositorio.getIdUsuario()));
            MySql.updateOne(repositorio.getId(), "repositorio", "id_lenguaje", Integer.toString(repositorio.getIdLenguaje()));
            MySql.updateOne(repositorio.getId(), "repositorio", "id_documento", Integer.toString(repositorio.getIdDocumento()));
            MySql.updateOne(repositorio.getId(), "repositorio", "fecha", dateToMySQLDate(repositorio.getFecha()));

            MySql.commitTrans();
            MySql.desconexion();
        } catch (Exception e) {
            throw new Exception("RepositorioDao.Nuevo() Error: "
                    + e.getMessage());
        }
    }

    public void borrar(int id) throws Exception {
        try {
            MySql.conexion();
            MySql.removeOne(id, "repositorio");
            MySql.desconexion();
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        } finally {
            MySql.desconexion();
        }
    }

    public Repositorio getEditar(Repositorio repositorio) throws Exception {
        try {
            MySql.conexion();

            repositorio.setTitulo((String) MySql.getOne("repositorio", "titulo", repositorio.getId()));
            repositorio.setContenido((String) MySql.getOne("repositorio", "contenido", repositorio.getId()));
            repositorio.setIdLenguaje((Integer) MySql.getOne("repositorio", "id_lenguaje", repositorio.getId()));
            repositorio.setIdDocumento((Integer) MySql.getOne("repositorio", "id_documento", repositorio.getId()));

            MySql.desconexion();
        } catch (Exception e) {
            throw new Exception("RepositorioDao.getEditar: Error: "
                    + e.getMessage());
        } finally {
            try {
                MySql.desconexion();
            } catch (Exception e) {
                throw new Exception(
                        "ClienteDao.getCliente: Error en la desconexion: "
                        + e.getMessage());
            }
        }
        return repositorio;
    }

    public void Editar(Repositorio repositorio) throws Exception {
        try {
            MySql.conexion();
            MySql.initTrans();
            if (repositorio.getId() == 0) { //es nueva la incidencia
                repositorio.setId(MySql.insertOne("repositorio"));
            }
            MySql.updateOne(repositorio.getId(), "repositorio", "titulo", repositorio.getTitulo());
            MySql.updateOne(repositorio.getId(), "repositorio", "contenido", repositorio.getContenido());
            MySql.updateOne(repositorio.getId(), "repositorio", "id_usuario", Integer.toString(repositorio.getIdUsuario()));
            MySql.updateOne(repositorio.getId(), "repositorio", "id_lenguaje", Integer.toString(repositorio.getIdLenguaje()));
            MySql.updateOne(repositorio.getId(), "repositorio", "id_documento", Integer.toString(repositorio.getIdDocumento()));
            MySql.commitTrans();
        } catch (Exception e) {
            MySql.rollbackTrans();
            throw new Exception("IncidenciaDao.setProducto: Error: " + e.getMessage());
        } finally {
            MySql.desconexion();
        }



    }
    
    

    public String dateToMySQLDate(Date fecha) {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(fecha);
    }
}
