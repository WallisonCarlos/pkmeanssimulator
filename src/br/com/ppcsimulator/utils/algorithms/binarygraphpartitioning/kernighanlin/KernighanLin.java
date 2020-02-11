package br.com.ppcsimulator.utils.algorithms.binarygraphpartitioning.kernighanlin;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import br.com.ppcsimulator.model.Link;
import br.com.ppcsimulator.model.Node;
import br.com.ppcsimulator.utils.algorithms.binarygraphpartitioning.Graph;

public class KernighanLin {
	
	private Graph graph;
	private int partitionSize;
	private Graph A;
	private Graph B;
	private Graph unswappedA;
	private Graph unswappedB;
	
	public KernighanLin() {
		
	}
	
	private KernighanLin(Graph graph) throws CloneNotSupportedException {
		setGraph(graph);
		this.partitionSize = graph.getVertices().size() / 2;
	    
//	    if (graph.getVertices().size() != partitionSize * 2) {
//	      throw new RuntimeException("Size of vertices must be even");
//	    }
	    
	    A = new Graph(graph.getMap()).clear();
	    B = new Graph(graph.getMap()).clear();
	    int i = 0;
	    for (Node vertex : graph.getVertices()) {
	      (++i > partitionSize ? B : A).addVertex(vertex);
	    }
	    
	    unswappedA = (Graph) A.clone();
	    unswappedB = (Graph) B.clone();
	    
	    System.out.println(A.getVertices().size()+" "+B.getVertices().size());
	    System.out.println("Do all swaps...");
	    doAllSwaps();
	    A.setEdges(graph.getEdgesFrom(A.getVertices()));
	    B.setEdges(graph.getEdgesFrom(B.getVertices()));
	}
	
	public Graph getGraph() {
		return graph;
	}

	public void setGraph(Graph graph) {
		this.graph = graph;
	}

	public int getPartitionSize() {
		return partitionSize;
	}

	public void setPartitionSize(int partitionSize) {
		this.partitionSize = partitionSize;
	}

	public Graph getA() {
		return A;
	}

	public void setA(Graph a) {
		A = a;
	}

	public Graph getB() {
		return B;
	}

	public void setB(Graph b) {
		B = b;
	}

	public Graph getUnswappedA() {
		return unswappedA;
	}

	public void setUnswappedA(Graph unswappedA) {
		this.unswappedA = unswappedA;
	}

	public Graph getUnswappedB() {
		return unswappedB;
	}

	public void setUnswappedB(Graph unswappedB) {
		this.unswappedB = unswappedB;
	}

	/** Performs KernighanLin with only the given vertices 
	 * @throws CloneNotSupportedException **/
	public static KernighanLin processWithVertices(Graph graph, Set<Node> vertices) throws CloneNotSupportedException {
		Graph newGraph = new Graph(graph.getMap());
	    
	    for (Node vertex : vertices) { 
	    	newGraph.addVertex(vertex);
	    }
	    
	    for (Link edge : graph.getEdges()) {
	    	Pair<Node> endPoints = graph.getEndPoints(edge);
	      	if (vertices.contains(endPoints.getFirst()) && 
	    		  vertices.contains(endPoints.getSecond())) {
	      		newGraph.addEdge(edge);
	        }
	    }
	    return process(newGraph);
	}
	
	public static KernighanLin process(Graph graph) throws CloneNotSupportedException {
	    return new KernighanLin(graph);
	}
	
	private void doAllSwaps() {
		LinkedList<Pair<Node>> swaps = new LinkedList<Pair<Node>>();
	    double minCost = Double.POSITIVE_INFINITY;
	    int minId = -1;
	    
	    for (int i = 0; i < partitionSize; i++) {
	    	double cost = doSingleSwap(swaps);
	    	if (cost < minCost) {
	    		minCost = cost; minId = i; 
	    	}
	    }
	    System.out.println("Unwind swaps...");
	    // Unwind swaps
	    while (swaps.size()-1 > minId) {
	    	Pair<Node> pair = swaps.pop();
	    	// unswap
	    	swapVertices(A, pair.getSecond(), B, pair.getFirst());
    	}
	    System.out.println("End Unwind swaps...");
  	}
	
	
	/** Chooses the least cost swap and performs it **/
	private double doSingleSwap(Deque<Pair<Node>> swaps) {
	    
		Pair<Node> maxPair = null;
	    double maxGain = Double.NEGATIVE_INFINITY;
	    
	    for (Node vertexA : unswappedA.getVertices()) {
	    	for (Node vertexB : unswappedB.getVertices()) {
	        
	        Link edge = graph.findEdge(vertexA, vertexB);
	        double edgeCost = (edge != null) ? edge.weight : 0;
	        // Calculate the gain in cost if these vertices were swapped
	        // subtract 2*edge_cost because this edge will still be an external edge
	        // after swapping
	        double gain = getVertexCost(vertexA) + getVertexCost(vertexB) - 2 * edgeCost;
	        
	        if (gain > maxGain) {
	          maxPair = new Pair<Node>(vertexA, vertexB);
	          maxGain = gain;
	        }
	        
	      }
	    }
	    
	    swapVertices(A, maxPair.getFirst(), B, maxPair.getSecond());
	    swaps.push(maxPair);
	    unswappedA.getVertices().remove(maxPair.getFirst());
	    unswappedB.getVertices().remove(maxPair.getSecond());
	    
	    return getCutCost();
	  }
	
	/** Returns the difference of external cost and internal cost of this vertex.
	   *  When moving a vertex from within group A, all internal edges become external 
	   *  edges and vice versa. **/
	private double getVertexCost(Node vertex) {
	    
	    double cost = 0;
	    boolean vertex1isInA = A.getVertices().contains(vertex);

	    for (Node vertex2 : graph.getNeighbors(vertex)) {
	    	boolean vertex2isInA = A.getVertices().contains(vertex2);
	    	Link edge = graph.findEdge(vertex, vertex2);
	      
	    	if (vertex1isInA != vertex2isInA) { // external
	    		cost += edge.weight;
	    	} else {
	    		cost -= edge.weight;
	    	}
	    }
	    return cost;
	}
	  
	  /** Returns the sum of the costs of all edges between A and B **/
	  public double getCutCost() {
	    double cost = 0;

	    for (Link edge : graph.getEdges()) {
	      Pair<Node> endPoints = graph.getEndPoints(edge);
	      
	      boolean firstInA = A.getVertices().contains(endPoints.getFirst());
	      boolean secondInA= A.getVertices().contains(endPoints.getSecond());
	      
	      if (firstInA != secondInA) // external
	        cost += edge.weight;
	    }
	    return cost;
	  }

	  /** Swaps va and vb in groups a and b **/
	  private static void swapVertices(Graph a, Node vertexA, Graph b, Node vertexB) {
		  if (!a.getVertices().contains(vertexA) || a.getVertices().contains(vertexB) ||
				  !b.getVertices().contains(vertexB) || b.getVertices().contains(vertexA)) {
			  throw new RuntimeException("Invalid swap");
		  }
		  a.getVertices().remove(vertexA); a.getVertices().add(vertexB);
		  b.getVertices().remove(vertexB); b.getVertices().add(vertexA);
	  }
	
	  public class VertexGroup extends HashSet<Node> {  

		private static final long serialVersionUID = 1L;
		
		public VertexGroup(HashSet<Node> clone) { super(clone); }
		  public VertexGroup() { }
	  }

	
	
}
