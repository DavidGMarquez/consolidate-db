/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author enrique
 */
public class CrearEntidades {

    Entidades entidades;

    public CrearEntidades(String nombre, String usuario, String pass) {
        String nombre_schema = nombre;
        String user = usuario;
        String password = pass;
        boolean debug = true;
        this.entidades = new Entidades(nombre_schema);


        try {
            if (debug) {
                System.out.println("\nBase de datos: " + nombre_schema + " ");
            }
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + nombre_schema, user, password);
            Statement s = conexion.createStatement();
            ResultSet rs = s.executeQuery("show tables");

            int i = 1;
            while (rs.next()) {

                if (debug) {
                    System.out.println("\tTabla: " + rs.getString(1) + " ");
                }
                String key = "";
                Statement s2 = conexion.createStatement();
                ResultSet rs2 = s2.executeQuery("show columns from " + rs.getString(1));
                String nombredb = nombre;
                String nombretabla = rs.getString(1);
                HashMap<String, Atributo> atributostabla = new HashMap<String, Atributo>();

                int orden = 1;
                if (debug) {
                    System.out.println("\tAtributos:");
                }

                while (rs2.next()) {

                    orden++;
                    if (debug) {
                        System.out.println("\t\t" + rs2.getString(1) + " de tipo " + rs2.getString(2));
                    }
                    if (rs2.getString(4).equals("PRI")) {
                        if (debug) {
                            System.out.println("\t\tClave principal");
                        }
                        key = rs2.getString(1);
                    }
                    Atributo atributo = new Atributo(rs2.getString(1), rs2.getString(2), orden, nombredb, nombretabla);
                    atributostabla.put(rs2.getString(1), atributo);
                }
                Entidad entidad = new Entidad(rs.getString(1), atributostabla, key);
                entidades.entidades.add(entidad);
            }





        } catch (SQLException ex) {
            Logger.getLogger(CrearEntidades.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
