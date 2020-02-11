package br.com.ppcsimulator.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@XmlRootElement(name = "signal")
public class Signal extends Element{
	
	@XmlAttribute(name = "cycle_duration")
	private int cycle_duration;
	@XmlAttribute(name = "offset")
	private int offset;
	@XmlElement(name = "nodes")
	private List<Node> nodes = new ArrayList<>();
	@XmlElement(name = "phases")
	private List<Phase> phases = new ArrayList<>();
	
	public int getCycle_duration() {
		return cycle_duration;
	}
	
	public void setCycle_duration(int cycle_duration) {
		this.cycle_duration = cycle_duration;
	}
	
	public int getOffset() {
		return offset;
	}
	
	public void setOffset(int offset) {
		this.offset = offset;
	}
	
	public List<Node> getNodes() {
		return nodes;
	}
	
	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}
	
	public List<Phase> getPhases() {
		return phases;
	}
	
	public void setPhases(List<Phase> phases) {
		this.phases = phases;
	}

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
