
package javaapplication1;

import java.io.FileWriter;
import java.io.IOException;

import com.hp.hpl.jena.enhanced.EnhGraph;
import com.hp.hpl.jena.enhanced.Personality;
import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.AnonId;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.RDFList;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.impl.RDFListImpl;


public class creacionOntologia {

	public creacionOntologia() {

		OntModel m = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM_RULE_INF);
		String prefijo = "http://www.lsi.us.es/ontologia1#";
		m.setNsPrefix("", prefijo);

		//Clases
		m.createClass(prefijo + "Curso");
		m.createClass(prefijo + "Departamento");
		m.createClass(prefijo + "Profesor");
		m.createClass(prefijo + "Titular");
		m.createClass(prefijo + "Ayudante");
		m.createClass(prefijo + "Colaborador");
		m.createClass(prefijo + "Asignatura");
		m.createClass(prefijo + "Funcionario");
		m.createClass(prefijo + "Organismo_Publico");
		m.createClass(prefijo+"Docente");		
		
		//Subclases
		m.getOntClass(prefijo + "Profesor").addSubClass(m.getOntClass(prefijo + "Titular"));
		m.getOntClass(prefijo + "Profesor").addSubClass(m.getOntClass(prefijo + "Ayudante"));
		m.getOntClass(prefijo + "Profesor").addSubClass(m.getOntClass(prefijo + "Colaborador"));

		//Clases disjuntas
		m.getOntClass(prefijo + "Ayudante").addDisjointWith(m.getOntClass(prefijo + "Titular"));
		m.getOntClass(prefijo + "Colaborador").addDisjointWith(m.getOntClass(prefijo + "Titular"));
		m.getOntClass(prefijo + "Ayudante").addDisjointWith(m.getOntClass(prefijo + "Colaborador"));
		
		//Union de clases
		RDFList l = m.createList();
		RDFNode n = m.getResource(prefijo + "Ayudante");
		l = l.cons(n);
		n = m.getResource(prefijo + "Titular");
		l = l.cons(n);
		n = m.getResource(prefijo + "Colaborador");
		l = l.cons(n);
		m.createUnionClass(prefijo + "Profesor", l);

        //Interseccion de clases
		RDFList l2 = m.createList();
		n = m.getResource(prefijo + "Funcionario");
		l2 = l2.cons(n);
		n = m.getResource(prefijo + "Docente");
		l2 = l2.cons(n);
		m.createIntersectionClass(prefijo + "Docente_Funcionario", l2);
		
        //Complemento
  	    m.createClass(prefijo+"Docente_No_Funcionario").convertToComplementClass(m.getOntClass(prefijo+"Docente_Funcionario"));
		
		//Propiedades con rango objeto
		m.createObjectProperty(prefijo + "esta_adscrita_a");
		m.createObjectProperty(prefijo + "da_clases_en");
		m.createObjectProperty(prefijo + "trabaja_en");
		
		
		//Propiedades con rango valores
		m.createDatatypeProperty(prefijo + "tiene_asignado_el_despacho");
		m.createDatatypeProperty(prefijo + "tomo_posesion");
		

		//Propiedades: Dominios y Rangos
		m.getObjectProperty(prefijo + "esta_adscrita_a").addDomain(m.getOntClass(prefijo + "Asignatura"));
		m.getObjectProperty(prefijo + "esta_adscrita_a").addRange(m.getOntClass(prefijo + "Departamento"));
		m.getObjectProperty(prefijo + "trabaja_en").addDomain(m.getOntClass(prefijo + "Funcionario"));
		m.getObjectProperty(prefijo + "trabaja_en").addRange(m.getOntClass(prefijo + "Organismo_Publico"));
		m.getObjectProperty(prefijo + "da_clases_en").addDomain(m.getOntClass(prefijo + "Profesor"));
		m.getObjectProperty(prefijo + "da_clases_en").addRange(m.getOntClass(prefijo + "Asignatura"));
		m.getDatatypeProperty(prefijo + "tiene_asignado_el_despacho").addDomain(m.getOntClass(prefijo + "Profesor"));
		m.getDatatypeProperty(prefijo + "tiene_asignado_el_despacho").addRange(m.getResource("http://www.w3.org/2001/XMLSchema#normalizedString"));
		m.getDatatypeProperty(prefijo + "tomo_posesion").addDomain(m.getOntClass(prefijo + "Titular"));
		m.getDatatypeProperty(prefijo + "tomo_posesion").addRange(m.getResource("http://www.w3.org/2001/XMLSchema#normalizedString"));

		//Propiedades funcionales
		m.getOntProperty(prefijo + "esta_adscrita_a").convertToFunctionalProperty();
		m.getOntProperty(prefijo + "tiene_asignado_el_despacho").convertToFunctionalProperty();
	
		//Propiedades: Restricciones
	     m.createRestriction(m.getOntProperty(prefijo+"trabaja_en")).convertToAllValuesFromRestriction(m.getOntResource(prefijo+"Departamento")).addSubClass(m.getOntClass(prefijo+"Profesor"));
		 m.createRestriction(m.getObjectProperty(prefijo + "da_clases_en")).convertToMinCardinalityRestriction(1).addSubClass(m.getOntClass(prefijo+"Profesor"));
	     
		//Equivalencias 
    	  m.getOntClass(prefijo+"Docente_Funcionario").addEquivalentClass(m.getOntClass(prefijo+"Titular"));
    	
		//Escritura
		System.out.println("\n\nModelo:\n");

		m.write(System.out, "RDF/XML-ABBREV");
		try {
			FileWriter fs = new FileWriter("ontologia1.owl");
			m.write(fs, "RDF/XML-ABBREV");
		} catch (IOException e) {
		}

	}
}