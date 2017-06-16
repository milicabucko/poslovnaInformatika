package com.poslovna.fakturisanje.models;

import java.math.BigInteger;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class CompanyXML {

	
	@XmlAttribute(name = "name", required = true)
	private String name;
	
	@XmlAttribute(name = "pib", required = true)
	private BigInteger pib;
	
	@XmlAttribute(name = "address", required = true)
	private String address;
	
	@XmlAttribute(name = "phonenumber", required = true)
	private String phonenumber;
	
	@XmlAttribute(name = "mbr", required = true)
	private BigInteger mbr;
	
	@XmlAttribute(name = "activitycode", required = true)
	private Integer activitycode;
	
	@XmlAttribute(name = "email", required = true)
	private String email;
	
	@XmlAttribute(name = "account", required = true)
	private String account;
	
	@XmlAttribute(name = "bank", required = true)
	private String bank;

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

	public BigInteger getMbr() {
		return mbr;
	}

	public void setMbr(BigInteger mbr) {
		this.mbr = mbr;
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
	
}
