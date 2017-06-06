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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "vrstaPDVa")
public class VrstaPDVa implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "nazivvrstePDVa")
	private String nazivVrstePDVa;
	
	@OneToMany(mappedBy = "vrstaPDVa", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Collection<GrupaArtikala> grupaArtikala;
	
	@ManyToOne
	private StopaPDVa stopPDVa;
	
	public VrstaPDVa(){
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNazivVrstePDVa() {
		return nazivVrstePDVa;
	}

	public void setNazivVrstePDVa(String nazivVrstePDVa) {
		this.nazivVrstePDVa = nazivVrstePDVa;
	}

	public Collection<GrupaArtikala> getGrupaArtikala() {
		return grupaArtikala;
	}

	public void setGrupaArtikala(Collection<GrupaArtikala> grupaArtikala) {
		this.grupaArtikala = grupaArtikala;
	}

	public StopaPDVa getStopPDVa() {
		return stopPDVa;
	}

	public void setStopPDVa(StopaPDVa stopPDVa) {
		this.stopPDVa = stopPDVa;
	}
	
	

}
