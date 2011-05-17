/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uspceu.pia.consolidatedb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            HashMap<String, Atributo> atributos1_temporal = new HashMap<String, Atributo>();
            HashMap<String, Atributo> atributos2_temporal = new HashMap<String, Atributo>();
            atributos1_temporal.putAll(this.atributos1);
            atributos2_temporal.putAll(this.atributos2);
            this.final_name = name_map_1;
            for (String nameAtributo1 : this.atributos1.keySet()) {
                for (String nameAtributo2 : this.atributos2.keySet()) {
                    if (0 == nameAtributo1.compareTo(nameAtributo2)) {
                        System.out.println("Atributo: " + nameAtributo1 + " " + nameAtributo2);
                        System.out.println("¿ Es correcto este mapeado de atributos ? (pulse tecla 's' si esta correcto)");
                        Scanner sc = new Scanner(System.in);
                        String caracter = sc.next();
                        if (0 == caracter.compareTo("s")) {
                            System.out.println("El atributo tendra el nombre de: " + nameAtributo1);
                            this.atributosFinales.add(nameAtributo1);
                            this.mapeoAtributosFinalesAtributos1.put(nameAtributo1, nameAtributo1);
                            this.mapeoAtributosFinalesAtributos2.put(nameAtributo1, nameAtributo2);
                            atributos1_temporal.remove(nameAtributo1);
                            atributos2_temporal.remove(nameAtributo2);
                        }
                    }
                }


            }
            for (String nameAtributo1 : atributos1_temporal.keySet()) {
                this.atributosFinales.add(nameAtributo1);
                this.mapeoAtributosFinalesAtributos1.put(nameAtributo1, nameAtributo1);
                this.mapeoAtributosFinalesAtributos2.put(nameAtributo1, null);
            }
            for (String nameAtributo2 : atributos2_temporal.keySet()) {
                this.atributosFinales.add(nameAtributo2);
                this.mapeoAtributosFinalesAtributos1.put(nameAtributo2, null);
                this.mapeoAtributosFinalesAtributos2.put(nameAtributo2, nameAtributo2);
            }

        } else {
            this.final_name = name_map_1;
            for (String nameAtributo1 : this.atributos1.keySet()) {
                this.atributosFinales.add(nameAtributo1);
                this.mapeoAtributosFinalesAtributos1.put(nameAtributo1, nameAtributo1);
            }
        }
    }

    public String getFinal_name() {
        return final_name;
    }

    public void listarAtributosFinales() {
        for (String atributo : atributosFinales) {
            System.out.print(atributo + " ");
        }
        System.out.println("");
    }

    public void listarAtributosFinalesMapeados() {
        String valor = "";

        for (String atributo : this.mapeoAtributosFinalesAtributos1.keySet()) {

            if (this.mapeoAtributosFinalesAtributos1.get(atributo) == null) {
                valor = "null";
            } else {
                valor = this.mapeoAtributosFinalesAtributos1.get(atributo);
            }
            System.out.print("nombre mapeado:" + atributo + " nombre real: " + valor + "\n");
        }
        if(dual){
        for (String atributo : this.mapeoAtributosFinalesAtributos2.keySet()) {

            if (this.mapeoAtributosFinalesAtributos2.get(atributo) == null) {
                valor = "null";
            } else {
                valor = this.mapeoAtributosFinalesAtributos2.get(atributo);
            }
            System.out.print("nombre mapeado:" + atributo + " nombre real: " + valor + "\n");
        }
        }
    }
}
