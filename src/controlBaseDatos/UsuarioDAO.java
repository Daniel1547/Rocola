/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlBaseDatos;

import Ventanas.Perfil;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Usuario;

/**
 *
 * @author ArmandRC
 */
public class UsuarioDAO {
 Conexion c;
 
 public UsuarioDAO(){
     c=new Conexion();
 }
    
 public boolean crear(Usuario user){
     if(c==null){
         c.conectar();
     }//******************************
     try {
         String sql="INSERT INTO USUARIO(NOMBRE_USUARIO,APELLIDO_USUARIO, SEXO, CORREO_E, USER_NAME, PASSWORD, TELEFONO, FOTO)VALUES(?,?,?,?,?,?,?,?);";
         PreparedStatement ps=c.conectar().prepareStatement(sql);
         ps.setString(1, user.getNombre());
         ps.setString(2, user.getApellido());
         ps.setString(3, user.getSexo());
         ps.setString(4, user.getCorreo());
         ps.setString(5, user.getUsuario());
         ps.setString(6, user.getContrasena());
         ps.setInt(7, user.getTelefono());
         ps.execute();
         ps.close();
         ps=null;
         c.desconectar();
         System.out.println("Usuario ingresado");
         return true;
         
     } catch (SQLException ex) {
         System.out.println("No se logro insertar usuario");
         c.desconectar();
         return false;
     }
    }
 
 
 
   public void guardaImagen(String ruta){
       Usuario user = null;
       Connection con = c.conectar();
       String insert = "INSERT INTO USUARIO(NOMBRE_USUARIO,APELLIDO_USUARIO, SEXO, CORREO_E, USER_NAME, PASSWORD, TELEFONO, FOTO)VALUES(?,?,?,?,?,?,?,?);";
       FileInputStream fi = null;
       PreparedStatement ps = null;
       try{
           File file = new File(ruta);
           fi = new FileInputStream(file);
           ps = con.prepareStatement(insert);
            ps.setString(1, user.getNombre());
            ps.setString(2, user.getApellido());
            ps.setString(3, user.getSexo());
            ps.setString(4, user.getCorreo());
            ps.setString(5, user.getUsuario());
            ps.setString(6, user.getContrasena());
            ps.setInt(7, user.getTelefono());
            ps.setBinaryStream(8, fi);
            ps.executeUpdate();
       }catch(Exception e){
           
       }
   }
 
    public List<Usuario> listaUsuarios(){
      Usuario usuario= null;
      List<Usuario> lista= new ArrayList<Usuario>();
      if(c==null){
       //  c.conectar();
      }
      try {
         String sql="SELECT ID_USUARIO,NOMBRE_USUARIO,APELLIDO_USUARIO,SEXO,CORREO_E,USER_NAME,PASSWORD,TELEFONO FROM usuario;";
         PreparedStatement ps=c.conectar().prepareStatement(sql);
         ResultSet rs=ps.executeQuery();

         while(rs.next()){
             usuario=new Usuario();
             usuario.setId(rs.getInt("ID_USUARIO"));
             usuario.setNombre(rs.getString("NOMBRE_USUARIO"));
             usuario.setApellido(rs.getString("APELLIDO_USUARIO"));
             usuario.setSexo(rs.getString("SEXO"));
             usuario.setCorreo(rs.getString("CORREO_E"));
             usuario.setUsuario(rs.getString("USER_NAME"));
             usuario.setContrasena(rs.getString("PASSWORD"));
             usuario.setTelefono(rs.getInt("TELEFONO"));
             lista.add(usuario);
        }
         
         rs.close();
         rs=null;
         ps.close();
         ps=null;
         c.desconectar();
         System.out.println("Consulta exitosa");
         return lista;
         
      } catch (SQLException ex) {
         System.out.println("Consulta fallida");
         c.desconectar();
         return lista;
      }
 }
  
  public int cambiaParametro(String user, String psw, String newUser){
      int verificador=0;
      Usuario usuario=null;
      List<Usuario> lista= new ArrayList<Usuario>();
      UsuarioDAO userDAO=new UsuarioDAO();
      lista=userDAO.listaUsuarios();
      
      for (int i=0; i<lista.size(); i++){
          usuario=lista.get(i);
          if(usuario.getNombre().equals(user) &&
                  usuario.getContrasena().equals(psw)){
                   try {
                        String sql="UPDATE usuario SET USER_NAME = ? WHERE ID_USUARIO =?;";
                        PreparedStatement ps=c.conectar().prepareStatement(sql);
                        ps.setString(1, newUser);
                        ps.setInt(2, usuario.getId());
                        ps.executeUpdate();
                        ps.close();
                        ps=null;
                        c.desconectar();
                        System.out.println("Usuario actualizado");
                        verificador=1;

                    } catch (SQLException ex) {
                        System.out.println("No se logro actualizar usuario");
                        c.desconectar();
                        verificador=0;
                    }
              
          }
      }

         return verificador;   
  }
  
    public int consultaLogin(String user, String psw ){
      int verificador=0;
      Usuario usuario=null;
      List<Usuario> lista= new ArrayList<Usuario>();
      UsuarioDAO userDAO=new UsuarioDAO();
      lista=userDAO.listaUsuarios();
      
      for (int i=0; i<lista.size(); i++){
          usuario=lista.get(i);
          if(usuario.getNombre().equals(user) &&
                  usuario.getContrasena().equals(psw)){
              verificador=1;
          }
      }

         return verificador;   
  }
 
}
