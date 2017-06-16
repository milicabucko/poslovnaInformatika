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
    "izdavaocRacuna", "kupac", "stavke"
})
@XmlRootElement(name = "fakturaXML")
public class FakturaXML {
	
	
	@XmlElement(required = true)
	protected FakturaXML.Stavke stavke;
	
    @XmlAttribute(name = "naziv", required = true)
    protected String naziv;
    
    @XmlAttribute(name = "brojDokumenta", required = true)
	protected Integer brojDokumenta;
    
	@XmlAttribute(name = "statusDokumenta", required = true)
	private String statusDokumenta;
	
	@XmlAttribute(name = "datumDokumenta", required = true)
	private String datumDokumenta;
	
	@XmlAttribute(name = "datumValute", required = true)
	private String datumValute;
	
	@XmlAttribute(name = "datumKnjizenja", required = true)
	private String datumKnjizenja;
	
	@XmlAttribute(name = "ukupnoZaPlacanje", required = true)
	private Float ukupnoZaPlacanje;
	
	@XmlElement(required = true)
	private CompanyXML izdavaocRacuna;
	
	@XmlElement(required = true)
	private CompanyXML kupac;
	

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public Integer getBrojDokumenta() {
		return brojDokumenta;
	}

	public void setBrojDokumenta(Integer brojDokumenta) {
		this.brojDokumenta = brojDokumenta;
	}

	public String getStatusDokumenta() {
		return statusDokumenta;
	}

	public void setStatusDokumenta(String statusDokumenta) {
		this.statusDokumenta = statusDokumenta;
	}

	public String getDatumDokumenta() {
		return datumDokumenta;
	}

	public void setDatumDokumenta(String datumDokumenta) {
		this.datumDokumenta = datumDokumenta;
	}

	public String getDatumValute() {
		return datumValute;
	}

	public void setDatumValute(String datumValute) {
		this.datumValute = datumValute;
	}

	public String getDatumKnjizenja() {
		return datumKnjizenja;
	}

	public void setDatumKnjizenja(String datumKnjizenja) {
		this.datumKnjizenja = datumKnjizenja;
	}

	public Float getUkupnoZaPlacanje() {
		return ukupnoZaPlacanje;
	}

	public void setUkupnoZaPlacanje(Float ukupnoZaPlacanje) {
		this.ukupnoZaPlacanje = ukupnoZaPlacanje;
	}


	public CompanyXML getIzdavaocRacuna() {
		return izdavaocRacuna;
	}

	public void setIzdavaocRacuna(CompanyXML izdavaocRacuna) {
		this.izdavaocRacuna = izdavaocRacuna;
	}

	public CompanyXML getKupac() {
		return kupac;
	}

	public void setKupac(CompanyXML kupac) {
		this.kupac = kupac;
	}
	

	public FakturaXML.Stavke getStavke() {
		return stavke;
	}

	public void setStavke(FakturaXML.Stavke stavke) {
		this.stavke = stavke;
	}
	
	
	
	
	
	@XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "stavka"
    })
	public static class Stavke {

		@XmlElement(required = true)
	    protected List<StavkaXML> stavka;

	    public List<StavkaXML> getStavka() {
	        if (stavka == null) {
	        	stavka = new ArrayList<StavkaXML>();
	        }
	        return this.stavka;
	    }

    }
	
	

	

}
