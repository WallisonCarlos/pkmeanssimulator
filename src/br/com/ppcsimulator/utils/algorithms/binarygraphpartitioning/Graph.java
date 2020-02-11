package br.com.ppcsimulator.utils.algorithms.binarygraphpartitioning;

import java.util.HashSet;
import java.util.Set;

import br.com.ppcsimulator.model.Link;
import br.com.ppcsimulator.model.Map;
import br.com.ppcsimulator.model.Node;
import br.com.ppcsimulator.utils.algorithms.binarygraphpartitioning.kernighanlin.Pair;

public class Graph implements Cloneable{
	
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
		Node[] nodes = edge.getNodes(vertices);
		return new Pair<Node>(nodes[0], nodes[1]);
	} 
	
	public Link findEdge(Node from, Node to) {
		for (Link edge : edges) {
			if ((edge.from == from.id && edge.to == to.id) 
					|| (edge.to == from.id && edge.from == to.id)) {
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
	    		Node v = edge.getNodes(vertices)[1];
	    		if (v != null) {
	    			neighbors.add(v);
	    		}
	    		continue;
	    	}
	    	if (edge.to == vertex.id) {
	    		Node v = edge.getNodes(vertices)[0];
	    		if (v != null) {
	    			neighbors.add(v);
	    		}
	    		continue;
	    	}
	    }
	    return neighbors;
	}
	
	public Set<Link> getEdgesFrom(Set<Node> vertices) {
		Set<Link> edges = new HashSet<Link>();
		for (Link edge : this.edges) {
			for (Node vertex : vertices) {
				if (edge.from == vertex.id || edge.to == vertex.id) {
					edges.add(edge);
					break;
				}
			}
		}
		return edges;
	}
	
	public Graph clear() {
		vertices.clear();
		edges.clear();
		return this;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException { 
		return super.clone(); 
	} 
	
	@Override
	public String toString() {
		return "{\"numberOfVertices\": "+vertices.size()+", \"numberOfEdges\": "+edges.size()+"}";
	}

}
