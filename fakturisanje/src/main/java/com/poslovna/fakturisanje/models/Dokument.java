package com.poslovna.fakturisanje.models;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "dokument")

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "stavkeDokumenta", "izdavaocRacuna", "kupac", "poslovnaGodina"})
@XmlRootElement(name = "faktura")
public class Dokument implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	@XmlAttribute(name = "id", required = true)
	private Integer id;
	
	@Column(name = "brojDokumenta")
	@XmlAttribute(name = "brojDokumenta", required = true)
	private Integer brojDokumenta;
	
	@Column(name = "statusDokumenta")
	@XmlAttribute(name = "statusDokumenta", required = true)
	private String statusDokumenta;
	
	@Column(name = "datumDokumenta")
	@XmlAttribute(name = "datumDokumenta", required = true)
	private String datumDokumenta;
	
	@Column(name = "vrstaDokumenta")
	@XmlAttribute(name = "vrstaDokumenta", required = true)
	private String vrstaDokumenta;
	
	@Column(name = "datumValute")
	@XmlAttribute(name = "datumValute", required = true)
	private String datumValute;
	
	@Column(name = "datumKnjizenja")
	@XmlAttribute(name = "datumKnjizenja", required = true)
	private String datumKnjizenja;
	
	@Column(name = "ukupnoZaPlacanje")
	@XmlAttribute(name = "ukupnoZaPlacanje", required = true)
	private Float ukupnoZaPlacanje;
	
	@ManyToOne
	@XmlElement(required = true)
	private Company izdavaocRacuna;
	
	@ManyToOne
	@XmlElement(required = true)
	private Company kupac;
	
	@ManyToOne
	@XmlElement(required = true)
	private PoslovnaGodina poslovnaGodina;
	
	@OneToMany(mappedBy = "dokument", cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	@XmlElement(required = true)
	private Collection<StavkaDokumenta> stavkeDokumenta;
	
	public Dokument() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBrojDokumenta() {
		return brojDokumenta;
	}

	public void setBrojDokumenta(Integer brojDokumenta) {
		this.brojDokumenta = brojDokumenta;
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

	public String getStatusDokumenta() {
		return statusDokumenta;
	}

	public void setStatusDokumenta(String statusDokumenta) {
		this.statusDokumenta = statusDokumenta;
	}

	public Company getIzdavaocRacuna() {
		return izdavaocRacuna;
	}

	public void setIzdavaocRacuna(Company izdavaocRacuna) {
		this.izdavaocRacuna = izdavaocRacuna;
	}

	public Collection<StavkaDokumenta> getStavkeDokumenta() {
		return stavkeDokumenta;
	}

	public void setStavkeDokumenta(Collection<StavkaDokumenta> stavkeDokumenta) {
		this.stavkeDokumenta = stavkeDokumenta;
	}

	public String getVrstaDokumenta() {
		return vrstaDokumenta;
	}

	public void setVrstaDokumenta(String vrstaDokumenta) {
		this.vrstaDokumenta = vrstaDokumenta;
	}

	public Company getKupac() {
		return kupac;
	}

	public void setKupac(Company kupac) {
		this.kupac = kupac;
	}

	public PoslovnaGodina getPoslovnaGodina() {
		return poslovnaGodina;
	}

	@JsonIgnore
	public void setPoslovnaGodina(PoslovnaGodina poslovnaGodina) {
		this.poslovnaGodina = poslovnaGodina;
	}

	public Float getUkupnoZaPlacanje() {
		return ukupnoZaPlacanje;
	}

	public void setUkupnoZaPlacanje(Float ukupnoZaPlacanje) {
		this.ukupnoZaPlacanje = ukupnoZaPlacanje;
	}
}
