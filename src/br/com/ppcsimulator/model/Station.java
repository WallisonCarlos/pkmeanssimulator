package br.com.ppcsimulator.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@XmlRootElement(name = "station")
public class Station extends Element{
	
	@XmlAttribute(name = "name")
	public String name;
	@XmlAttribute(name = "idNode")
	public int idNode;

	@Override
	public Node getNode(Map map) {
		System.out.println("station: "+idNode+" - "+name);
		return map.getNode(idNode);
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.JSON_STYLE).toString();
	}

}
