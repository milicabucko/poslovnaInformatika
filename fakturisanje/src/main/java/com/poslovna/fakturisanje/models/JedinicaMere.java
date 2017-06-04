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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "jedinicamere")
public class JedinicaMere implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "tipJedinice")
	private String tipJedinice;
	
	@OneToMany(mappedBy = "jedinicaMere", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Collection<Artikal> artikli;

	public JedinicaMere() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTipJedinice() {
		return tipJedinice;
	}

	public void setTipJedinice(String tipJedinice) {
		this.tipJedinice = tipJedinice;
	}
	
	
	
	

}
