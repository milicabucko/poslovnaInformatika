package com.poslovna.fakturisanje.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poslovna.fakturisanje.models.Magacin;
import com.poslovna.fakturisanje.repositories.MagacinRepository;

@Service
public class MagacinService {

	@Autowired
	private MagacinRepository magacinRepository;
	
	public Magacin findBySifra(String sifra){
		return magacinRepository.findBySifra(sifra);
	}
	
	public Magacin save(Magacin magacin){
		return magacinRepository.save(magacin);
	}
}
