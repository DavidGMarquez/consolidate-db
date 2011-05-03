/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.util.ArrayList;

/**
 *
 * @author kike
 */
public class Mapeado {

    protected String nombre_1;
    protected String tabla_1;
    protected String nombre_2;
    protected String tabla_2;

    Mapeado(String nombre_1, String tabla_1, String nombre_2, String tabla_2) {
        this.nombre_1 = nombre_1;
        this.tabla_1 = tabla_1;
        this.nombre_2 = nombre_2;
        this.tabla_2 = tabla_2;

    }

    void mostrar() {
        System.out.println("Mapping: \n");
        System.out.println("Tabla: " + this.nombre_1 + " de la " + this.tabla_1);
        System.out.println("Tabla: " + this.nombre_2 + " de la " + this.tabla_2);
    }


}
