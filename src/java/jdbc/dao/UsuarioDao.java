/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import jdbc.entity.Repositorio;
import jdbc.entity.Usuario;
import jdbc.model.MySql;

/**
 *
 * @author vesprada
 */
public class UsuarioDao {
    
     public Usuario validaUsuario(Usuario pojoU) {
        try{ 
            MySql.conexion();
            String consulta = ("select * from usuario where login ='"+pojoU.getLogin()+
                    "' AND password = '"+pojoU.getPassword()+"'");
            ResultSet result = MySql.get(consulta);
            if(result.next()){
                pojoU.setApe1(result.getString("ape1"));
                pojoU.setApe2(result.getString("ape2"));
                pojoU.setEmail(result.getString("email"));
                pojoU.setId(result.getInt("id"));
                pojoU.setNombre(result.getString("nombre"));
                pojoU.setPassword(result.getString("password"));
                pojoU.setTelefono(result.getString("telefono"));
                pojoU.setIdTipoUsuario(result.getInt("id_tipo_usuario"));
                pojoU.setLogin(result.getString("login"));            
            }
            MySql.desconexion();
            
        }catch(Exception e){
            
        }
        return pojoU;
    }
   

    }
    

