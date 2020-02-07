package br.com.pkmeanssimulator.model;

public class Node {

	private int id;
	private double x;
	private double y;
	
	public Node() {
	
	}
	
	public Node(int id, double x, double y) {
		this.id = id;
		this.x = x;
		this.y = y;
	}
	
	public Node(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	public double distance(Node node) {
		return Math.sqrt(Math.pow((node.getY() - this.getY()), 2) + Math.pow((node.getX() - this.getX()), 2));

	}
	
	
	
	
}
