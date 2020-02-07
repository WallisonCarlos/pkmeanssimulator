package br.com.pkmeanssimulator.utils.algorithms.kmeans;


import java.util.ArrayList;
import java.util.List;

import br.com.pkmeanssimulator.model.Data;
import br.com.pkmeanssimulator.model.Element;
import br.com.pkmeanssimulator.model.Node;

public class KMeans {
	
	//Number of Clusters. This metric should be related to the number of points
    private int NUM_CLUSTERS = 2;    
    //Number of Points
    private int NUM_POINTS = 15;
    private Data data;
    //Min and Max X and Y
    private static final int MIN_COORDINATE = 0;
    private static final int MAX_COORDINATE = 10;
    
    private List<Cluster> clusters;
    private List<Element> elements;
    
    public KMeans(int clusters, Data data) {
    	this.data = data;
    	NUM_CLUSTERS = clusters;
    	this.clusters = new ArrayList<>();
    	this.elements = new ArrayList<>();
    }
    
	//The process to calculate the K Means, with iterating method.
    public void calculate() {
        boolean finish = false;
        int iteration = 0;
        
        // Add in new data, one at a time, recalculating centroids with each new one. 
        while(!finish) {
        	//Clear cluster state
        	clearClusters();
        	
        	List<Node> lastCentroids = getCentroids();
        	
        	//Assign points to the closer cluster
        	assignCluster();
            
            //Calculate new centroids.
        	calculateCentroids();
        	
        	iteration++;
        	
        	List<Node> currentCentroids = getCentroids();
        	
        	//Calculates total distance between new and old Centroids
        	double distance = 0;
        	for(int i = 0; i < lastCentroids.size(); i++) {
        		distance += lastCentroids.get(i).distance(currentCentroids.get(i));
        	}
        	System.out.println("#################");
        	System.out.println("Iteration: " + iteration);
        	System.out.println("Centroid distances: " + distance);
        	        	
        	if(distance == 0) {
        		finish = true;
        	}
        }
    }
    
    private void clearClusters() {
    	for(Cluster cluster : clusters) {
    		cluster.clear();
    	}
    }
    
    private List<Node> getCentroids() {
    	List<Node> centroids = new ArrayList<>(NUM_CLUSTERS);
    	for(Cluster cluster : clusters) {
    		Node aux = cluster.getCentroid();
    		Node node = new Node(aux.getId(), aux.getX(),aux.getY());
    		centroids.add(node);
    	}
    	return centroids;
    }
    
    private void assignCluster() {
        double max = Double.MAX_VALUE;
        double min = max; 
        int cluster = 0;                 
        double distance = 0.0; 
        
        for(Element element : elements) {
        	Node node = element.getNode(data.getMap());
        	min = max;
            for(int i = 0; i < NUM_CLUSTERS; i++) {
            	Cluster c = clusters.get(i);
                distance = node.distance(c.getCentroid());
                if(distance < min){
                    min = distance;
                    cluster = i;
                }
            }
            clusters.get(cluster).getElements().add(element);
        }
    }
    
    private void calculateCentroids() {
        for(Cluster cluster : clusters) {
            double sumX = 0;
            double sumY = 0;
            List<Element> elements = cluster.getElements();
            int nElements = elements.size();
            
            for(Element element : elements) {
            	Node node = element.getNode(data.getMap());
            	sumX += node.getX();
                sumY += node.getY();
            }
            
            Node centroid = cluster.getCentroid();
            if (nElements > 0) {
            	double newX = sumX / nElements;
            	double newY = sumY / nElements;
            	centroid = new Node(newX, newY);
            }
        }
    }

}
