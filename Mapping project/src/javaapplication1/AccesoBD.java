/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javaapplication1;
import java.sql.*;

/**
 *
 * @author enrique
 */
public class AccesoBD {

    public AccesoBD() {




    /**
     * Crea una instancia de la clase MySQL y realiza todo el código
     * de conexión, consulta y muestra de resultados.
        // Se mete todo en un try por los posibles errores de MySQL**/
        try
        {
            // Se registra el Driver de MySQL
          //  DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());

            // Se obtiene una conexión con la base de datos. Hay que
            // cambiar el usuario "root" y la clave "la_clave" por las
            // adecuadas a la base de datos que estemos usando.
          //  Class.forName("com.mysql.jdbc.Driver");
            Connection conexion = DriverManager.getConnection (
                "jdbc:mysql://localhost:3306/elements","root", "root");

            // Se crea un Statement, para realizar la consulta
            Statement s = conexion.createStatement();

            // Se realiza la consulta. Los resultados se guardan en el
            // ResultSet rs
            ResultSet rs = s.executeQuery ("select * from elements");

            // Se recorre el ResultSet, mostrando por pantalla los resultados.
            while (rs.next())
            {
                System.out.println (rs.getString (1) + " " + rs.getString (2)+ " " +
                    rs.getString (3));
            }

            // Se cierra la conexión con la base de datos.
            conexion.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


}
