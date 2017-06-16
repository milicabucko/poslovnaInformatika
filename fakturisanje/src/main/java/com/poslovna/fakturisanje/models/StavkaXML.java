package com.poslovna.fakturisanje.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "stavka")
@XmlType(name = "", propOrder = {
	    "artikal"
	})
public class StavkaXML {
	

	@XmlAttribute(name = "kolicina", required = true)
	protected Integer kolicina;
	
	@XmlAttribute(name = "cena", required = true)
	protected Float cena;

	@XmlElement(required = true)
	protected ArtikalXML artikal;
	
	
	public Integer getKolicina() {
		return kolicina;
	}

	public void setKolicina(Integer kolicina) {
		this.kolicina = kolicina;
	}

	public Float getCena() {
		return cena;
	}

	public void setCena(Float cena) {
		this.cena = cena;
	}

	public ArtikalXML getArtikal() {
		return artikal;
	}

	public void setArtikal(ArtikalXML artikal) {
		this.artikal = artikal;
	}


}
