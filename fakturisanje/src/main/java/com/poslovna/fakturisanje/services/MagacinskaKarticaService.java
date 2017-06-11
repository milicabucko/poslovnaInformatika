package com.poslovna.fakturisanje.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poslovna.fakturisanje.models.Artikal;
import com.poslovna.fakturisanje.models.Magacin;
import com.poslovna.fakturisanje.models.MagacinskaKartica;
import com.poslovna.fakturisanje.repositories.MagacinskaKarticaRepository;

@Service
public class MagacinskaKarticaService {

	@Autowired
	private MagacinskaKarticaRepository magacinskaKarticaRepository;
	
	public Collection<MagacinskaKartica> nadjiSveMagacinskeKartice(Magacin magacin) {
		return magacinskaKarticaRepository.findByMagacin(magacin);
	}
	
	public MagacinskaKartica nadjiMagacinskuKarticuArtikla(Magacin magacin, Artikal artikal) {
		return magacinskaKarticaRepository.findByMagacinAndArtikal(magacin, artikal);
	} 
	
	public MagacinskaKartica findOne(Integer id) {
		return magacinskaKarticaRepository.findOne(id);
	}
	
	public MagacinskaKartica saveKartica(MagacinskaKartica kartica){
		return magacinskaKarticaRepository.save(kartica);
	}
}
