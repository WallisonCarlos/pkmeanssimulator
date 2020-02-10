package br.com.pkmeanssimulator.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@XmlRootElement(name = "phase")
public class Phase {

	@XmlAttribute(name = "origin")
	private int origin;
	@XmlAttribute(name = "greem_duration")
	private int green_duration;
	@XmlAttribute(name = "green_start")
	private int green_start;
	
	public int getOrigin() {
		return origin;
	}
	
	public void setOrigin(int origin) {
		this.origin = origin;
	}
	
	public int getGreen_duration() {
		return green_duration;
	}
	
	public void setGreen_duration(int green_duration) {
		this.green_duration = green_duration;
	}
	
	public int getGreen_start() {
		return green_start;
	}
	
	public void setGreen_start(int green_start) {
		this.green_start = green_start;
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.JSON_STYLE).toString();
	}
	
}
