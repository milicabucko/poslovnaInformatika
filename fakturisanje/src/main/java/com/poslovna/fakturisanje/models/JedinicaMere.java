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

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "jedinicamere")

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "company"})
@XmlRootElement(name = "jedinicamere")
public class JedinicaMere implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	@XmlAttribute(name = "id", required = true)
	private Integer id;
	
	@Column(name = "oznakaJedinice")
	@XmlAttribute(name = "oznakaJedinice", required = true)
	private String oznakaJedinice;
	
	@Column(name = "nazivJedinice")
	@XmlAttribute(name = "nazivJedinice", required = true)
	private String nazivJedinice;
	
	@ManyToOne
	@JsonBackReference
	@XmlElement(required = false)
	private Company company;
	

	public JedinicaMere() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOznakaJedinice() {
		return oznakaJedinice;
	}

	public void setOznakaJedinice(String oznakaJedinice) {
		this.oznakaJedinice = oznakaJedinice;
	}

	public String getNazivJedinice() {
		return nazivJedinice;
	}

	public void setNazivJedinice(String nazivJedinice) {
		this.nazivJedinice = nazivJedinice;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
	
	

}
