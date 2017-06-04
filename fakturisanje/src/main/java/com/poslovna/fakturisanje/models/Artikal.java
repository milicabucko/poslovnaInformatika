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
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "artikal")
public class Artikal implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "sifra")
	private String sifra;
	
	@Column(name = "naziv")
	private String naziv;
	
	@Column(name = "opis")
	private String opis;
	
	@Column(name = "vrsta")
	private String vrsta;
	
	@ManyToOne
	private JedinicaMere jedinicaMere;
	
	@ManyToOne
	private GrupaArtikala grupaArtikala;
	
	
	public Artikal() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSifra() {
		return sifra;
	}

	public void setSifra(String sifra) {
		this.sifra = sifra;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public String getVrsta() {
		return vrsta;
	}

	public void setVrsta(String vrsta) {
		this.vrsta = vrsta;
	}
	
	public JedinicaMere getJedinicaMere() {
		return jedinicaMere;
	}
	
	
	public void setJedinicaMere(JedinicaMere jedinicaMere) {
		this.jedinicaMere = jedinicaMere;
	}

	public GrupaArtikala getGrupaArtikala() {
		return grupaArtikala;
	}

	@JsonIgnore
	public void setGrupaArtikala(GrupaArtikala grupaArtikala) {
		this.grupaArtikala = grupaArtikala;
	}
	
	
}
