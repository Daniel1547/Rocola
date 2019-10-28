/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controlBaseDatos;

import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 *
 * @author ArmandRC
 */
public class LikeDAO {
    Conexion c;
    
    
    public LikeDAO(){
        c=new Conexion();
    }
    
    public boolean darLike(int idUser, int idSong){
        if(c==null){
         c.conectar();
        }//******************************
        try {
            String sql="INSERT INTO LIKED_SONGS(ID_USUARIO, ID_CANCION) VALUES(?, ?);";
            PreparedStatement ps=c.conectar().prepareStatement(sql);
            ps.setInt(1, idUser);
            ps.setInt(2, idSong);
            ps.execute();
            ps.close();
            ps=null;
            c.desconectar();
            System.out.println("Like dado");
            return true;

        } catch (SQLException ex) {
            System.out.println("No se logro insertar usuario");
            c.desconectar();
            return false;
        }
    }
    
        public static void main(String[] args){
 
        LikeDAO like=new LikeDAO();
        
        like.darLike(1, 1);
      
    }
}
