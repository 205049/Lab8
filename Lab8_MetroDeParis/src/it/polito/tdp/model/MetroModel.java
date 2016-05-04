package it.polito.tdp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.jgrapht.GraphPath;
import org.jgrapht.Graphs;
import org.jgrapht.WeightedGraph;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.AbstractBaseGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

import it.polito.tdp.db.MetroDAO;

public class MetroModel {

	private WeightedGraph<Fermata, DefaultWeightedEdge> grafo;

	public List<Fermata> elencoFermate() {
		MetroDAO dao = new MetroDAO();
		return dao.elencoFermate();
	}

	public List<String> elencoNomeFermate() {
		MetroDAO dao = new MetroDAO();

		List<Fermata> ff = new ArrayList<Fermata>(dao.elencoFermate());
		List<String> fnome = new ArrayList<String>();

		for (Fermata t : ff)
			fnome.add(t.getNome());

		return fnome;
	}

	public List<Connessione> elencoConnessioni() {
		MetroDAO dao = new MetroDAO();
		return dao.elencoConnesioni();
	}
	
	public List<Linea> elencoLinee() {
		MetroDAO dao = new MetroDAO();
		return dao.elencoLinee();
	}

	public void generaGrafo() {
		grafo = new SimpleWeightedGraph<Fermata, DefaultWeightedEdge>(DefaultWeightedEdge.class);

		for (Fermata t : this.elencoFermate())
			grafo.addVertex(t);

		for (Connessione c1 : this.elencoConnessioni()) {
			Fermata t1 = this.codiceFermata(c1.getCodiceFermataPartenza());
			Fermata t2 = this.codiceFermata(c1.getCodiceFermataArrivo());
			
			double weight = 0;
			double sp = LatLngTool.distance(new LatLng(t1.getX(), t1.getY()), new LatLng(t2.getX(), t2.getY()), LengthUnit.KILOMETER);
			Linea l = this.codiceLinea(c1.getCodiceLinea());
			weight = sp/l.getVelocita();
			
			Graphs.addEdgeWithVertices(grafo, t1, t2, weight);

			//System.out.println(t1.getCodice() + " - " + t2.getCodice());
		}
		
	}

	public Fermata codiceFermata(int codice) {
		for (Fermata tt : this.elencoFermate()) {
			if (tt.getCodice() == codice)
				return tt;
		}
		return null;
	}
	
	public Linea codiceLinea(int codice) {
		for (Linea tt : this.elencoLinee()) {
			if (tt.getCodice() == codice)
				return tt;
		}
		return null;
	}
	
	public List<Fermata> getCammino(String s1, String s2){
		
		Fermata f1 = this.nomeFermata(s1); 
		Fermata f2 = this.nomeFermata(s2);
		
		if(f1 == null || f2 == null)
			return null;
		
		DijkstraShortestPath<Fermata, DefaultWeightedEdge> dijkstra 
			= new DijkstraShortestPath<Fermata, DefaultWeightedEdge>(grafo, f1, f2);
		
		GraphPath<Fermata, DefaultWeightedEdge> path = dijkstra.getPath();
		
		if(path == null)
			return null;
		
		double tempo = path.getWeight();
		System.out.println(tempo*60);
		
		System.out.println(Graphs.getPathVertexList(path));
		return Graphs.getPathVertexList(path);
		
	}
	
	public Fermata nomeFermata(String nome) {
		for (Fermata tt : this.elencoFermate()) {
			if (tt.getNome().compareTo(nome) == 0)
				return tt;
		}
		return null;
	}

}
