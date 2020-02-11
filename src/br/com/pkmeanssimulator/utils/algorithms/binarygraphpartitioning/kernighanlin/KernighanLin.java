package br.com.pkmeanssimulator.utils.algorithms.binarygraphpartitioning.kernighanlin;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import br.com.pkmeanssimulator.model.Link;
import br.com.pkmeanssimulator.model.Node;
import br.com.pkmeanssimulator.utils.algorithms.binarygraphpartitioning.Graph;

public class KernighanLin {
	
	private Graph graph;
	private int partitionSize;
	private VertexGroup A;
	private VertexGroup B;
	private VertexGroup unswappedA;
	private VertexGroup unswappedB;
	
	public KernighanLin() {
		
	}
	
	private KernighanLin(Graph graph) {
		setGraph(graph);
		this.partitionSize = graph.getVertices().size() / 2;
	    
	    if (graph.getVertices().size() != partitionSize * 2) {
	      throw new RuntimeException("Size of vertices must be even");
	    }
	    
	    A = new VertexGroup();
	    B = new VertexGroup();
	    int i = 0;
	    for (Node vertex : graph.getVertices()) {
	      (++i > partitionSize ? B : A).add(vertex);
	    }
	    
	    unswappedA = new VertexGroup(A);
	    unswappedB = new VertexGroup(B);
	    
	    System.out.println(A.size()+" "+B.size());
	    
	    doAllSwaps();
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

	public VertexGroup getA() {
		return A;
	}

	public void setA(VertexGroup a) {
		A = a;
	}

	public VertexGroup getB() {
		return B;
	}

	public void setB(VertexGroup b) {
		B = b;
	}

	public VertexGroup getUnswappedA() {
		return unswappedA;
	}

	public void setUnswappedA(VertexGroup unswappedA) {
		this.unswappedA = unswappedA;
	}

	public VertexGroup getUnswappedB() {
		return unswappedB;
	}

	public void setUnswappedB(VertexGroup unswappedB) {
		this.unswappedB = unswappedB;
	}

	/** Performs KernighanLin with only the given vertices **/
	public static KernighanLin processWithVertices(Graph graph, Set<Node> vertices) {
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
	
	public static KernighanLin process(Graph graph) {
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
	    // Unwind swaps
	    while (swaps.size()-1 > minId) {
	    	Pair<Node> pair = swaps.pop();
	    	// unswap
	    	swapVertices(A, pair.getSecond(), B, pair.getFirst());
    	}
  	}
	
	
	/** Chooses the least cost swap and performs it **/
	private double doSingleSwap(Deque<Pair<Node>> swaps) {
	    
		Pair<Node> maxPair = null;
	    double maxGain = Double.NEGATIVE_INFINITY;
	    
	    for (Node vertexA : unswappedA) {
	    	for (Node vertexB : unswappedB) {
	        
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
	    unswappedA.remove(maxPair.getFirst());
	    unswappedB.remove(maxPair.getSecond());
	    
	    return getCutCost();
	  }
	
	/** Returns the difference of external cost and internal cost of this vertex.
	   *  When moving a vertex from within group A, all internal edges become external 
	   *  edges and vice versa. **/
	private double getVertexCost(Node vertex) {
	    
	    double cost = 0;
	    boolean vertex1isInA = A.contains(vertex);

	    for (Node vertex2 : graph.getNeighbors(vertex)) {
	    	boolean vertex2isInA = A.contains(vertex2);
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
	      
	      boolean firstInA = A.contains(endPoints.getFirst());
	      boolean secondInA= A.contains(endPoints.getSecond());
	      
	      if (firstInA != secondInA) // external
	        cost += edge.weight;
	    }
	    return cost;
	  }

	  /** Swaps va and vb in groups a and b **/
	  private static void swapVertices(VertexGroup a, Node vertexA, VertexGroup b, Node vertexB) {
		  if (!a.contains(vertexA) || a.contains(vertexB) ||
				  !b.contains(vertexB) || b.contains(vertexA)) {
			  throw new RuntimeException("Invalid swap");
		  }
		  a.remove(vertexA); a.add(vertexB);
		  b.remove(vertexB); b.add(vertexA);
	  }
	
	  public class VertexGroup extends HashSet<Node> {  

		private static final long serialVersionUID = 1L;
		
		public VertexGroup(HashSet<Node> clone) { super(clone); }
		  public VertexGroup() { }
	  }

	
	
}
