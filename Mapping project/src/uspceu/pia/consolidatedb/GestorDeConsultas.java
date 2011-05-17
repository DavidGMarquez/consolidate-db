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
        ArrayList<String> atributosIntroducidos=this.obtenerAtributos(entidadIntroducida);

    }

    private String obtenerEntidad() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Introduce la entidad de la que quieres realizar la consulta");
            String entidadIntroducida = sc.next();
            if (entidadesFinales.entidadesfinales.get(entidadIntroducida) != null) {
                return entidadIntroducida;
            }
            System.out.println(entidadIntroducida+" no existe como entidad");
            System.out.println("Lista de entidades disponbiles:");
            entidadesFinales.escribirlista();
        }
    }

    private ArrayList<String> obtenerAtributos(String entidadIntroducida) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Introduce los atributos separados con comas o un asterisco si quieres todos");
            String entidadIntroducida = sc.next();
            if (entidadesFinales.entidadesfinales.get(entidadIntroducida) != null) {
                return entidadIntroducida;
            }
            System.out.println(entidadIntroducida+" no existe como entidad");
            System.out.println("Lista de entidades disponbiles:");
            entidadesFinales.escribirlista();
        }
    }

}



