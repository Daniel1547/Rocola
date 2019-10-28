/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import Ventanas.Login;
import controlBaseDatos.UsuarioDAO;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import modelo.Usuario;

/**
 *
 * @author Dani
 */
public class ControlLogin implements MouseListener {
    
    
    int verificador;
    private Login login=new Login();
    
    
    public ControlLogin() {
    }
    
    public ControlLogin(Login Login) {
        this.login = login;
        iniciar();//se puede sesabilitar para hacerlo en el main, sólo es para prueba
    } 
    
    public void iniciar(){
        this.login.butAceptar.addMouseListener (this);
        this.login.btnCancelar.addMouseListener (this);
        //se inicializa la ventana
        this.login.setSize (400, 500);
        this.login.setVisible (true);
        System.out.println("fase 1 inicia");
        
    }
    
    public void verificaCampo (Login login){
        
        Usuario user=new Usuario();
        UsuarioDAO usuarioDAO=new UsuarioDAO(); //variable de clase usuarioBaseDatos que aun no se ha hecho
       
           
        usuarioDAO.consultaLogin(user.getUsuario(),user.getContrasena());
        
        System.out.println("fase 4 registra ok"); //<-----verifica el nivel en que se encuentra 
         //   usuarioRegistro.setNombre(registro.texNombre.getText());
            //se pasan todos los parametros del registro
         //   usuarioBaseDatos=usuarioRegistro;//una vez lleno el usuario que regresaras lo pasas a el usuarioBaseDatos
        
    
    }
    
    
    @Override
    public void mouseClicked(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
