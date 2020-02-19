package br.com.ppcsimulator.utils.algorithms.binarygraphpartitioning.kernighanlin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.com.ppcsimulator.model.Link;
import br.com.ppcsimulator.model.Node;
import br.com.ppcsimulator.utils.algorithms.binarygraphpartitioning.Graph;

public class MergeKernighanLin {
	
	private List<Node> internals = new ArrayList<>();
	private List<Node> boundries = new ArrayList<>();
	private Graph graph;
	
	public MergeKernighanLin(Graph graph) {
		this.graph = graph;
	}
	
//	public Graph coarsening(Graph graph) {
//		Graph g = new Graph(graph.getMap()).clear();
//		for (Node u : graph.getVertices()) {
//			if (!u.matched) {
//				u.matched = true;
//				for (Node v : graph.getVertices()) {
//					g.addVertex(u);
//					Link edge = graph.findEdge(u, v);
//					if (edge != null && !v.matched) {
//						v.matched = true;
//						g.addEdge(edge);
//						g.addVertex(v);
//					}
//				}
//			}
//		}
//		return g;
//	}
	
	public void merge(List<Node> nodes, int init, int middle, int end) {
		List<Node> left = new ArrayList<Node>();
		List<Node> right = new ArrayList<Node>();
		copy(nodes, left, right, init, middle, end);
	}
	
	public void mergeKL(List<Node> nodes, int init, int end) {
		if (init < end) {
			int middle = end + (end - init) / 2;
			mergeKL(nodes, init, middle);
			mergeKL(nodes, middle + 1, end);
			merge(nodes, init, middle, end);
		}
	}
	
	private void part(List<Node> nodes, List<Node> internals, List<Node> boundries) {
		
	}
	
	private void copy(List<Node> nodes, List<Node> left, List<Node> right, int init, int middle, int end) {
	    int n1 = middle - init + 1; 
	    int n2 =  end - middle; 
	    
	    for (int i = 0; i < n1; i++) {
	        left.add(nodes.get(init + i)); 
	    }
	    
	    for (int j = 0; j < n2; j++) {
	        right.add(nodes.get(middle + 1 + j));
	    }
	}
}
