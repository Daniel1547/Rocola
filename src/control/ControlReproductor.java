/*
 * @(#)Rockolav2.java 1.82 09/10/19
 *
 * Armando Rivera Cervantes
 * Copyright (c) Inc.
 * Iztapalapa CDMX México
 * Todos los derechos reservados.
 *
 * Más información y descripción del Copyright.
 *
 */

package control;

import TelematicoTools.FormMouse.formRoot;
import TelematicoTools.Platillos.DiscoAux;
import TelematicoTools.Platillos.DiscoOne;
import Ventanas.Login;
import Ventanas.Perfil;
import Ventanas.Primaria;
import Ventanas.Registro;
import Ventanas.Secundaria;
import controlBaseDatos.LikeDAO;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import modelo.ClaseCancion;
import modelo.ClaseGenero;


/**
 *
 * @author ArmandRC
 */
public class ControlReproductor implements MouseListener {
   
    int idUsuario;
    int idCancion;
    String nombreUsuario;
    Primaria pri = new Primaria();
    Secundaria sec = new Secundaria();
    
    //clases
 //   private claseCancion cCancion;
    
    DiscoOne d1 = new DiscoOne();
    DiscoAux dAux = new DiscoAux();
    formRoot mueveMouse = new formRoot();
    
    //lista de favorita
    private ArrayList<ClaseCancion> favoritas = new ArrayList<>();
    private ClaseCancion cCan;
    private ClaseGenero cGen;
    private int codigo;
    private int botonPlay = 0; // variable para controlar la reproducción
    
    DefaultListModel Flist = null;
    
    
    public ControlReproductor(Primaria pri, Secundaria sec,
            int idUser, String nombreUsuario){
        this.pri = pri;
        this.sec = sec;
        this.idUsuario = idUser;
        this.nombreUsuario = nombreUsuario;
        inicia();
    }
    
    private void inicia (){
        //Eventos de los botones
        this.pri.butSugerencias.addMouseListener (this);
        this.pri.butPausa.addMouseListener (this);
        this.pri.butPlay.addMouseListener (this);
        this.pri.butPreferencias.addMouseListener (this);
        this.pri.butStop.addMouseListener (this);
        this.pri.butSugerencias.addMouseListener (this);
        this.pri.butLike.addMouseListener(this);
        this.pri.butCerrar.addMouseListener(this);
        this.pri.butPerfil.addMouseListener(this);
        //Evento para los temas de la lista favoritas
        this.pri.jList1.addMouseListener (this);
        this.pri.laUsuario.setText(nombreUsuario);
        //se inicializa la ventana
        this.pri.setSize (650, 700);
        this.pri.setVisible (true);
        mueveMouse.ControlProgress (this.pri.jProgressBar1,
                this.pri.jSlider1, d1);
        d1.getTimeRun (this.pri.jProgressBar1);
    }

    @Override
    public void mouseClicked (MouseEvent e) {
        
    }

    @Override
    public void mousePressed (MouseEvent e) {
        
    }

    @Override
    public void mouseReleased (MouseEvent e) {
        
                
        if (e.getSource() == this.pri.butSugerencias){
             
            
        }else if (e.getSource() == this.pri.butPausa){
            botonPlay = 1;
            d1.pause();
        }else if (e.getSource() == this.pri.butPlay){
            switch (botonPlay){
                case 0:
                    idCancion = prePlay();
                break;
                case 1:
                    d1.continuar();
                break;      
            }
        }else  if (e.getSource() == this.pri.butPreferencias){
            getListaCanciones( getRutaCanciones(new File ("C:\\Users\\"
                    + "ArmandRC\\Documents\\NetBeansProjects\\Rocko2\\mus")));
            //mensaje("mis canciones");
        }else  if (e.getSource() == this.pri.butStop){
            d1.stop();
            //mensaje("Parar");
        }else if (e.getSource() == this.pri.butLike){
            
            if(idCancion == 0){
                JOptionPane.showMessageDialog(this.pri, "Intenta nuevamente");
            }else{
                LikeDAO like=new LikeDAO();
                like.darLike(idUsuario, idCancion );
                JOptionPane.showMessageDialog(this.pri, "Diste ¡¡¡Like!!!");
            }

        }else if (e.getSource() == this.pri.jList1){
            botonPlay=0;
            if (e.getClickCount() == 2){
                idCancion=prePlay();
            }   
        }else if (e.getSource() == this.pri.butCerrar){
           this.pri.removeAll();
           this.d1.stop();
           /* this.pri.setVisible(false);*/
           this.pri.dispose();
            Login login=new Login();
            
            login.setVisible(true);
        }else if (e.getSource() == this.pri.butPerfil){
            Perfil perfil=new Perfil();
            perfil.setVisible(true);
            
            //introducir objeto clase de Perfil
        }
    }

    @Override
    public void mouseEntered (MouseEvent e) {
        
    }

    @Override
    public void mouseExited (MouseEvent e) {

    }
    
    private int prePlay (){
        int id = 0;
        String idCan;
        
        for(ClaseCancion items: favoritas){
            if (items.getNombre().equals(this.pri.jList1.getSelectedValue())){
                play(items);
                id=Integer.parseInt(items.getCodigo());
                System.out.println(id);
            }
        }
        return id;
    }

    
    private void play (ClaseCancion item){
      //  cCancion = item;
        pri.nombreCan.setText (item.getNombre());
        if (item.getNombre().endsWith(".mp3")){
            d1.stop();
            d1.PlayMP3(item.getMusica());
        }else if (item.getNombre().endsWith(".wav")){
            d1.stop();
            d1.PlayWAV(item.getMusica());
        }
    }
    
    private ArrayList<File> getRutaCanciones (File root){
        ArrayList<File> canciones =new ArrayList<>();
        File[] archivos = root.listFiles();
        for ( File patch : archivos){
            if ( (patch.isDirectory()) && (!patch.isHidden())){
                canciones.addAll (getRutaCanciones(root));
                System.out.println("- - - >" + patch.getName() + "<- - -");
            }else{
                if(patch.getName().endsWith(".mp3") ||
                        patch.getName().endsWith(".wav")){
                    canciones.add(patch);
                }
            }
        }
        return canciones;
    }
    
    
    //generar una lista oculta de los temas que no son favoritos 
    private void getListaCanciones(ArrayList<File> rootFiles){
        Flist = new DefaultListModel();
        pri.jList1.setModel(Flist);
        
        new Thread() {
            @Override
            public void run() {
                favoritas = new ArrayList<> ();
                for ( File items : rootFiles){
                    cGen = new ClaseGenero();
                    cCan = new ClaseCancion();
                    //Obtener Genero
                    codigo = (int) Math.round(Math.random() * 9999);
                    // Lo siguiente sirve para crear el codigo como una cadena
                    cGen.setCodigo(codigo + "");
                    cGen.setGenero(dAux.getMetaDatos(items.toString()));
                    //Cancion
                    cCan.setCodigo(codigo + "");
                    cCan.setNombre(items.getName());
                    if(items.getName().endsWith(".mp3")){
                        cCan.setDuracion(dAux.duracionMP3(items.toString()));
                    }else if(items.getName().endsWith(".wav")){
                        cCan.setDuracion(dAux.duracionWav(items.toString()));
                    }
                    cCan.setMusica(dAux.getBytes(items.toString()));
                    cCan.setGenero(cGen);
                    favoritas.add(cCan);
                    Flist.addElement(items.getName());
                    System.out.println(items.getName());
                }
            }
        }.start();
    }

}
