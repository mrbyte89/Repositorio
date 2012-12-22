/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc.dao;

import java.sql.ResultSet;
import java.util.LinkedList;
import jdbc.entity.Documento;
import jdbc.entity.Repositorio;
import jdbc.entity.Usuario;
import jdbc.model.MySql;

/**
 *
 * @author vesprada
 */
public class DocumentoDao {
    
     public static LinkedList<Documento> getDocumento(Usuario usuario) {

        LinkedList<Documento> listaDocumento = new LinkedList<>();


        try {

            MySql.conexion();

            String sql = " SELECT ID, TITULO  FROM DOCUMENTO WHERE ID_USUARIO = '" + usuario.getId() + "' ORDER BY ID DESC";

            ResultSet rs = MySql.get(sql);

            while (rs.next()) {
                Documento documento = new Documento();

                documento.setId(rs.getInt("ID"));
                documento.setTitulo(rs.getString("TITULO"));
            
                listaDocumento.add(documento);

            }


            MySql.desconexion();




        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaDocumento;

    }
     
     
     public Documento Mostrar(Documento documento) throws Exception {
        try {
            MySql.conexion();
           
            documento.setTitulo((String) MySql.getOne("documento", "titulo", documento.getId()));
   
        } catch (Exception er) {
            throw new Exception("ClienteDao.getCliente: Error: "
                    + er.getMessage());
        } finally {
            try {
                MySql.desconexion();
            } catch (Exception er) {
                throw new Exception(
                        "ClienteDao.getCliente: Error en la desconexion: "
                        + er.getMessage());
            }
        }
        return documento;
    }

    
}
