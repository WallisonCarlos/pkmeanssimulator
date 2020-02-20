package br.com.ppcsimulator.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@XmlRootElement(name = "phase")
public class Phase {

	@XmlAttribute(name = "origin")
	public int origin;
	@XmlAttribute(name = "greem_duration")
	public int green_duration;
	@XmlAttribute(name = "green_start")
	public int green_start;
	
	public Phase() {
		
	}
	
	public Phase(int origin, int green_duration, int green_start) {
		super();
		this.origin = origin;
		this.green_duration = green_duration;
		this.green_start = green_start;
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.JSON_STYLE).toString();
	}
	
}
