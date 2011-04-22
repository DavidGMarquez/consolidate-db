/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javaapplication1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

/**
 *
 * @author enrique
 */

public class Entidad {

    protected String name;
    protected String key;
    protected HashMap <String,Atributo> atributos;
    protected String name_map;
    protected String foreign_key;
   // protected ArrayList<String> attributes;
    
    public Entidad(String name, HashMap<String, Atributo> atributos, String key){

        this.name=name;
        this.atributos=atributos;
        this.key=key;
       // this.foreign_key=foreign_key;
    }

    void escribir() {
        System.out.println("Nombre tabla:"+ this.name);
        System.out.println("Primary key:"+ this.key);
        System.out.println("Atributos:");
     //   for (int i =1;i<this.attributes.size();i++){
       //     System.out.println( this.attributes.get(i));
        //}
    }

}
