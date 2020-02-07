package br.com.pkmeanssimulator.model;

import java.util.ArrayList;
import java.util.List;

public class Map {
	
	private List<Node> nodes = new ArrayList<>();
	private List<Link> links = new ArrayList<>();

	public List<Node> getNodes() {
		return nodes;
	}

	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}
	
	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}

	public Node getNode(Integer id) {
		for (Node node : nodes) {
			if (node.getId() == id.intValue()) {
				return node;	
			}
		}
		return null;
	}

}
