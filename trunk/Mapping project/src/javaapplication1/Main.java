/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javaapplication1;

class Main
{
    public static void main(String[] args)
    {
       
        CrearEntidades entidades1 = new CrearEntidades("piadb", "root", "root");
        CrearEntidades entidades2 = new CrearEntidades("piaDbaux", "root", "root");
        CrearMapeados mapeado=new CrearMapeados(entidades1,entidades2);
        mapeado.entidadesFinales.Escribir();
        //Obtener resultado global
        
    }
}
