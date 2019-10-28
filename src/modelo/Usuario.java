/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author ArmandRC
 */
public class Usuario {
    
     private int id;
    private String nombre;
    private String apellido;
    private String sexo;
    private String correo;
    private String usuario;
    private String contrasena;
    private int telefono;
    
    public Usuario(){
        
    }
    
    public Usuario(String usuario, String contrasena){
        this.usuario = usuario;
        this.contrasena = contrasena;
    }
    
    public Usuario(String nombre, String apellido, String sexo,
            String correo, String usuario, String contrasena,
            int telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.sexo = sexo;
        this.correo = correo;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.telefono = telefono;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public boolean esCorreo(String correo){
        Pattern pat = null;
        Matcher mat = null;
        pat = Pattern.compile("([A-Za-z0-9])*+[.]+([A-Za-z0-9])*@uacm.mx");
        mat = pat.matcher(correo);
        if(mat.find())
            return true;
        else
            return false;
    }
    
}
