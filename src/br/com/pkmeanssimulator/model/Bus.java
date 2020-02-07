package br.com.pkmeanssimulator.model;

import java.util.ArrayList;
import java.util.List;

public class Bus implements Element{
	
	private String id;
	private Integer inverval;
	private Integer start_time;
	private String stops;
	List<Integer> nodesIds = new ArrayList<>();
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public Integer getInverval() {
		return inverval;
	}
	
	public void setInverval(Integer inverval) {
		this.inverval = inverval;
	}
	
	public Integer getStart_time() {
		return start_time;
	}
	
	public void setStart_time(Integer start_time) {
		this.start_time = start_time;
	}
	
	public String getStops() {
		return stops;
	}
	
	public void setStops(String stops) {
		this.stops = stops;
	}
	
	public List<Integer> getNodesId() {
		if (stops == null) {
			return new ArrayList<>();
		}
		String ids[] = stops.split(",");
		if (nodesIds.isEmpty()) {
			for (int i = 0;i < ids.length;i++) {
				nodesIds.add(Integer.parseInt(ids[i].replace(" ", "")));
			}
		}
		return nodesIds;
	}

	@Override
	public Node getNode(Map map) {
		return (getNodesId().isEmpty()) ? null : map.getNode(getNodesId().get(0));
	}

	

}
