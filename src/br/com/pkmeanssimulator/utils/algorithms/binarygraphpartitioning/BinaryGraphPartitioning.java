package br.com.pkmeanssimulator.utils.algorithms.binarygraphpartitioning;

import br.com.pkmeanssimulator.utils.algorithms.binarygraphpartitioning.kernighanlin.KernighanLin;

public class BinaryGraphPartitioning {
	
	private Node root;
	private Graph graph;
	private int numberProcessors;
	
	private BinaryGraphPartitioning() {
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

	public void partition(Graph graph) {
		partition(root, graph, 0, numberProcessors);
	}
	
	private void partition(Node node, Graph graph, int begin, int end) {
		if (node == null) {
			node = new Node();
		}
		if (begin + 1 >= end) {
			node.setGraph(graph);
			node.setRank(begin);
			return ;
		} else {
			KernighanLin kernighanLin = KernighanLin.process(graph);
			int m = (int) Math.ceil((begin+end)/2);
			node.setLeft(new Node());
			node.setRight(new Node());
			partition(node.getLeft(), graph, begin, m);
			partition(node.getRight(), graph, m, end);
		}
		
	}

}
