package br.com.ppcsimulator.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@XmlRootElement(name = "link")
public class LinkMetro extends Element{
	
	@XmlAttribute(name = "nameOrigin")
	public String nameOrigin;
	@XmlAttribute(name = "nameDestination")
	public String nameDestination;
	@XmlAttribute(name = "idOrigin")
	public int idOrigin;
	@XmlAttribute(name = "idDestination")
	public int idDestination;

	@Override
	public Node getNode(Map map) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.JSON_STYLE).toString();
	}

}
