package com.poslovna.fakturisanje.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "artikal")

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "jedinicaMere", "grupaArtikala"})
@XmlRootElement(name = "artikal")
public class Artikal implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	@XmlAttribute(name = "id", required = true)
	private Integer id;
	
	@Column(name = "sifra")
	@XmlAttribute(name = "sifra", required = true)
	private String sifra;
	
	@Column(name = "naziv")
	@XmlAttribute(name = "naziv", required = true)
	private String naziv;
	
	@Column(name = "opis")
	@XmlAttribute(name = "opis", required = true)
	private String opis;
	
	@Column(name = "vrsta")
	@XmlAttribute(name = "vrsta", required = true)
	private String vrsta;
	
	@ManyToOne
	@XmlElement(required = false)
	private JedinicaMere jedinicaMere;
	
	@ManyToOne
	@XmlElement(required = false)
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
