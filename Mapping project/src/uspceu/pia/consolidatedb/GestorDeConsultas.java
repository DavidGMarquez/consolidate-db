/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uspceu.pia.consolidatedb;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        ArrayList<String> atributosIntroducidos = this.obtenerAtributos(entidadIntroducida);
        String consultaSQL = this.generarConsulta(entidadIntroducida, atributosIntroducidos);
        ResultSet rs = this.ejecutarConsulta(consultaSQL, "pia", "pia");
        if (rs != null) {
            procesarSalida(rs, entidadIntroducida, atributosIntroducidos);
        } else {
            System.out.println("Error en la consulta");
        }

    }

    private String obtenerEntidad() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Introduce la entidad de la que quieres realizar la consulta");
            String entidadIntroducida = sc.next();
            if (existeEntidad(entidadIntroducida)) {
                return entidadIntroducida;
            }
            System.out.println(entidadIntroducida + " no existe como entidad");
            System.out.println("Lista de entidades disponbiles:");
            entidadesFinales.escribirlista();
        }
    }

    private ArrayList<String> obtenerAtributos(String entidadIntroducida) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Introduce los atributos separados con comas o un asterisco si quieres todos");
            String cadenaAtributos = sc.next();
            if (cadenaAtributos.compareTo("*") == 0) {
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
    }

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
        ArrayList<String> atributosFinales = this.entidadesFinales.entidadesfinales.get(entidadIntroducida).atributosFinales;
        for (String atributo : atributosFinales) {
            System.out.print(atributo + " ");
        }
        System.out.println("");
    }

    private String generarConsulta(String entidadIntroducida, ArrayList<String> atributosIntroducidos) {
        Entidad entidad1 = entidades1.entidades.get(entidadesFinales.entidadesfinales.get(entidadIntroducida).name_map_1);
        Entidad entidad2 = entidades2.entidades.get(entidadesFinales.entidadesfinales.get(entidadIntroducida).name_map_2);
        EntidadFinal entidadFinal = entidadesFinales.entidadesfinales.get(entidadIntroducida);
        String tabla1 = entidades1.nombre_schema + "." + entidad1.name;
        String tabla2 = entidades2.nombre_schema + "." + entidad2.name;
        String claveTabla1 = entidades1.nombre_schema + "." + entidad1.name + "." + entidad1.key;
        String claveTabla2 = entidades2.nombre_schema + "." + entidad2.name + "." + entidad2.key;
        ArrayList<String> atributosFinales = new ArrayList<String>(this.entidadesFinales.entidadesfinales.get(entidadIntroducida).atributosFinales);
        String atributos = "";
        for (String atributoFinal : atributosFinales) {
            String atributo1 = entidadFinal.mapeoAtributosFinalesAtributos1.get(atributoFinal);
            String atributo2 = entidadFinal.mapeoAtributosFinalesAtributos2.get(atributoFinal);
            if (atributo1 != null) {
                atributos = atributos.concat(tabla1 + "." + atributo1 + " as " + atributoFinal + "1 ,");
            }
            if (atributo2 != null) {
                atributos = atributos.concat(tabla2 + "." + atributo2 + " as " + atributoFinal + "2 ,");
            }
        }
        atributos = atributos.substring(0, atributos.length() - 1);




        String sql = "select " + atributos + " from " + tabla1 + " left outer join " + tabla2 + " on "
                + claveTabla1 + "=" + claveTabla2
                + " union "
                + "select * from " + tabla1 + " right outer join " + tabla2 + " on "
                + claveTabla1 + "=" + claveTabla2 + ";";
        System.out.println("Consulta:");
        System.out.println(sql);
        return sql;
    }

    private ResultSet ejecutarConsulta(String consultaSQL, String user, String password) {
        Connection conexion = null;
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/", user, password);
        } catch (SQLException ex) {
            Logger.getLogger(GestorDeConsultas.class.getName()).log(Level.SEVERE, null, ex);
        }
        Statement s = null;
        try {
            s = conexion.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(GestorDeConsultas.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            ResultSet rs = s.executeQuery(consultaSQL);
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(GestorDeConsultas.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    private void procesarSalida(ResultSet rs, String entidadIntroducida, ArrayList<String> atributosIntroducidos) {
        System.out.println("Consulta sobre la entidad " + entidadIntroducida);
        try {
            while (rs.next()) {
                for (String atributoFinal : atributosIntroducidos) {
                    System.out.print("Atributo:" + atributoFinal);
                    String tipoAtributo1 = obtenerTipo(atributoFinal, entidadIntroducida, 1);
                    String tipoAtributo2 = obtenerTipo(atributoFinal, entidadIntroducida, 2);
                    if (tipoAtributo1.compareTo(tipoAtributo2) != 0) {
                        System.out.println(toStringAtributo(rs, tipoAtributo1, atributoFinal, 1));
                        System.out.println(toStringAtributo(rs, tipoAtributo2, atributoFinal, 2));
                    } else {
                        toStringDosAtributos(rs, tipoAtributo1, atributoFinal);
                    }
                    System.out.println("");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestorDeConsultas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String obtenerTipo(String atributo, String entidad, int db) {
        EntidadFinal entidadfinal = this.entidadesFinales.entidadesfinales.get(entidad);
        String atributo1 = entidadfinal.mapeoAtributosFinalesAtributos1.get(atributo);
        if (db == 1) {
            if (atributo1 != null) {
                return entidadfinal.atributos1.get(atributo1).type;
            }
        }
        if (db == 2) {
            String atributo2 = entidadfinal.mapeoAtributosFinalesAtributos2.get(atributo);
            if (atributo2 != null) {
                return entidadfinal.atributos2.get(atributo2).type;
            }
        }
        return null;
    }

    private String toStringAtributo(ResultSet rs, String tipoAtributo, String atributoFinal, int i) {
        if (tipoAtributo.contains("varchar")) {
            try {
                return (rs.getString(atributoFinal + i));
            } catch (SQLException ex) {
                Logger.getLogger(GestorDeConsultas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (tipoAtributo.contains("int")) {
            try {

                return (Integer.toString(rs.getInt(new String(atributoFinal + i))));
            } catch (SQLException ex) {
                Logger.getLogger(GestorDeConsultas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return "-*-";

    }

    private String toStringDosAtributos(ResultSet rs, String tipoAtributo, String atributoFinal) {
        String atributo1=toStringAtributo(rs, tipoAtributo, atributoFinal, 1);
        String atributo2=toStringAtributo(rs, tipoAtributo, atributoFinal, 2);
        if(atributo1.compareTo(atributo2)!=0){
            return atributo1+" / "+atributo2;
        }
        return atributo1;
    }
}
