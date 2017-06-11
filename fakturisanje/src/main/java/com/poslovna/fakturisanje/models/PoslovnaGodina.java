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
@Table(name = "poslovnaGodina")
public class PoslovnaGodina implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "brojGodine")
	private Integer brojGodine;
	
	@Column(name = "aktivna")
	private Boolean aktivna;
	
	@Column(name = "datumPocetka")
	private String datumPocetka;
	
	@Column(name = "datumZavrsetka")
	private String datumZavrsetka;
	
	@ManyToOne
	private Company preduzece;
	
	@OneToMany(mappedBy = "poslovnaGodina", cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private Collection<Dokument> dokumenti;
	
	public PoslovnaGodina() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBrojGodine() {
		return brojGodine;
	}

	public void setBrojGodine(Integer brojGodine) {
		this.brojGodine = brojGodine;
	}

	public Boolean getAktivna() {
		return aktivna;
	}

	public void setAktivna(Boolean aktivna) {
		this.aktivna = aktivna;
	}

	public Company getPreduzece() {
		return preduzece;
	}

	public void setPreduzece(Company preduzece) {
		this.preduzece = preduzece;
	}

	public String getDatumPocetka() {
		return datumPocetka;
	}

	public void setDatumPocetka(String datumPocetka) {
		this.datumPocetka = datumPocetka;
	}

	public String getDatumZavrsetka() {
		return datumZavrsetka;
	}

	public void setDatumZavrsetka(String datumZavrsetka) {
		this.datumZavrsetka = datumZavrsetka;
	}

	public Collection<Dokument> getDokumenti() {
		return dokumenti;
	}

	public void setDokumenti(Collection<Dokument> dokumenti) {
		this.dokumenti = dokumenti;
	}
}
