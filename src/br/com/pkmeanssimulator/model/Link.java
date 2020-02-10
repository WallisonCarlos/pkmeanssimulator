package br.com.pkmeanssimulator.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@XmlRootElement(name = "link")
public class Link extends Element{

	@XmlAttribute(name = "id")
	public int id;
	@XmlAttribute(name = "from")
	public int from;
	@XmlAttribute(name = "to")
	public int to;
	@XmlAttribute(name = "length")
	public double length;
	@XmlAttribute(name = "freespeed")
	public double freespeed;
	@XmlAttribute(name = "capacity")
	public double capacity;
	@XmlAttribute(name = "permlanes")
	public float permlanes;
	@XmlAttribute(name = "oneway")
	public int oneway;
	@XmlAttribute(name = "modes")
	public String modes;
	@XmlAttribute(name = "originid")
	public int originid;
	@XmlAttribute(name = "type")
	public String type;
	
	public Link() {
	
	}

	@Override
	public Node getNode(Map map) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE).toString();
	}
	
}
