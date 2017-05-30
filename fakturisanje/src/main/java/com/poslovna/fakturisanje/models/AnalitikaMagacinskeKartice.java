package com.poslovna.fakturisanje.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
	private StavkaDokumenta stavkaDok;
	
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

	public StavkaDokumenta getStavkaDok() {
		return stavkaDok;
	}

	public void setStavkaDok(StavkaDokumenta stavkaDok) {
		this.stavkaDok = stavkaDok;
	}
	
	
}
