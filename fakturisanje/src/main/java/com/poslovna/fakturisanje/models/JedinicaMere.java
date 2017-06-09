package com.poslovna.fakturisanje.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "jedinicamere")
public class JedinicaMere implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "oznakaJedinice")
	private String oznakaJedinice;
	
	@Column(name = "nazivJedinice")
	private String nazivJedinice;
	

	public JedinicaMere() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOznakaJedinice() {
		return oznakaJedinice;
	}

	public void setOznakaJedinice(String oznakaJedinice) {
		this.oznakaJedinice = oznakaJedinice;
	}

	public String getNazivJedinice() {
		return nazivJedinice;
	}

	public void setNazivJedinice(String nazivJedinice) {
		this.nazivJedinice = nazivJedinice;
	}

}
