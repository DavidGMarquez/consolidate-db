/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author enrique
 */
public class EntidadFinal {

    protected String name_map_1;
    protected String name_map_2;
    protected String key_1;
    protected String key_2;
    protected HashMap<String, Atributo> atributos1;
        protected HashMap<String, Atributo> atributos2;

    EntidadFinal(String name_map_1, String name_map_2, String key_1, String key_2, HashMap<String, Atributo> atributos1, HashMap<String, Atributo> atributos2) {
        this.name_map_1 = name_map_1;
        this.name_map_2 = name_map_2;
        this.key_1 = key_1;
        this.key_2 = key_2;
        this.atributos1 = atributos1;
        this.atributos2 = atributos2;
    }

    void escribir() {
        System.out.println("Nombre tabla en db 1: " + this.name_map_1);
        System.out.println("Nombre tabla en db 2: " + this.name_map_2);
        System.out.println("Primary key en 1: " + this.key_1);
        System.out.println("Primary key en 2: " + this.key_2);
        System.out.println("Atributos... ");

        Set<String> keySet1 = this.atributos1.keySet();
        for(String clave:keySet1)
        {
            atributos1.get(clave).escribir();
        }



        Set<String> keySet2 = this.atributos2.keySet();
        for(String clave:keySet2)
        {
            atributos2.get(clave).escribir();
        }



    }
}
