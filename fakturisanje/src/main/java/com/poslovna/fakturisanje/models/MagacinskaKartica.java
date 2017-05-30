package com.poslovna.fakturisanje.models;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

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
	private Float pocStanjeKolicinski;
	
	@Column(name = "pocStanjeVred")
	private Float pocStanjeVrednosno;
	
	@Column(name = "prometUlKol")
	private Float prometUlazaKol;
	
	@Column(name = "prometIzKol")
	private Float prometIzlazaKol;
	
	@Column(name = "prometUlVred")
	private Float prometUlazaVred;
	
	@Column(name = "prometIzVred")
	private Float prometIzlazaVred;
	
	@ManyToOne
	private Magacin magacin;
	
	//fali Poslovna godina 
	
	@OneToMany(mappedBy = "stavkaDok", cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private Collection<AnalitikaMagacinskeKartice> analitike;
	
	public MagacinskaKartica() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Float getPocStanjeKolicinski() {
		return pocStanjeKolicinski;
	}

	public void setPocStanjeKolicinski(Float pocStanjeKolicinski) {
		this.pocStanjeKolicinski = pocStanjeKolicinski;
	}

	public Float getPocStanjeVrednosno() {
		return pocStanjeVrednosno;
	}

	public void setPocStanjeVrednosno(Float pocStanjeVrednosno) {
		this.pocStanjeVrednosno = pocStanjeVrednosno;
	}

	public Float getPrometUlazaKol() {
		return prometUlazaKol;
	}

	public void setPrometUlazaKol(Float prometUlazaKol) {
		this.prometUlazaKol = prometUlazaKol;
	}

	public Float getPrometIzlazaKol() {
		return prometIzlazaKol;
	}

	public void setPrometIzlazaKol(Float prometIzlazaKol) {
		this.prometIzlazaKol = prometIzlazaKol;
	}

	public Float getPrometUlazaVred() {
		return prometUlazaVred;
	}

	public void setPrometUlazaVred(Float prometUlazaVred) {
		this.prometUlazaVred = prometUlazaVred;
	}

	public Float getPrometIzlazaVred() {
		return prometIzlazaVred;
	}

	public void setPrometIzlazaVred(Float prometIzlazaVred) {
		this.prometIzlazaVred = prometIzlazaVred;
	}

	public Magacin getMagacin() {
		return magacin;
	}

	public void setMagacin(Magacin magacin) {
		this.magacin = magacin;
	}

	public Collection<AnalitikaMagacinskeKartice> getAnalitike() {
		return analitike;
	}

	public void setAnalitike(Collection<AnalitikaMagacinskeKartice> analitike) {
		this.analitike = analitike;
	}

	
	
}  
