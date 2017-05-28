package com.poslovna.fakturisanje.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poslovna.fakturisanje.models.Artikal;
import com.poslovna.fakturisanje.repositories.ArtikalRepository;

@Service
public class ArtikalService {
	
	@Autowired
	private ArtikalRepository artikalRepository;

	public Collection<Artikal> findByNazivContaining(String naziv) {
		return artikalRepository.findByNazivContaining(naziv);
	}

	public Artikal findBySifra(String sifra) {
		return artikalRepository.findBySifra(sifra);
	}

	public Artikal findOne(Integer artikalId) {
		return artikalRepository.findOne(artikalId);
	}
	
	

}
