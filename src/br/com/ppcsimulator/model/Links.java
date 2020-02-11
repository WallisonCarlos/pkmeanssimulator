package br.com.ppcsimulator.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@XmlRootElement(name = "links")
public class Links {

	@XmlAttribute(name = "capperiod")
	public String capPeriod;
	@XmlAttribute(name = "effectivecellsize")
	public float effectiveCellSize;
	@XmlAttribute(name = "effectivelanewidth")
	public float effectiveLaneWidth;
	@XmlElement(name = "link")
	public List<Link> links = new ArrayList<>();
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE).toString();
	}
}
