package com.poslovna.fakturisanje.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class StavkaXML {
	
	@XmlAttribute(name = "naziv", required = true)
    protected String naziv;
	
	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String value) {
		this.naziv = value;
	}

}
