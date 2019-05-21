package it.polito.tdp.metroparis.model;

import java.util.HashMap;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

import it.polito.tdp.metroparis.db.ConnessioneDAO;
import it.polito.tdp.metroparis.db.MetroDAO;

/**
 * Con questa soluzione 48 stazioni che sono collegate
 * da due linee diverse vengono contate una sola volta
 * 
 * Per soluzione migliore si doveva usare un multigrafo.
 * @author Tany
 *
 */
public class Model {

	private Graph<Fermata, DefaultEdge> metro;
	private Map<Integer, Fermata> identityMap;
	
	public Model() {
		identityMap = new HashMap<>();
		metro = new SimpleDirectedGraph<>(DefaultEdge.class);
	}
	
	public void creaGrafo() {
		
		MetroDAO dao = new MetroDAO();
		ConnessioneDAO daoC = new ConnessioneDAO();
		dao.getAllFermate(identityMap);
		
		//aggiungi i vertici al grafo
		//ovvero le fermate alla metrò
		Graphs.addAllVertices(metro, identityMap.values());
		
		//aggiungo a metrò le connessioni
		for(StazioniConnesse sc: daoC.listConnesse()) {
			metro.addEdge(identityMap.get(sc.getId_Partenza()), identityMap.get(sc.getId_Arrivo()));
		}
	}

	public Graph<Fermata, DefaultEdge> getMetro() {
		return metro;
	}

	public Map<Integer, Fermata> getIdentityMap() {
		return identityMap;
	}
}
