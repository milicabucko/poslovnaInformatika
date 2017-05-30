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
@Table(name = "cenovnik")
public class Cenovnik implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "datumVazenjaPocetak")
	private String datumVazenjaPocetak;
	
	@Column(name = "datumVazenjaKraj")
	private String datumVazenjaKraj;
	
	@OneToMany(mappedBy = "cenovnik", cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private Collection<StavkaCenovnika> stavkecenovnika;
	
	@ManyToOne
	private Company company;
	
	public Cenovnik() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public Company getCompany() {
		return company;
	}

	@JsonIgnore
	public void setCompany(Company company) {
		this.company = company;
	}

	public Collection<StavkaCenovnika> getStavkecenovnika() {
		return stavkecenovnika;
	}

	public void setStavkecenovnika(Collection<StavkaCenovnika> stavkecenovnika) {
		this.stavkecenovnika = stavkecenovnika;
	}

	public String getDatumVazenjaPocetak() {
		return datumVazenjaPocetak;
	}

	public void setDatumVazenjaPocetak(String datumVazenjaPocetak) {
		this.datumVazenjaPocetak = datumVazenjaPocetak;
	}

	public String getDatumVazenjaKraj() {
		return datumVazenjaKraj;
	}

	public void setDatumVazenjaKraj(String datumVazenjaKraj) {
		this.datumVazenjaKraj = datumVazenjaKraj;
	}
	

}
