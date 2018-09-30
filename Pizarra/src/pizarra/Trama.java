/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizarra;

import java.awt.*;
import java.io.*;
import java.util.*;

/**
 *
 * @author user
 */
public class Trama implements Serializable{

    private Vector v = null;
    private String s = null;
    
    public Trama(){/*Constructor de la clase trama*/
        this.v = new Vector();
        this.s = new String();
    }
    
    /*Este metodo asigna un nuevo elemento de tipo poligono al vector*/
    public void set_poygon_to_vector(Polygon p){
        this.v.add(p);
    }
    
    /*Asigna un String al dato miembro s*/
    public void setString(String s){
        this.s = null;
        this.s = s;
    }
    
    /*Retorna el vector*/
    public Vector getVector(){
        return this.v;
    }
    
    /*Retorna el string*/
    public String getString(){
    
        return this.s;
    }
    
    /*Este metodo se considero necesaio para vaciar el vector al momemto de borrar la pizarra*/
    public void vaciarVector(){
        this.v.clear();
        
    }
    
}
