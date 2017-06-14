package com.poslovna.fakturisanje.models;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@Entity
@Table(name = "company")

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "cenovnici", "grupeArtikala"})
@XmlRootElement(name = "company")
public class Company implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	@XmlAttribute(name = "id", required = true)
	private Integer id;
	
	@Column(name = "name")
	@XmlAttribute(name = "name", required = true)
	private String name;
	
	@Column(name = "pib")
	@XmlAttribute(name = "pib", required = true)
	private BigInteger pib;
	
	@Column(name = "address")
	@XmlAttribute(name = "address", required = true)
	private String address;
	
	@Column(name = "phonenumber")
	@XmlAttribute(name = "phonenumber", required = true)
	private String phonenumber;
	
	@Column(name = "cidnumber")
	@XmlAttribute(name = "cidnumber", required = true)
	private BigInteger cidnumber;
	
	@Column(name = "activitycode")
	@XmlAttribute(name = "activitycode", required = true)
	private Integer activitycode;
	
	@Column(name = "email")
	@XmlAttribute(name = "email", required = true)
	private String email;
	
	@Column(name = "account")
	@XmlAttribute(name = "account", required = true)
	private String account;
	
	@Column(name = "bank")
	@XmlAttribute(name = "bank", required = true)
	private String bank;
	
	
	@OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@XmlElement(required = false)
	private Collection<Cenovnik> cenovnici;
	
	@OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@XmlElement(required = false)
	private Collection<GrupaArtikala> grupeArtikala;

	public Company() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigInteger getPib() {
		return pib;
	}

	public void setPib(BigInteger pib) {
		this.pib = pib;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public BigInteger getCidnumber() {
		return cidnumber;
	}

	public void setCidnumber(BigInteger cidnumber) {
		this.cidnumber = cidnumber;
	}

	public Integer getActivitycode() {
		return activitycode;
	}

	public void setActivitycode(Integer activitycode) {
		this.activitycode = activitycode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}
	

	public Collection<Cenovnik> getCenovnici() {
		return cenovnici;
	}

	public void setCenovnici(Collection<Cenovnik> cenovnici) {
		this.cenovnici = cenovnici;
	}

	public Collection<GrupaArtikala> getGrupeArtikala() {
		return grupeArtikala;
	}

	public void setGrupeArtikala(Collection<GrupaArtikala> grupeArtikala) {
		this.grupeArtikala = grupeArtikala;
	}
	

}
