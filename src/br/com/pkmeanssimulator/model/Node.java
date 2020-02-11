package br.com.pkmeanssimulator.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@XmlRootElement(name = "node")
public class Node extends Element{

	@XmlAttribute(name = "id")
	public int id;
	@XmlAttribute(name = "x")
	public double x;
	@XmlAttribute(name = "y")
	public double y;
	
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
	
	
	public double distance(Node node) {
		return Math.sqrt(Math.pow((node.y - this.y), 2) + Math.pow((node.x - this.x), 2));

	}

	@Override
	public Node getNode(Map map) {
		return this;
	}
	
	@Override
	public boolean equals(Object node) {
		if (node != null) {
			if (node instanceof Node) {
				return id == ((Node) node).id;
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE).toString();
	}
	
	
}
