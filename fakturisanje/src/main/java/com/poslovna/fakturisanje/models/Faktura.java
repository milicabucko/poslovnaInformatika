package com.poslovna.fakturisanje.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
	
	@Column(name = "datumDokumenta")
	private String datumDokumenta;
	
	@Column(name = "datumValute")
	private String datumValute;
	
	@Column(name = "datumKnjizenja")
	private String datumKnjizenja;
	
	
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
	
	
	
	

}
