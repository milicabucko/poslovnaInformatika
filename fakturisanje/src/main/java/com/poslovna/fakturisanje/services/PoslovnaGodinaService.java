package com.poslovna.fakturisanje.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poslovna.fakturisanje.models.Company;
import com.poslovna.fakturisanje.models.PoslovnaGodina;
import com.poslovna.fakturisanje.repositories.PoslovnaGodinaRepository;

@Service
public class PoslovnaGodinaService {

	@Autowired
	private PoslovnaGodinaRepository poslovnaGodinaRepository;
	
	public PoslovnaGodina sacuvajGodinu(PoslovnaGodina godina){
		return poslovnaGodinaRepository.save(godina);
	}
	
	public PoslovnaGodina findByPreduzeceAndBrojGodine(Company company, Integer brojGodine){
		return poslovnaGodinaRepository.findByPreduzeceAndBrojGodine(company, brojGodine);
	}
	
	public Collection<PoslovnaGodina> findAll(){
		return poslovnaGodinaRepository.findAll();
	}
	
	public Collection<PoslovnaGodina> findAllByPreduzece(Company company){
		return poslovnaGodinaRepository.findAllByPreduzece(company);
	}
	
	public PoslovnaGodina findByPreduzeceAndAktivna(Company company, Boolean aktivna){
		return poslovnaGodinaRepository.findByPreduzeceAndAktivna(company, aktivna);
	}
}
