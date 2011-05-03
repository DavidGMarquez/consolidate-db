/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javaapplication1;

import java.util.HashMap;

/**
 *
 * @author enrique
 */
public class EntidadFinal {

    protected String name_map_1;
    protected String name_map_2;

    protected String key;

    protected HashMap <String,Atributo> atributos;

    EntidadFinal(String name_map_1, String name_map_2, String key, HashMap<String, Atributo> atributos) {
        this.name_map_1=name_map_1;
        this.name_map_2=name_map_2;
        this.key=key;
        this.atributos=atributos;
    }

     void escribir() {
        System.out.println("Nombre tabla en db 1: "+ this.name_map_1);
        System.out.println("Nombre tabla en db 2: "+ this.name_map_2);
        System.out.println("Primary key: "+ this.key);
        System.out.println("Atributos... ");
     
    }
    

}
