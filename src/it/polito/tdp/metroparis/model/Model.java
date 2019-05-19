package it.polito.tdp.metroparis.model;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

import it.polito.tdp.metroparis.db.MetroDAO;

public class Model {

	private List<Fermata> fermate;
	private List<Connessione> connessioni;
	private Graph<Fermata, DefaultEdge> metro;
	
	public void creaGrafo() {
		MetroDAO dao = new MetroDAO();
		
		fermate = dao.getAllFermate();
		metro = new SimpleDirectedGraph<>(DefaultEdge.class);
		Graphs.addAllVertices(metro, fermate);
		connessioni = dao.getAllConnessioni();
		for(Connessione connessione: connessioni) {
			metro.addEdge(connessione.getStazP(), connessione.getStazA());
		}
		
	}

	public List<Fermata> getFermate() {
		return fermate;
	}

	public Graph<Fermata, DefaultEdge> getMetro() {
		return metro;
	}
}
