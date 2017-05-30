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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "stavkacenovnika")
public class StavkaCenovnika implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "cena")
	private Float cena;
	
	@ManyToOne
	private Artikal artikal;	
	
	@ManyToOne
	private Cenovnik cenovnik;
	
	public StavkaCenovnika() {
	// TODO Auto-generated constructor stub
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
	
	@JsonIgnore
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
