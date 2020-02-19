package br.com.ppcsimulator.utils.algorithms.binarygraphpartitioning;

import java.util.List;
import java.util.Random;
import java.util.Set;

import br.com.ppcsimulator.model.Data;
import br.com.ppcsimulator.model.Element;
import br.com.ppcsimulator.model.Link;
import br.com.ppcsimulator.model.Neighbor;
import br.com.ppcsimulator.utils.algorithms.binarygraphpartitioning.kernighanlin.KernighanLin;
import br.com.ppcsimulator.utils.algorithms.kmeans.Cluster;
import br.com.ppcsimulator.utils.algorithms.kmeans.KMeans;

public class BinaryGraphPartitioning {
	
	private Node root;
	private Graph graph;
	private int numberProcessors;
	
	public BinaryGraphPartitioning() {
		super();
		setNumberProcessors(2);
	}
	
	public BinaryGraphPartitioning(int numberProcessors) {
		this();
		setNumberProcessors(numberProcessors);
	}

	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}
	
	public int getNumberProcessors() {
		return numberProcessors;
	}

	public void setNumberProcessors(int numberProcessors) {
		this.numberProcessors = numberProcessors;
	}

	public Graph getGraph() {
		return graph;
	}

	public void setGraph(Graph graph) {
		this.graph = graph;
	}

	public void partition(Graph graph) throws CloneNotSupportedException {
		partition(root, graph, 0, numberProcessors);
	}
	
	private void partition(Node node, Graph graph, int begin, int end) throws CloneNotSupportedException {
		if (node == null) {
			node = new Node();
			node.setId(new Random().nextInt());
		}
		if (begin + 1 >= end) {
			node.setGraph(graph);
			node.setRank(begin);
			System.out.println("Stop!!");
			return ;
		} else {
			KernighanLin kernighanLin = KernighanLin.process(graph);
			int m = (int) Math.ceil((begin+end)/2);
			System.out.println("Begin: "+begin+" Middle: "+m+" End: "+end);
			node.setLeft(new Node());
			node.setRight(new Node());
			node.getLeft().setCutCost(kernighanLin.getCutCost());
			node.getRight().setCutCost(kernighanLin.getCutCost());
			System.out.println("CutCost: "+kernighanLin.getCutCost());
			System.out.println("Graph A: "+kernighanLin.getA()+" Graph B: "+kernighanLin.getB());
			partition(node.getLeft(), kernighanLin.getA(), begin, m);
			partition(node.getRight(), kernighanLin.getB(), m, end);
		}
		
	}
	
	public void printLeaves() {
		printLeaves(root);
	}
	
	private void printLeaves(Node node) {
		if (node == null) { return; }
		System.out.println(node);
		printLeaves(node.getLeft());
		printLeaves(node.getRight());
	}
	
//	public Graph coarsening(Graph graph) {
//		Graph g = new Graph(graph.getMap()).clear();
//		int i = 1;
//		for (br.com.ppcsimulator.model.Node u : graph.getVertices()) {
//			System.out.println(i+" - "+u);
//			if (!u.matched) {
//				u.matched = true;
//				for (br.com.ppcsimulator.model.Node v : graph.getVertices()) {
//					g.addVertex(u);
//					Link edge = graph.findEdge(u, v);
//					if (edge != null && !v.matched) {
//						v.matched = true;
//						g.addEdge(edge);
//						g.addVertex(v);
//					}
//				}
//			}
//			System.out.println(i+" - "+u);
//			i++;
//		}
//		return g;
//	}
	
	public Graph coarsening(Graph graph) {
		Graph g = new Graph(graph.getMap()).clear();
		for (br.com.ppcsimulator.model.Node u : graph.getVertices()) {
			if (!u.matched) {
				u.matched = true;
				g.addVertex(u);
				for (Neighbor neighbor : graph.getNeighbors2(u)) {
					if (neighbor.link != null && !neighbor.node.matched) {
						neighbor.node.matched = true;
						g.addEdge(neighbor.link);
						g.addVertex(neighbor.node);
					}
				}
			}
		}
		return g;
	}
	
	public Graph coarseningNeighborsTuned(Graph graph) {
		Graph g = new Graph(graph.getMap()).clear();
		Data data = new Data();
		data.setMap(graph.getMap());
		KMeans kMeans = new KMeans((graph.getVertices().size() / 500), data);
		kMeans.calculate();
		int c = 0;
		for (Cluster cluster : kMeans.getClusters()) {
			System.out.println(c+" "+cluster);
			int i = 1;
			for (Element ue : cluster.getElements()) {
				br.com.ppcsimulator.model.Node u = (br.com.ppcsimulator.model.Node) ue;
				System.out.println(i+" - "+u);
				if (!u.matched) {
					u.matched = true;
					g.addVertex(u);
					for (Neighbor neighbor : graph.getNeighbors2(u)) {
						if (neighbor.link != null && !neighbor.node.matched) {
							neighbor.node.matched = true;
							g.addEdge(neighbor.link);
							g.addVertex(neighbor.node);
						}
					}
				}
				System.out.println(i+" - "+u);
				i++;
			}
		}
		return g;
	}
	
	public Graph coarseningKmeans(Graph graph) {
		Graph g = new Graph(graph.getMap()).clear();
		
		Data data = new Data();
		data.setMap(graph.getMap());
		KMeans kMeans = new KMeans((graph.getVertices().size() / 500), data);
		kMeans.calculate();
		int c = 0;
		for (Cluster cluster : kMeans.getClusters()) {
			System.out.println(c+" "+cluster);
			int i = 1;
			for (Element ue : cluster.getElements()) {
				br.com.ppcsimulator.model.Node u = (br.com.ppcsimulator.model.Node) ue;
				if (!u.matched) {
					u.matched = true;
					for (Element ve : cluster.getElements()) {
						br.com.ppcsimulator.model.Node v = (br.com.ppcsimulator.model.Node) ve;
						g.addVertex(u);
						Link edge = graph.findEdge(u, v);
						if (edge != null && !v.matched) {
							v.matched = true;
							g.addEdge(edge);
							g.addVertex(v);
						}
					}
				}
				//System.out.println(i+" - "+u);
				i++;
			}
			c++;
		}
		return g;
	}

}
