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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "grupaartikala")
public class GrupaArtikala implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "nazivgrupe")
	private String nazivGrupe;
	
	@OneToMany(mappedBy = "grupaArtikala", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Collection<Artikal> artikli;
	
	@ManyToOne
	@JsonBackReference
	private Company company;
	
	@ManyToOne
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
