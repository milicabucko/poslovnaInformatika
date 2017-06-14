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

@Entity
@Table(name = "poslovnaGodina")

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "preduzece", "dokumenti"})
@XmlRootElement(name = "poslovnaGodina")
public class PoslovnaGodina implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	@XmlAttribute(name = "id", required = true)
	private Integer id;
	
	@Column(name = "brojGodine")
	@XmlAttribute(name = "brojGodine", required = true)
	private Integer brojGodine;
	
	@Column(name = "aktivna")
	@XmlAttribute(name = "aktivna", required = true)
	private Boolean aktivna;
	
	@Column(name = "datumPocetka")
	@XmlAttribute(name = "datumPocetka", required = true)
	private String datumPocetka;
	
	@Column(name = "datumZavrsetka")
	@XmlAttribute(name = "datumZavrsetka", required = true)
	private String datumZavrsetka;
	
	@ManyToOne
	@XmlElement(required = false)
	private Company preduzece;
	
	@OneToMany(mappedBy = "poslovnaGodina", cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	@XmlElement(required = false)
	private Collection<Dokument> dokumenti;
	
	public PoslovnaGodina() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBrojGodine() {
		return brojGodine;
	}

	public void setBrojGodine(Integer brojGodine) {
		this.brojGodine = brojGodine;
	}

	public Boolean getAktivna() {
		return aktivna;
	}

	public void setAktivna(Boolean aktivna) {
		this.aktivna = aktivna;
	}

	public Company getPreduzece() {
		return preduzece;
	}

	public void setPreduzece(Company preduzece) {
		this.preduzece = preduzece;
	}

	public String getDatumPocetka() {
		return datumPocetka;
	}

	public void setDatumPocetka(String datumPocetka) {
		this.datumPocetka = datumPocetka;
	}

	public String getDatumZavrsetka() {
		return datumZavrsetka;
	}

	public void setDatumZavrsetka(String datumZavrsetka) {
		this.datumZavrsetka = datumZavrsetka;
	}

	public Collection<Dokument> getDokumenti() {
		return dokumenti;
	}

	public void setDokumenti(Collection<Dokument> dokumenti) {
		this.dokumenti = dokumenti;
	}
}
