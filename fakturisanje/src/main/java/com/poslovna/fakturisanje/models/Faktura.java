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
@Table(name = "faktura")
public class Faktura implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "brojDokumenta")
	private Integer brojDokumenta;
	
	@Column(name = "statusDokumenta")
	private String statusDokumenta;
	
	@Column(name = "datumDokumenta")
	private String datumDokumenta;
	
	@Column(name = "datumValute")
	private String datumValute;
	
	@Column(name = "datumKnjizenja")
	private String datumKnjizenja;
	
	@ManyToOne
	private Company izdavaocRacuna;
	
	@ManyToOne
	private BusinessPartner kupac;
	
	@OneToMany(mappedBy = "dokument", cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private Collection<StavkaDokumenta> stavkeDokumenta;
	
	
	public Faktura() {
		
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getBrojDokumenta() {
		return brojDokumenta;
	}


	public void setBrojDokumenta(Integer brojDokumenta) {
		this.brojDokumenta = brojDokumenta;
	}


	public String getDatumDokumenta() {
		return datumDokumenta;
	}


	public void setDatumDokumenta(String datumDokumenta) {
		this.datumDokumenta = datumDokumenta;
	}


	public String getDatumValute() {
		return datumValute;
	}


	public void setDatumValute(String datumValute) {
		this.datumValute = datumValute;
	}


	public String getDatumKnjizenja() {
		return datumKnjizenja;
	}


	public void setDatumKnjizenja(String datumKnjizenja) {
		this.datumKnjizenja = datumKnjizenja;
	}


	public String getStatusDokumenta() {
		return statusDokumenta;
	}


	public void setStatusDokumenta(String statusDokumenta) {
		this.statusDokumenta = statusDokumenta;
	}


	public Company getIzdavaocRacuna() {
		return izdavaocRacuna;
	}

	
	public void setIzdavaocRacuna(Company izdavaocRacuna) {
		this.izdavaocRacuna = izdavaocRacuna;
	}


	public BusinessPartner getKupac() {
		return kupac;
	}

	
	public void setKupac(BusinessPartner kupac) {
		this.kupac = kupac;
	}


	public Collection<StavkaDokumenta> getStavkeDokumenta() {
		return stavkeDokumenta;
	}


	public void setStavkeDokumenta(Collection<StavkaDokumenta> stavkeDokumenta) {
		this.stavkeDokumenta = stavkeDokumenta;
	}
	
	
	
	

}