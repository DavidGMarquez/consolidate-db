/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javaapplication1;

import org.mindswap.pellet.jena.PelletReasonerFactory;

import com.clarkparsia.pellet.sparqldl.jena.SparqlDLExecutionFactory;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import javax.lang.model.util.ElementScanner6;

class Utils {
    public static void consultaSPARQL(OntModel m, String query)
    {

		// Now read the query file into a query object
		Query q = QueryFactory.read( query );

		// Create a SPARQL-DL query execution for the given query and
		// ontology model
		QueryExecution qe = SparqlDLExecutionFactory.create( q, m );

		// We want to execute a SELECT query, do it, and return the result set
		ResultSet rs = qe.execSelect();

		// Print the query for better understanding
		System.out.println(q.toString());

		// There are different things we can do with the result set, for
		// instance iterate over it and process the query solutions or, what we
		// do here, just print out the results
		ResultSetFormatter.out( rs );

		// And an empty line to make it pretty
		System.out.println();
	}
}

class Prueba
{
    public static void probarPPO()
    {
        String[]	queries		= new String[]
        {
            "file:C:/owl/examples/cq1.sparql",
            "file:C:/owl/examples/cq2.sparql",
            "file:C:/owl/examples/cq3.sparql",
            "file:C:/owl/examples/cq4.sparql",
            "file:C:/owl/examples/cq5.sparql",
            "file:C:/owl/examples/cq6.sparql",
            "file:C:/owl/examples/cq7.sparql",
            "file:C:/owl/examples/cq8.sparql"
        };

        String	fileOnt	= "file:C:/owl/ppo.owl";

        probarOntologia(queries, fileOnt);
   }

    public static void probarPizzaOntology()
    {
        String[]	queries		= new String[]
        {
            "file:C:/owl/examples/cqPizza.sparql"
        };

        String	fileOnt	= "http://www.co-ode.org/ontologies/pizza/2007/02/12/pizza.owl";

        probarOntologia(queries, fileOnt);
   }
    public static void probarMcCarthy()
    {
        String[]	queries		= new String[]
        {
            "file:examples/cqPizza.sparql"
        };

        String	fileOnt	= "http://mccarthy.dia.fi.upm.es/edu/resource/Grupo/Electr%C3%B3nica%20Industrial";

        probarOntologia(queries, fileOnt);
   }

    public static void probarPPOEnServidor()
    {
        String[]	queries		= new String[]
        {
            "file:examples/cqPizza.sparql"
        };

        String	fileOnt	= "http://localhost/ontology/DrugProduct";

        probarOntologia(queries, fileOnt);
   }

    public static void probarOntologia(String[]	queries, String	fileOnt)
    {
		OntModel m = ModelFactory.createOntologyModel( PelletReasonerFactory.THE_SPEC );

		// Then read the data from the fileOnt into the ontology model
		m.read( fileOnt );

        for ( int i = 0; i < queries.length ; i++ )
        {
            Utils.consultaSPARQL(m, queries[i]);
        }
        //Consulta.consultaPropiedad(m, nsOnt + "a", nsOnt + "isPartOf");
    }
}

class Main
{
    public static void main(String[] args)
    {
        //Prueba.probarPPO();
        // Prueba.probarPizzaOntology();
        //Prueba.probarMcCarthy();
        //Prueba.probarPPOEnServidor();
        //new AccesoBD();
        createOntology createOntology1 = new createOntology("piaDB", "root", "root");
        createOntology createOntology2 = new createOntology("piaDbaux", "root", "root");



    }
}
