package com.poslovna.fakturisanje.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "stavkaDokumenta")
public class StavkaDokumenta implements Serializable{


	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@ManyToOne
	private Artikal artikal;
	
	@Column(name = "kolicina")
	private Integer kolicina;
	
	@Column(name = "cena")
	private Float cena;
	
	@Column(name = "rabat")
	private Float rabat;
	
	@Column(name = "procenatPDVa")
	private int procenatPDVa;
	
	@ManyToOne
	private Dokument dokument;
	
	
	public StavkaDokumenta() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Artikal getArtikal() {
		return artikal;
	}

	public void setArtikal(Artikal artikal) {
		this.artikal = artikal;
	}

	public Dokument getDokument() {
		return dokument;
	}
	
	@JsonIgnore
	public void setDokument(Dokument dokument) {
		this.dokument = dokument;
	}

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

	public Float getRabat() {
		return rabat;
	}

	public void setRabat(Float rabat) {
		this.rabat = rabat;
	}

	public int getProcenatPDVa() {
		return procenatPDVa;
	}

	public void setProcenatPDVa(int procenatPDVa) {
		this.procenatPDVa = procenatPDVa;
	}
}
