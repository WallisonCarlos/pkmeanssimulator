package br.com.ppcsimulator.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@XmlRootElement(name = "trip")
public class Trip extends Element{
	
	@XmlAttribute(name = "name")
	public String name;
	@XmlAttribute(name = "origin")
	public int origin;
	@XmlAttribute(name = "destination")
	public int destination;
	@XmlAttribute(name = "link_origin")
	public int link_origin;
	@XmlAttribute(name = "count")
	public int count;
	@XmlAttribute(name = "digital_rails_capable")
	public boolean digital_rails_capable;
	@XmlAttribute(name = "mode")
	public String mode;
	@XmlAttribute(name = "start")
	public int start;
	
	public Trip() {
		
	}

	public Trip(String name, int origin, int destination, int link_origin, int count, int start, String mode, boolean digital_rails_capable) {
		super();
		this.name = name;
		this.start = start;
		this.mode = mode;
		this.origin = origin;
		this.destination = destination;
		this.link_origin = link_origin;
		this.count = count;
		this.digital_rails_capable = digital_rails_capable;
	}



	@Override
	public Node getNode(Map map) {
		return map.getNode(map.getLink(link_origin).from);
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE).toString();
	}

}
