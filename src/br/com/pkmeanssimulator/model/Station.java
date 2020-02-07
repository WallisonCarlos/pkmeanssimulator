package br.com.pkmeanssimulator.model;

public class Station implements Element{
	
	private String name;
	private Integer idNode;
	
	public Integer getIdNode() {
		return idNode;
	}
	
	public void setIdNode(Integer idNode) {
		this.idNode = idNode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Node getNode(Map map) {
		return map.getNode(idNode);
	}
	
	

}
