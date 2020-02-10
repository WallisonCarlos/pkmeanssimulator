package br.com.pkmeanssimulator.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@XmlRootElement(name = "network")
public class Map extends Element{
	
	@XmlElement(name = "nodes")
	public Nodes nodes;
	@XmlElement(name = "links")
	public Links links;

	public Node getNode(Integer id) {
		for (Node node : nodes.nodes) {
			if (node.id == id.intValue()) {
				return node;	
			}
		}
		return null;
	}
	
	public Link getLink(Integer id) {
		for (Link link : links.links) {
			if (link.id == id.intValue()) {
				return link;	
			}
		}
		return null;
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
