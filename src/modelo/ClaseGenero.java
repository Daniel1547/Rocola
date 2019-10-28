/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author ArmandRC
 */
public class ClaseGenero {
    
    private String codigo;
    private String genero;
    
    public ClaseGenero () {
    }
    
    public ClaseGenero ( String codigo, String genero ){
        this.codigo = codigo;
        this.genero = genero;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    @Override
    public String toString() {
        return genero;
    }

    
    
}
