/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uspceu.pia.consolidatedb;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author enrique
 */
public class Entidades {

    protected HashMap<String, Entidad> entidades;
    protected String nombre_schema;

    public Entidades(String nombre_schema) {

        this.entidades = new HashMap<String, Entidad>();
        this.nombre_schema = nombre_schema;

    }

    public void Escribir() {
        for (String nameEntidad : entidades.keySet()) {
            this.entidades.get(nameEntidad).escribir();
        }
    }
}

