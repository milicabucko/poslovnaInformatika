package com.poslovna.fakturisanje.models;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "stavka"
})
@XmlRootElement(name = "fakturaXML")
public class FakturaXML {
	
	@XmlElement(required = true)
    protected List<StavkaXML> stavka;
	
    @XmlAttribute(name = "naziv", required = true)
    protected String naziv;

    public List<StavkaXML> getStavka() {
        if (stavka == null) {
        	stavka = new ArrayList<StavkaXML>();
        }
        return this.stavka;
    }

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	

}
