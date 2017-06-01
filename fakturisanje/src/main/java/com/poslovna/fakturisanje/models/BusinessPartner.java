package com.poslovna.fakturisanje.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "businesspartner")
public class BusinessPartner implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	
	@ManyToOne
	private Company company1;
	
	@ManyToOne
	private Company company2;
	
	public BusinessPartner() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Company getCompany1() {
		return company1;
	}
	
	public void setCompany1(Company company1) {
		this.company1 = company1;
	}

	public Company getCompany2() {
		return company2;
	}

	public void setCompany2(Company company2) {
		this.company2 = company2;
	}

	
	

}
