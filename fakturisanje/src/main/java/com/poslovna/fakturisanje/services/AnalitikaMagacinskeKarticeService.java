package com.poslovna.fakturisanje.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poslovna.fakturisanje.models.AnalitikaMagacinskeKartice;
import com.poslovna.fakturisanje.repositories.AnalitikaMagacinskeKarticeRepository;

@Service
public class AnalitikaMagacinskeKarticeService {

	@Autowired
	private AnalitikaMagacinskeKarticeRepository analitikaMagacinskeKarticeRepository;
	
	public AnalitikaMagacinskeKartice save(AnalitikaMagacinskeKartice analitikaMagacinskeKartice) {
		return analitikaMagacinskeKarticeRepository.save(analitikaMagacinskeKartice);
	}
	
}
