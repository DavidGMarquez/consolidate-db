/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package uspceu.pia.consolidatedb;

class Main
{
    public static void main(String[] args)
    {
       
        CrearEntidades entidades1 = new CrearEntidades("piadb", "pia", "pia");
        CrearEntidades entidades2 = new CrearEntidades("piaDbaux", "pia", "pia");
        CrearMapeados mapeado=new CrearMapeados(entidades1,entidades2);
        mapeado.entidadesFinales.Escribir();
        GestorDeConsultas gestorDeConsultas=new GestorDeConsultas(mapeado);


        //Obtener resultado global
        
    }

    /*
     * Consultas
     *
     Obtener resultados con un join
     select * from piadb.personas natural join piadbaux.personas
     *
     Obtener resultados que difieren (que no estan en las dos tablas)
     select * from piadb.personas left outer join piadbaux.personas on piadbaux.personas.idpersonas=piadb.personas.idpersonas
where piadbaux.personas.idpersonas is null
union all
select * from piadb.personas right outer join piadbaux.personas on piadbaux.personas.idpersonas=piadb.personas.idpersonas
where piadb.personas.idpersonas is null;
     *
     Obtener todo junto
     select * from piadb.personas left outer join piadbaux.personas on piadbaux.personas.idpersonas=piadb.personas.idpersonas
union all
select * from piadb.personas right outer join piadbaux.personas on piadbaux.personas.idpersonas=piadb.personas.idpersonas
where piadb.personas.idpersonas is null;
     Como elegir un conjunto de atributos
     *
     select piadb.personas.nombre as name,piadb.personas.email,piadb.personas.idpersonas as emailo
,piadbaux.personas.apellido as apellido
from piadb.personas
left outer join piadbaux.personas on piadbaux.personas.idpersonas=piadb.personas.idpersonas
union all
select piadb.personas.nombre as name,piadb.personas.email,piadb.personas.idpersonas as emailo
,piadbaux.personas.apellido as apellido
from piadb.personas
right outer join piadbaux.personas on piadbaux.personas.idpersonas=piadb.personas.idpersonas
where piadb.personas.idpersonas is null;
     *
     *
     *
     */
}
