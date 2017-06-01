package com.poslovna.fakturisanje.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poslovna.fakturisanje.models.BusinessPartner;
import com.poslovna.fakturisanje.models.Cenovnik;
import com.poslovna.fakturisanje.repositories.CenovnikRepository;

@Service
public class CenovnikService {
	
	@Autowired
	private CenovnikRepository cenovnikRepository;
	
	public Cenovnik add(Cenovnik cenovnik){
		return cenovnikRepository.save(cenovnik);
	}
	
	public Collection<Cenovnik> getAll(){
		return cenovnikRepository.findAll();
	}
	
	public Cenovnik findOne(Integer cenovnikId) {
		return cenovnikRepository.findOne(cenovnikId);
	}

	public Collection<Cenovnik> findByCompany(Integer companyId) {
		return cenovnikRepository.findByCompany(companyId);
	}
}
