/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javaapplication1;

/**
 *
 * @author enrique
 */
public class Atributo {

    protected String name;
    protected String type;
    protected int order;
    protected String database;
    protected String table_name;

    public Atributo(String name, String type, int order, String database, String table_name) {

        this.name = name;
        this.type = type;
        this.order = order;
        this.database = database;
        this.table_name = table_name;

    }



}
