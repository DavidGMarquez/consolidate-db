/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uspceu.pia.consolidatedb;

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
        this.entidades1=entidades1;
        this.entidades2=entidades2;
        this.entidadesFinales=entidadesFinales;
    }

    void realizarConsulta() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce la entidad de la que quieres realizar la consulta");
        String entidadIntroducida = sc.next();
        boolean encontrado=false;
   



        System.out.println("Ha introducido la entidad:"+entidadIntroducida);
        System.out.println("Introduzca los campos que quiere ver");
    }
}
