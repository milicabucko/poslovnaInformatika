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
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "cenovnik")

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "stavkecenovnika", "company"})
@XmlRootElement(name = "cenovnik")
public class Cenovnik implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	@XmlAttribute(name = "id", required = true)
	private Integer id;
	
	@Column(name = "datumVazenjaPocetak")
	@XmlAttribute(name = "datumVazenjaPocetak", required = true)
	private String datumVazenjaPocetak;
	
	@Column(name = "datumVazenjaKraj")
	@XmlAttribute(name = "datumVazenjaKraj", required = true)
	private String datumVazenjaKraj;
	
	@Column(name = "aktivan")
	@XmlAttribute(name = "aktivan", required = true)
	private Boolean aktivan;
	
	@OneToMany(mappedBy = "cenovnik", cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	//@XmlElement(required = false)
	private Collection<StavkaCenovnika> stavkecenovnika;
	
	@ManyToOne
	@XmlElement(required = false)
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

	public Boolean getAktivan() {
		return aktivan;
	}

	public void setAktivan(Boolean aktivan) {
		this.aktivan = aktivan;
	}
}
