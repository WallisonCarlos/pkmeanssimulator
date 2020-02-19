package br.com.ppcsimulator.utils.algorithms.kmeans;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.com.ppcsimulator.model.Data;
import br.com.ppcsimulator.model.Element;
import br.com.ppcsimulator.model.Node;

public class KMeans {
	
	//Number of Clusters. This metric should be related to the number of points
    private int NUM_CLUSTERS = 2;    
    private Data data;
    
    private List<Cluster> clusters;
    private List<Element> elements;
    
    public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	public List<Cluster> getClusters() {
		return clusters;
	}

	public void setClusters(List<Cluster> clusters) {
		this.clusters = clusters;
	}

	public List<Element> getElements() {
		return elements;
	}

	public void setElements(List<Element> elements) {
		this.elements = elements;
	}
	
	public KMeans(int clusters, Data data) {
    	this.data = data;
    	NUM_CLUSTERS = clusters;
    	this.elements = data.getElements();
    	this.clusters = createClusters();	
    }
    
    private List<Cluster> createClusters() {
    	List<Cluster> clusters = new ArrayList<>();
    	Random rand = new Random();
    	for (int i = 0;i < NUM_CLUSTERS;i++) {
    		clusters.add(new Cluster(i));
    		Node node = elements.get(Math.abs((int) (rand.nextInt() % elements.size()))).getNode(data.getMap());
    		clusters.get(i).setCentroid(new Node(node.id, node.x, node.y));
    	}
    	return clusters;
    }
    public void showClusters() {
    	for (Cluster cluster : clusters) {
    		System.out.println(cluster);
    	}
    }
    
	//The process to calculate the K Means, with iterating method.
    public void calculate() {
        boolean finish = false;        
        // Add in new data, one at a time, recalculating centroids with each new one. 
        int iteration = 0;
        while(!finish) {
        	//Clear cluster state
        	clearClusters();
        	
        	List<Node> lastCentroids = getCentroids();
        	
        	//Assign points to the closer cluster
        	assignCluster();
            
            //Calculate new centroids.
        	calculateCentroids();
        	
        	List<Node> currentCentroids = getCentroids();
        	
        	//Calculates total distance between new and old Centroids
        	double distance = 0;
        	for(int i = 0; i < lastCentroids.size(); i++) {
        		distance += lastCentroids.get(i).distance(currentCentroids.get(i));
        	}
        	System.out.println("Iteration "+iteration+": ");
        	        	
        	if(distance <= 0.0001) {
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
    		Node node = new Node(aux.id, aux.x,aux.y);
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
            	sumX += node.x;
                sumY += node.y;
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
