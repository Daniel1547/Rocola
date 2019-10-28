/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rocko2;

import Ventanas.Login;
import Ventanas.Primaria;
import Ventanas.Registro;
import Ventanas.Secundaria;
import control.ControlLista;
import control.ControlRegistro;
import control.ControlReproductor;
import modelo.Usuario;

/**
 *
 * @author ArmandRC
 */
public class Rocko2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
      /*  Primaria pri = new Primaria();
        Secundaria sec = new Secundaria();
         
        ControlReproductor cR = new ControlReproductor(pri,sec);
        ControlLista cL = new ControlLista(sec);
        
       /* Registro registro = new Registro();
        
        ControlRegistro iniciaRegistro = new ControlRegistro(registro);*/
      
      Login login=new Login();
      login.setVisible(true);
        
    }
    
}
