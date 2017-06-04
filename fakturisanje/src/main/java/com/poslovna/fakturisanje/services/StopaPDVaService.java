package com.poslovna.fakturisanje.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poslovna.fakturisanje.models.StopaPDVa;
import com.poslovna.fakturisanje.repositories.StopaPDVaRepository;
@Service
public class StopaPDVaService {
	
	@Autowired
	private StopaPDVaRepository stopaPDVaRepository;
	
	public StopaPDVa save(StopaPDVa stopaPDVa) {
		return stopaPDVaRepository.save(stopaPDVa);
	}
	
	public Collection<StopaPDVa> getAll() {
		return stopaPDVaRepository.findAll();
	}

}
