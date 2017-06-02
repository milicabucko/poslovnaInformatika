package com.poslovna.fakturisanje.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poslovna.fakturisanje.models.Dokument;
import com.poslovna.fakturisanje.repositories.DokumentRepository;

@Service
public class DokumentService {

	@Autowired
	private DokumentRepository fakturaRepository;

	public Integer nadjiSlececiBrojDokumenta() {
		return fakturaRepository.getMaxBrojDokumenta();
	}

	public Dokument save(Dokument faktura) {
		return fakturaRepository.save(faktura);
	}

	public Dokument findOne(Integer fakturaId) {
		return fakturaRepository.findOne(fakturaId);
	}

	public Collection<Dokument> getAll() {
		return fakturaRepository.findAll();
	}
	
	public Integer setStatusDokumentaForFaktura(String status, Integer id) {
		return fakturaRepository.setStatusDokumentaForFaktura(status, id);
	}
	
	public Collection<Dokument> findByBrojDokumenta(Integer brojDokumenta) {
		return fakturaRepository.findByBrojDokumenta(brojDokumenta);
	}
	
	
	
}
