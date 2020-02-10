package br.com.pkmeanssimulator.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@XmlRootElement(name = "metro")
public class Metro extends Element{
	
	@XmlElement(name = "stations")
	public Stations stations;
	@XmlElement(name = "links")
	public List<LinkMetro> links = new ArrayList<>();
	
	public Metro() {
		setTypeElement(ElementTypeEnum.METRO);
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
