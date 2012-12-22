/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc.dao;

import java.sql.ResultSet;
import java.util.LinkedList;
import jdbc.entity.Documento;
import jdbc.entity.Lenguaje;
import jdbc.entity.Usuario;
import jdbc.model.MySql;

/**
 *
 * @author vesprada
 */
public class LenguajeDao {
    
    public Lenguaje Mostrar(Lenguaje lenguaje) throws Exception {
        try {
            MySql.conexion();
           
            lenguaje.setNombre((String) MySql.getOne("lenguaje", "nombre", lenguaje.getId()));
   
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
        return lenguaje;
    }
    
    public static LinkedList<Lenguaje> getLenguaje() {

        LinkedList<Lenguaje> listaLenguaje = new LinkedList<>();


        try {

            MySql.conexion();
            
            String consulta = "SELECT ID, NOMBRE FROM LENGUAJE WHERE 1";

            ResultSet rs = MySql.get(consulta);

            while (rs.next()) {
                Lenguaje lenguaje = new Lenguaje();

                lenguaje.setId(rs.getInt("ID"));
                lenguaje.setNombre(rs.getString("NOMBRE"));
            
                listaLenguaje.add(lenguaje);

            }


            MySql.desconexion();




        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaLenguaje;

    }
         
}
