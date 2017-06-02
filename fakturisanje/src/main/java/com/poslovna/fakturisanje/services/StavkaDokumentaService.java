package com.poslovna.fakturisanje.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poslovna.fakturisanje.models.StavkaDokumenta;
import com.poslovna.fakturisanje.repositories.StavkaDokumentaRepository;

@Service
public class StavkaDokumentaService {

	@Autowired
	private StavkaDokumentaRepository stavkaDokumentaRepository;
	
	
	public StavkaDokumenta save(StavkaDokumenta stavkaDokumenta) {
		return stavkaDokumentaRepository.save(stavkaDokumenta);
	}
	
	public StavkaDokumenta findOne(Integer id) {
		return stavkaDokumentaRepository.findOne(id);
	}
	
}
