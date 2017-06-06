package com.poslovna.fakturisanje.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poslovna.fakturisanje.models.Cenovnik;
import com.poslovna.fakturisanje.models.Company;
import com.poslovna.fakturisanje.models.GrupaArtikala;
import com.poslovna.fakturisanje.repositories.GrupaArtikalaRepository;

@Service
public class GrupaAtrikalaService {
	
	@Autowired
	private GrupaArtikalaRepository grupaArtikalaRepository;
	
	public Collection<GrupaArtikala> findByCompany(Company company) {
		return grupaArtikalaRepository.findByCompany(company);
	}
	
	public GrupaArtikala add(GrupaArtikala grupa){
		return grupaArtikalaRepository.save(grupa);
	}
	

}
