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
@Table(name = "kartica")
public class MagacinskaKartica implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "pocStanjeKol")
	private Float pocStanjeKol;
	
	@Column(name = "pocStanjeVred")
	private Float pocStanjeVred;
	
	@Column(name = "prometUlKol")
	private Float prometUlKol;
	
	@Column(name = "prometIzKol")
	private Float prometIzKol;
	
	@Column(name = "prometUlVred")
	private Float prometUlVred;
	
	@Column(name = "prometIzVred")
	private Float prometIzVred;
	
	@ManyToOne
	private StavkaCenovnika stavkaCenovnika;
	
	@ManyToOne
	private Artikal artikal;
	
	@ManyToOne
	private Magacin magacin;
	
	//fali Poslovna godina 
	
	@OneToMany(mappedBy = "magacinskaKartica", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Collection<AnalitikaMagacinskeKartice> analitike;
	
	public MagacinskaKartica() {
		
	}
	
	public MagacinskaKartica(float i, float j, float k, float l,
			float m, float n, StavkaCenovnika stavkaCenovnika, Artikal artikal, Magacin magacin){
		this.pocStanjeKol = i;
		this.pocStanjeVred = j;
		this.prometUlKol = k;
		this.prometIzKol = l;
		this.prometUlVred = m;
		this.prometIzVred = n;
		this.stavkaCenovnika = stavkaCenovnika;
		this.artikal = artikal;
		this.magacin = magacin;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	public Magacin getMagacin() {
		return magacin;
	}

	@JsonIgnore
	public void setMagacin(Magacin magacin) {
		this.magacin = magacin;
	}

	public Collection<AnalitikaMagacinskeKartice> getAnalitike() {
		return analitike;
	}

	public void setAnalitike(Collection<AnalitikaMagacinskeKartice> analitike) {
		this.analitike = analitike;
	}

	public Artikal getArtikal() {
		return artikal;
	}

	public void setArtikal(Artikal artikal) {
		this.artikal = artikal;
	}

	public Float getPocStanjeKol() {
		return pocStanjeKol;
	}

	public void setPocStanjeKol(Float pocStanjeKol) {
		this.pocStanjeKol = pocStanjeKol;
	}

	public Float getPocStanjeVred() {
		return pocStanjeVred;
	}

	public void setPocStanjeVred(Float pocStanjeVred) {
		this.pocStanjeVred = pocStanjeVred;
	}

	public Float getPrometUlKol() {
		return prometUlKol;
	}

	public void setPrometUlKol(Float prometUlKol) {
		this.prometUlKol = prometUlKol;
	}

	public Float getPrometIzKol() {
		return prometIzKol;
	}

	public void setPrometIzKol(Float prometIzKol) {
		this.prometIzKol = prometIzKol;
	}

	public Float getPrometUlVred() {
		return prometUlVred;
	}

	public void setPrometUlVred(Float prometUlVred) {
		this.prometUlVred = prometUlVred;
	}

	public Float getPrometIzVred() {
		return prometIzVred;
	}

	public void setPrometIzVred(Float prometIzVred) {
		this.prometIzVred = prometIzVred;
	}

	public StavkaCenovnika getStavkaCenovnika() {
		return stavkaCenovnika;
	}

	public void setStavkaCenovnika(StavkaCenovnika stavkaCenovnika) {
		this.stavkaCenovnika = stavkaCenovnika;
	}

	
	
}  
