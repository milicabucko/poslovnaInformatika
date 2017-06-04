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
@Table(name = "stopaPDVa")
public class StopaPDVa implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "datumStope")
	private String datumStope;
	
	@Column(name = "procenatPDVa")
	private int procenatPDVa;
	
	@ManyToOne
	private VrstaPDVa vrstaPDVa;
	
	public StopaPDVa(){
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDatumStope() {
		return datumStope;
	}

	public void setDatumStope(String datumStope) {
		this.datumStope = datumStope;
	}

	public int getProcenatPDVa() {
		return procenatPDVa;
	}

	public void setProcenatPDVa(int procenatPDVa) {
		this.procenatPDVa = procenatPDVa;
	}

	public VrstaPDVa getVrstaPDVa() {
		return vrstaPDVa;
	}

	@JsonIgnore
	public void setVrstaPDVa(VrstaPDVa vrstaPDVa) {
		this.vrstaPDVa = vrstaPDVa;
	}
	
	

}
