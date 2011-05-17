/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uspceu.pia.consolidatedb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kike
 */
class GeneradorMapeados {

    protected HashMap<String, Entidad> entidades1;
    protected HashMap<String, Entidad> entidades2;
    protected EntidadesFinales entidadesFinales;

    GeneradorMapeados(Entidades entidades1, Entidades entidades2) {

        //Obtención de las entidades de un modo más accesible
        this.entidades1 = new HashMap<String, Entidad>(entidades1.entidades);
        this.entidades2 = new HashMap<String, Entidad>(entidades2.entidades);
        this.entidadesFinales = new EntidadesFinales();
        //comparamos nombres de Entidades de ambas base de datos y mostramos los posibles mappings
        for (String nameEntidad1 : this.entidades1.keySet()) {
            for (String nameEntidad2 : this.entidades2.keySet()) {
                if (0 == this.entidades1.get(nameEntidad1).name.compareTo(this.entidades2.get(nameEntidad2).name)) {
                    Mapeado mapeado = new Mapeado(this.entidades1.get(nameEntidad1).name, entidades1.nombre_schema, this.entidades1.get(nameEntidad2).name, entidades2.nombre_schema);
                    mapeado.mostrar();
                    try {
                        System.out.println("¿ Es correcto este mapeado ? (pulse tecla 's' si esta correcto)");
                        char caracter = (char) System.in.read();
                        if (caracter == 's') {
                            System.out.println("Creando el mapping... ");
                            this.crearEntidadFinalDoble(nameEntidad1, nameEntidad2);
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(GeneradorMapeados.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
        }
    }

    private void crearEntidadFinalDoble(String nameEntidad1,String nameEntidad2) {
        HashMap<String, Atributo> atributos1 = new HashMap<String, Atributo>();
        HashMap<String, Atributo> atributos2 = new HashMap<String, Atributo>();
        atributos1.putAll(this.entidades1.get(nameEntidad1).atributos);
        atributos2.putAll(this.entidades2.get(nameEntidad2).atributos);
        EntidadFinal e = new EntidadFinal(this.entidades1.get(nameEntidad1).name, this.entidades2.get(nameEntidad2).name, this.entidades1.get(nameEntidad1).key, this.entidades2.get(nameEntidad2).key, atributos1, atributos2, true);
        this.entidadesFinales.entidadesfinales.put(e.final_name, e);
    }

    public EntidadesFinales getEntidadesFinales() {
        return entidadesFinales;


    }
}


