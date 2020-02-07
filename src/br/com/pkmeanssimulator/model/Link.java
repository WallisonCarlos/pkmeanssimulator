package br.com.pkmeanssimulator.model;

public class Link {


	private int id;
	private int from;
	private int to;
	private double length;
	private double freespeed;
	private double capacity;
	private float permlanes;
	private int oneway;
	private String modes;
	private int originid;
	private String type;
	
	public Link() {
	
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFrom() {
		return from;
	}

	public void setFrom(int from) {
		this.from = from;
	}

	public int getTo() {
		return to;
	}

	public void setTo(int to) {
		this.to = to;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public double getFreespeed() {
		return freespeed;
	}

	public void setFreespeed(double freespeed) {
		this.freespeed = freespeed;
	}

	public double getCapacity() {
		return capacity;
	}

	public void setCapacity(double capacity) {
		this.capacity = capacity;
	}

	public float getPermlanes() {
		return permlanes;
	}

	public void setPermlanes(float permlanes) {
		this.permlanes = permlanes;
	}

	public int getOneway() {
		return oneway;
	}

	public void setOneway(int oneway) {
		this.oneway = oneway;
	}

	public String getModes() {
		return modes;
	}

	public void setModes(String modes) {
		this.modes = modes;
	}

	public int getOriginid() {
		return originid;
	}

	public void setOriginid(int originid) {
		this.originid = originid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	
}
