/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uspceu.pia.consolidatedb;

import java.util.ArrayList;
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
    protected HashMap<String, String> mapeoAtributosFinalesAtributos1;
    protected HashMap<String, String> mapeoAtributosFinalesAtributos2;
    protected ArrayList<String> atributosFinales;
    protected String final_name;
    protected boolean dual;

    EntidadFinal(String name_map_1, String name_map_2, String key_1, String key_2, HashMap<String, Atributo> atributos1, HashMap<String, Atributo> atributos2, boolean dual) {
        this.dual = dual;
        this.name_map_1 = name_map_1;
        this.name_map_2 = name_map_2;
        this.key_1 = key_1;
        this.key_2 = key_2;
        this.atributos1 = atributos1;
        this.atributos2 = atributos2;
        this.mapeoAtributosFinalesAtributos1 = new HashMap<String, String>();
        this.mapeoAtributosFinalesAtributos2 = new HashMap<String, String>();
        this.atributosFinales = new ArrayList<String>();
        this.procesar();
    }

    EntidadFinal(String name_map_1, String key_1, HashMap<String, Atributo> atributos1, boolean dual) {
        this.dual = dual;
        this.name_map_1 = name_map_1;
        this.key_1 = key_1;
        this.atributos1 = atributos1;
        this.mapeoAtributosFinalesAtributos1 = new HashMap<String, String>();
        this.atributosFinales = new ArrayList<String>();
        this.procesar();
    }

    void escribir() {

        System.out.println("Nombre tabla en db 1: " + this.name_map_1);
        if (this.dual) {
            System.out.println("Nombre tabla en db 2: " + this.name_map_2);
        }
        System.out.println("Primary key en 1: " + this.key_1);
        if (this.dual) {
            System.out.println("Primary key en 2: " + this.key_2);
        }
        System.out.println("Atributos... ");

        Set<String> keySet1 = this.atributos1.keySet();
        for (String clave : keySet1) {
            atributos1.get(clave).escribir();
        }

        if (this.dual) {
            Set<String> keySet2 = this.atributos2.keySet();
            for (String clave : keySet2) {
                atributos2.get(clave).escribir();
            }
        }

    }

    private void procesar() {

        if (this.dual) //Encontrar correspondencia entre atributos tambien elegir nombre de la entidad
        {
            this.final_name = name_map_1;
        } else {
            //Copiar los atributos 1
        }
    }

    public String getFinal_name() {
        return final_name;
    }

    private void listarAtributosFinales(){
        for(String atributo:atributosFinales){
            System.out.print(atributo+" ");
        }
        System.out.println("");
    }
    
}
