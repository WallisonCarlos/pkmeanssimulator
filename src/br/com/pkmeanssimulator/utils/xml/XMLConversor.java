package br.com.pkmeanssimulator.utils.xml;

import java.io.File;
import javax.xml.bind.JAXBContext;  
import javax.xml.bind.JAXBException;  
import javax.xml.bind.Unmarshaller;  

public class XMLConversor<T> {
	
	private File file;
	
	public XMLConversor(File file) {
		this.file = file;
	}
	
	public T xmlToObject(Class<T> clazz) {
		JAXBContext jaxbContext;
		try {
		    jaxbContext = JAXBContext.newInstance(clazz);              		 
		    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		    T employee = (T) jaxbUnmarshaller.unmarshal(file);
		    return employee;
		} catch (JAXBException e) {
		    e.printStackTrace();
		    return null;
		}
	}
	
	public String objetctToXml(T o) {
		return "";
	}

}
