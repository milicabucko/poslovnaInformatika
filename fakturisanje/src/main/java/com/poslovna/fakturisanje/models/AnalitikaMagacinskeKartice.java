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
@Table(name = "analitika")
public class AnalitikaMagacinskeKartice implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "vrstaPrometa")
	private String vrstaPrometa;
	
	@Column(name = "smer")
	private String smer;
	
	@ManyToOne
	private StavkaDokumenta stavkaDokumenta;
	
	@ManyToOne
	private MagacinskaKartica magacinskaKartica;
	
	public AnalitikaMagacinskeKartice() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getVrstaPrometa() {
		return vrstaPrometa;
	}

	public void setVrstaPrometa(String vrstaPrometa) {
		this.vrstaPrometa = vrstaPrometa;
	}

	public String getSmer() {
		return smer;
	}

	public void setSmer(String smer) {
		this.smer = smer;
	}

	public MagacinskaKartica getMagacinskaKartica() {
		return magacinskaKartica;
	}

	@JsonIgnore
	public void setMagacinskaKartica(MagacinskaKartica magacinskaKartica) {
		this.magacinskaKartica = magacinskaKartica;
	}

	public StavkaDokumenta getStavkaDokumenta() {
		return stavkaDokumenta;
	}

	public void setStavkaDokumenta(StavkaDokumenta stavkaDokumenta) {
		this.stavkaDokumenta = stavkaDokumenta;
	}


	
	
}
