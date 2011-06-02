/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uspceu.pia.consolidatedb;

class Main {

    public static void main(String[] args) {
        GeneradorEntidades entidades1 = new GeneradorEntidades("piadb", "pia", "pia");
        GeneradorEntidades entidades2 = new GeneradorEntidades("piaDbaux", "pia", "pia");
        GeneradorMapeados mapeado = new GeneradorMapeados(entidades1.getEntidades(), entidades2.getEntidades());
        System.out.println("---------");
        mapeado.entidadesFinales.escribirlistaConAtributos();
        GestorDeConsultas gestorDeConsultas = new GestorDeConsultas(entidades1.getEntidades(), entidades2.getEntidades(), mapeado.getEntidadesFinales());
        gestorDeConsultas.realizarConsulta();
    }
}
