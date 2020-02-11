package br.com.ppcsimulator.utils.algorithms.binarygraphpartitioning;

import java.util.Random;

import br.com.ppcsimulator.utils.algorithms.binarygraphpartitioning.kernighanlin.KernighanLin;

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

}
