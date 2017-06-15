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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "grupaartikala")

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "artikli", "company", "vrstaPDVa"})
@XmlRootElement(name = "grupaartikala")
public class GrupaArtikala implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	@XmlAttribute(name = "id", required = true)
	private Integer id;
	
	@Column(name = "nazivgrupe")
	@XmlAttribute(name = "nazivgrupe", required = true)
	private String nazivGrupe;
	
	@OneToMany(mappedBy = "grupaArtikala", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	//@XmlTransient
	//@XmlElement(required = false)
	private Collection<Artikal> artikli;
	
	@ManyToOne
	@JsonBackReference
	@XmlElement(required = false)
	private Company company;
	
	@ManyToOne
	@XmlElement(required = false)
	private VrstaPDVa vrstaPDVa;
	
	public GrupaArtikala(){
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNazivGrupe() {
		return nazivGrupe;
	}

	public void setNazivGrupe(String nazivGrupe) {
		this.nazivGrupe = nazivGrupe;
	}

	public Collection<Artikal> getArtikli() {
		return artikli;
	}

	public void setArtikli(Collection<Artikal> artikli) {
		this.artikli = artikli;
	}

	public Company getCompany() {
		return company;
	}

	@JsonIgnore
	public void setCompany(Company company) {
		this.company = company;
	}

	public VrstaPDVa getVrstaPDVa() {
		return vrstaPDVa;
	}
	
	@JsonIgnore
	public void setVrstaPDVa(VrstaPDVa vrstaPDVa) {
		this.vrstaPDVa = vrstaPDVa;
	}
	
	

}
