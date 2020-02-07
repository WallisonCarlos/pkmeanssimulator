package br.com.pkmeanssimulator.model;

public class Trip implements Element{
	
	private int origin;
	private int destination;
	private int link_origin;
	private int count;
	private boolean digital_rails_capable;
	
	public int getOrigin() {
		return origin;
	}
	
	public void setOrigin(int origin) {
		this.origin = origin;
	}
	
	public int getDestination() {
		return destination;
	}
	
	public void setDestination(int destination) {
		this.destination = destination;
	}
	
	public int getLink_origin() {
		return link_origin;
	}
	
	public void setLink_origin(int link_origin) {
		this.link_origin = link_origin;
	}
	
	public int getCount() {
		return count;
	}
	
	public void setCount(int count) {
		this.count = count;
	}
	
	public boolean isDigital_rails_capable() {
		return digital_rails_capable;
	}
	
	public void setDigital_rails_capable(boolean digital_rails_capable) {
		this.digital_rails_capable = digital_rails_capable;
	}

	@Override
	public Node getNode(Map map) {
		return map.getNode(link_origin);
	}
	
	

}
