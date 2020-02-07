package br.com.pkmeanssimulator.model;

import java.util.ArrayList;
import java.util.List;

public class Signal {

	private int cycle_duration;
	private int offset;
	private List<Node> nodes = new ArrayList<>();
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
	
	
	
}
