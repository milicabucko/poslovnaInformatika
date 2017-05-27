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
	
	@ManyToOne
	private Faktura dokument;
	
	
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

	public Faktura getDokument() {
		return dokument;
	}
	
	@JsonIgnore
	public void setDokument(Faktura dokument) {
		this.dokument = dokument;
	}
	
	

}
