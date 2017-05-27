package com.poslovna.fakturisanje.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poslovna.fakturisanje.repositories.FakturaRepository;

@Service
public class FakturaService {

	@Autowired
	private FakturaRepository fakturaRepository;

	public Integer nadjiSlececiBrojDokumenta() {
		return fakturaRepository.getMaxBrojDokumenta();
	}
	
	
	
}
