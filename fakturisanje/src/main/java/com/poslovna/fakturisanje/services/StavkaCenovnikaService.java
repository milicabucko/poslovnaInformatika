package com.poslovna.fakturisanje.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poslovna.fakturisanje.models.StavkaCenovnika;
import com.poslovna.fakturisanje.repositories.StavkaCenovnikaRepository;

@Service
public class StavkaCenovnikaService {

	
	@Autowired
	private StavkaCenovnikaRepository stavkaCenovnikaRepository;
	
	public  StavkaCenovnika save( StavkaCenovnika stavkaCenovnika) {
		return stavkaCenovnikaRepository.save(stavkaCenovnika);
	}
}
