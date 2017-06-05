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

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "company")
public class Company implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "pib")
	private BigInteger pib;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "phonenumber")
	private String phonenumber;
	
	@Column(name = "cidnumber")
	private BigInteger cidnumber;
	
	@Column(name = "activitycode")
	private Integer activitycode;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "account")
	private String account;
	
	@Column(name = "bank")
	private String bank;
	
	
	@OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Collection<Cenovnik> cenovnici;
	
	@OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
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
