package com.poslovna.fakturisanje.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poslovna.fakturisanje.models.Faktura;
import com.poslovna.fakturisanje.repositories.FakturaRepository;

@Service
public class FakturaService {

	@Autowired
	private FakturaRepository fakturaRepository;

	public Integer nadjiSlececiBrojDokumenta() {
		return fakturaRepository.getMaxBrojDokumenta();
	}

	public Faktura save(Faktura faktura) {
		return fakturaRepository.save(faktura);
	}

	public Faktura findOne(Integer fakturaId) {
		return fakturaRepository.findOne(fakturaId);
	}

	public Collection<Faktura> getAll() {
		return fakturaRepository.findAll();
	}
	
	public Integer setStatusDokumentaForFaktura(String status, Integer id) {
		return fakturaRepository.setStatusDokumentaForFaktura(status, id);
	}
	
	
	
}
