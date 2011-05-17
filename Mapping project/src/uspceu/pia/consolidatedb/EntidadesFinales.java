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
public class EntidadesFinales {

    protected HashMap<String, EntidadFinal> entidadesfinales;

    public EntidadesFinales() {

        this.entidadesfinales = new HashMap<String, EntidadFinal>();

    }

    public void Escribir() {
        for (String nombre : entidadesfinales.keySet()) {
            this.entidadesfinales.get(nombre).escribir();
        }
    }
}

