/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uspceu.pia.consolidatedb;

import java.util.ArrayList;

/**
 *
 * @author enrique
 */
public class Entidades {

    protected ArrayList<Entidad> entidades;
    protected String nombre_schema;

    public Entidades(String nombre_schema) {

        this.entidades = new ArrayList();
        this.nombre_schema = nombre_schema;

    }

    public void Escribir() {
        for (int i = 0; i < this.entidades.size(); i++) {
            this.entidades.get(i).escribir();
        }
    }
}
