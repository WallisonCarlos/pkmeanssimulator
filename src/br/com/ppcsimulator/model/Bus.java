package br.com.ppcsimulator.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@XmlRootElement(name = "bus")
public class Bus extends Element{
	
	
	private String id;
	private int inverval;
	private int startTime;
	private String stops;
	List<Integer> nodesIds = new ArrayList<>();
	
	@XmlAttribute(name = "id")
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	@XmlAttribute(name = "interval")
	public int getInverval() {
		return inverval;
	}
	
	public void setInverval(int inverval) {
		this.inverval = inverval;
	}

	@XmlAttribute(name = "start_time")
	public int getStartTime() {
		return startTime;
	}
	
	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	@XmlAttribute(name = "stops")
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
		System.out.println("bus");
		return (getNodesId().isEmpty()) ? null : map.getNode(getNodesId().get(0));
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE).toString();
	}

}
