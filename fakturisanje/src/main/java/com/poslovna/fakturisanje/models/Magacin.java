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
@Table(name = "magacin")
public class Magacin implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "sifra")
	private String sifra;
	
	@Column(name = "naziv")
	private String naziv;
	
	@ManyToOne
	private Company preduzece;
	
	@OneToMany(mappedBy = "magacin", cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private Collection<MagacinskaKartica> kartice;
	

	public Magacin() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	
	public String getSifra() {
		return sifra;
	}

	public void setSifra(String sifra) {
		this.sifra = sifra;
	}

	public Company getPreduzece() {
		return preduzece;
	}

	public void setPreduzece(Company preduzece) {
		this.preduzece = preduzece;
	}

	public Collection<MagacinskaKartica> getKartice() {
		return kartice;
	}

	public void setKartice(Collection<MagacinskaKartica> kartice) {
		this.kartice = kartice;
	}
	
}
