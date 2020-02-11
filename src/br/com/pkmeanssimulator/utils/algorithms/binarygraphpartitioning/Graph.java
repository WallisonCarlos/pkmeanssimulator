package br.com.pkmeanssimulator.utils.algorithms.binarygraphpartitioning;

import java.util.HashSet;
import java.util.Set;

import br.com.pkmeanssimulator.model.Link;
import br.com.pkmeanssimulator.model.Map;
import br.com.pkmeanssimulator.model.Node;
import br.com.pkmeanssimulator.utils.algorithms.binarygraphpartitioning.kernighanlin.Pair;

public class Graph {
	
	private Map map;
	private Set<Node> vertices; 
	private Set<Link> edges;
	
	public Graph(Map map) {
		setMap(map);
		setVertices(new HashSet<Node>(map.nodes.nodes));
		setEdges(new HashSet<Link>(map.links.links));
	}
	
	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public Set<Node> getVertices() {
		return vertices;
	}
	
	public void setVertices(Set<Node> vertices) {
		this.vertices = vertices;
	}
	
	public Set<Link> getEdges() {
		return edges;
	}
	
	public void setEdges(Set<Link> edges) {
		this.edges = edges;
	}
	
	public boolean addVertex(Node vertex) {
		return vertices.add(vertex);
	}
	
	public boolean addEdge(Link edge) {
		return edges.add(edge);
	}
	
	public Pair<Node> getEndPoints(Link edge) {
		Node[] nodes = edge.getNodes(map);
		return new Pair<Node>(nodes[0], nodes[1]);
	} 
	
	public Link findEdge(Node from, Node to) {
		for (Link edge : edges) {
			if (edge.from == from.id && edge.to == to.id) {
				return edge;
			}
		}
		return null;
	}
	
	public Link findEdge(Node vertex) {
		for (Link edge : edges) {
			if (edge.from == vertex.id || edge.to == vertex.id) {
				return edge;
			}
		}
		return null;
	}
	
	public Set<Node> getNeighbors(Node vertex) {
		Set<Node> neighbors = new HashSet<Node>();
	    for (Link edge : edges) {
	    	if (edge.from == vertex.id) {
	    		neighbors.add(edge.getNodes(map)[1]);
	    		continue;
	    	}
	    	if (edge.to == vertex.id) {
	    		neighbors.add(edge.getNodes(map)[0]);
	    	}
	    }
	    return neighbors;
	  }
	

}
