/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javaapplication1;

import java.util.ArrayList;

/**
 *
 * @author enrique
 */
public class Entidades {

    protected ArrayList<Entidad> entidades;

    public Entidades(){

        this.entidades=new ArrayList();
        
    }

    public void Escribir(){
    for (int i =0;i<this.entidades.size();i++){
    this.entidades.get(i).escribir();
    }
    }

}
