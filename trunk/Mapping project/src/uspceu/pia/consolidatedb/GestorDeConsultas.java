/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uspceu.pia.consolidatedb;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author USUARIO
 */
class GestorDeConsultas {

    Entidades entidades1;
    Entidades entidades2;
    EntidadesFinales entidadesFinales;

    GestorDeConsultas(Entidades entidades1, Entidades entidades2, EntidadesFinales entidadesFinales) {
        this.entidades1 = entidades1;
        this.entidades2 = entidades2;
        this.entidadesFinales = entidadesFinales;
    }

    void realizarConsulta() {
        String entidadIntroducida = this.obtenerEntidad();
   //     ArrayList<String> atributosIntroducidos=this.obtenerAtributos(entidadIntroducida);
        ArrayList<String> atributosIntroducidos = this.obtenerAtributos(entidadIntroducida);
        this.ejecutarConsulta(entidadIntroducida,atributosIntroducidos);

    }

    private String obtenerEntidad() {

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Introduce la entidad de la que quieres realizar la consulta");
            entidadesFinales.escribirlista();
            String entidadIntroducida = sc.next();
            if (existeEntidad(entidadIntroducida)) {
                return entidadIntroducida;
            }
            System.out.println(entidadIntroducida + " no existe como entidad");
            System.out.println("Lista de entidades disponbiles:");
            entidadesFinales.escribirlista();
        }
    }

    /*private ArrayList<String> obtenerAtributos(String entidadIntroducida) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Introduce los atributos separados con comas o un asterisco si quieres todos");
            String cadenaAtributos = sc.next();
            if(cadenaAtributos.compareTo("*")==0)
            {
             return entidadesFinales.entidadesfinales.get(entidadIntroducida).atributosFinales;
            }
            String[] split = cadenaAtributos.split(",");
            ArrayList<String> atributos = new ArrayList<String>();
            for (int i = 0; i < split.length; i++) {
                if (perteneceAtributo(split[i], entidadIntroducida)) {
                    atributos.add(split[i]);
                } else {
                    System.out.println(split[i] + " no existe como atributo final");
                }
            }
            if (atributos.size() == split.length) {
                return atributos;
            }
            System.out.println("Lista de atributos:");
            this.listarAtributos(entidadIntroducida);
        }
    }*/

    private boolean existeEntidad(String entidad) {
        if (entidadesFinales.entidadesfinales.get(entidad) != null) {
            return true;
        }
        return false;
    }

    private boolean perteneceAtributo(String atributo, String entidad) {
        return this.entidadesFinales.entidadesfinales.get(entidad).atributosFinales.contains(atributo);
    }

    private void listarAtributos(String entidadIntroducida) {
        this.entidadesFinales.entidadesfinales.get(entidadIntroducida);
    }

    private void ejecutarConsulta(String entidadIntroducida, ArrayList<String> atributosIntroducidos) {
        
        String sql="select * from "+entidades1.nombre_schema+"."+entidadIntroducida+"left outer join";


    }
}