/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import Ventanas.Registro;
import controlBaseDatos.UsuarioDAO;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import modelo.Usuario;

/**
 *
 * @author ArmandRC
 */
public class ControlRegistro implements MouseListener {

    int verificador;
    Usuario usuarioRegistro;
    private Registro registro=new Registro();
    

    public ControlRegistro() {
    }
    
    public ControlRegistro(Registro registro) {
        this.registro = registro;
        iniciar();//se puede sesabilitar para hacerlo en el main, sólo es para prueba
    } 
    
    public void iniciar(){
        this.registro.butAceptar.addMouseListener (this);
        this.registro.butCancelar.addMouseListener (this);
        //se inicializa la ventana
        this.registro.setSize (400, 500);
        this.registro.setVisible (true);
        System.out.println("fase 1 inicia");
        
    }
    
    public void registrar(Registro registro){
        String genero=null;
        Usuario user=new Usuario();
        UsuarioDAO usuarioDAO=new UsuarioDAO(); //variable de clase usuarioBaseDatos que aun no se ha hecho
        if(registro.rButHombre.isEnabled())
            genero = "Masculino";
        if(registro.rButMujer.isEnabled())
            genero = "Femenino";
        
        //System.out.println(genero);Para verificar el parametro pasado
        user.setNombre(registro.texNombre.getText()); //ejemplo de como meter los datos del usuario
        user.setApellido(registro.textApellido.getText());
        user.setSexo(genero);
        user.setCorreo(registro.textMail.getText());
        user.setUsuario(registro.textUser.getText());
        user.setContrasena(registro.textPassword.getText());
        user.setTelefono(58591623);
           
        usuarioDAO.crear(user);
        
        System.out.println("fase 4 registra ok"); //<-----verifica el nivel en que se encuentra 
         //   usuarioRegistro.setNombre(registro.texNombre.getText());
            //se pasan todos los parametros del registro
         //   usuarioBaseDatos=usuarioRegistro;//una vez lleno el usuario que regresaras lo pasas a el usuarioBaseDatos
        
    }
    
    public int verificaCampos(Registro registro){
        int verificador=1; //ahí que cambiar por cero, el uno sólo es para la prueba
        //usa la variable registro para que hacer las verificaciones y una vez que este todo correcto asegna 1 a la variable verificador
        if(registro.rButHombre.isEnabled() && this.registro.rButMujer.isEnabled()){
            JOptionPane.showMessageDialog(this.registro, "Elija tipo de género");
            verificador=0;
        }
       /* if(registro.texNombre.getText().isEmpty() && registro.textApellido.getText().isEmpty()
                && registro.textUser.getText().isEmpty() && registro.textPassword.getText().isEmpty()
                && registro.textPhone.getText().isEmpty()){
            JOptionPane.showMessageDialog(this.registro, "Campos obligatorios");
            verificador=0;
            
        }*/
        System.out.println("fase 2 verifica ");
        
        return verificador;
    }
    
    
 
    public void mouseClicked(MouseEvent e) {
       
    }


    public void mousePressed(MouseEvent e) {
    
    }


    public void mouseReleased(MouseEvent e) {
        
        if (e.getSource() == this.registro.butAceptar){

             verificador = verificaCampos(this.registro); // llamas ala fucion para verificar los campos 
             if(verificador == 1){
                System.out.println("fase 3 mouse");
                registrar(this.registro);
                
             }else{
                 
                 // una ventana auxiliar de registro fallido
             }
        }else if (e.getSource() == this.registro.butCancelar){
            registro.dispose();
        }
       
    }

  
    public void mouseEntered(MouseEvent e) {
       
    }


    public void mouseExited(MouseEvent e) {
        
    }
    
}
