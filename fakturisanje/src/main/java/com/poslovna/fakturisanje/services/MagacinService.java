package com.poslovna.fakturisanje.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poslovna.fakturisanje.models.Company;
import com.poslovna.fakturisanje.models.Magacin;
import com.poslovna.fakturisanje.repositories.MagacinRepository;

@Service
public class MagacinService {

	@Autowired
	private MagacinRepository magacinRepository;
	
	public Magacin findBySifra(String sifra){
		return magacinRepository.findBySifra(sifra);
	}
	
	public Collection<Magacin> findAllBySifra(String sifra){
		return magacinRepository.findAllBySifra(sifra);
	}
	
	public Magacin findById(Integer id) {
		return magacinRepository.findOne(id);
	}
	
	public Magacin save(Magacin magacin){
		return magacinRepository.save(magacin);
	}
	
	public Collection<Magacin> findAll() {
		return magacinRepository.findAll();
	}

	public Magacin findByPreduzece(Company preduzece) {
		return magacinRepository.findByPreduzece(preduzece);
	}
	
	public Collection<Magacin> findBySifraContaining(String sifra){
		return magacinRepository.findBySifraContaining(sifra);
	}
	
	public void deleteMagacin(Magacin magacin){
		magacinRepository.delete(magacin);
	}
}
