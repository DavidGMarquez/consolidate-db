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
class CrearMapeados {

    protected ArrayList<Entidad> entidades1;
    protected ArrayList<Entidad> entidades2;
    protected EntidadesFinales entidadesFinales;

    CrearMapeados(CrearEntidades entidades1, CrearEntidades entidades2) {

        //Obtención de las entidades de un modo más accesible
        this.entidades1 = entidades1.entidades.entidades;
        this.entidades2 = entidades2.entidades.entidades;
        this.entidadesFinales=new EntidadesFinales();
        //comparamos nombres de Entidades de ambas base de datos y mostramos los posibles mappings
        for (int i = 0; i < this.entidades1.size(); i++) {
            for (int j = 0; j < this.entidades2.size(); j++) {
                if (0 == this.entidades1.get(i).name.compareTo(this.entidades2.get(j).name)) {
                    Mapeado mapeado=new Mapeado(this.entidades1.get(i).name,entidades1.entidades.nombre_schema,this.entidades1.get(i).name,entidades2.entidades.nombre_schema);
                    mapeado.mostrar();
                    try {
                        System.out.println("¿ Es correcto este mapeado ? (pulse tecla 's' si esta correcto)");
                        char caracter = (char) System.in.read();
                        if(caracter=='s'){
                            System.out.println("Creando el mapping... ");
                            this.crearEntidadFinalDoble(i,j);
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(CrearMapeados.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
        }

    }

    private void crearEntidadFinalDoble(int i,int j) {
         HashMap<String, Atributo> atributos1 = new HashMap<String, Atributo>();
         HashMap<String, Atributo> atributos2 = new HashMap<String, Atributo>();
         atributos1.putAll(this.entidades1.get(i).atributos);
         atributos2.putAll(this.entidades2.get(j).atributos);
        EntidadFinal e=new EntidadFinal(this.entidades1.get(i).name,this.entidades2.get(j).name,this.entidades1.get(i).key,this.entidades2.get(j).key,atributos1,atributos2,true);
        this.entidadesFinales.entidadesfinales.add(e);
    }
}


