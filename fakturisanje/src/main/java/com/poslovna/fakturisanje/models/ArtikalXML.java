package com.poslovna.fakturisanje.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class ArtikalXML {

	
	@XmlAttribute(name = "sifra", required = true)
	private String sifra;
	
	@XmlAttribute(name = "naziv", required = true)
	private String naziv;
	
	@XmlAttribute(name = "opis", required = true)
	private String opis;
	
	@XmlAttribute(name = "vrsta", required = true)
	private String vrsta;
	
	@XmlAttribute(name = "jedinicaMere", required = true)
	private String jedinicaMere;

	public String getSifra() {
		return sifra;
	}

	public void setSifra(String sifra) {
		this.sifra = sifra;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public String getVrsta() {
		return vrsta;
	}

	public void setVrsta(String vrsta) {
		this.vrsta = vrsta;
	}

	public String getJedinicaMere() {
		return jedinicaMere;
	}

	public void setJedinicaMere(String jedinicaMere) {
		this.jedinicaMere = jedinicaMere;
	}
	
	
}
