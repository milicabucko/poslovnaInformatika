package com.poslovna.fakturisanje.models;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlInlineBinaryData;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "stavkacenovnika")

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "artikal", "cenovnik"})
@XmlRootElement(name = "stavkacenovnika")
public class StavkaCenovnika implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	@XmlAttribute(name = "id", required = true)
	private Integer id;
	
	@Column(name = "cena")
	@XmlAttribute(name = "cena", required = true)
	private Float cena;
	
	@ManyToOne
	@XmlElement(required = false)
	private Artikal artikal;	
	
	@ManyToOne
	@XmlElement(required = false)
	private Cenovnik cenovnik;
	
	public StavkaCenovnika() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Float getCena() {
		return cena;
	}

	public void setCena(Float cena) {
		this.cena = cena;
	}
	
	
	public Artikal getArtikal() {
		return artikal;
	}
	
	public void setArtikal(Artikal artikal) {
		this.artikal = artikal;
	}

	public Cenovnik getCenovnik() {
		return cenovnik;
	}

	@JsonIgnore
	public void setCenovnik(Cenovnik cenovnik) {
		this.cenovnik = cenovnik;
	}
	
	
	

}
