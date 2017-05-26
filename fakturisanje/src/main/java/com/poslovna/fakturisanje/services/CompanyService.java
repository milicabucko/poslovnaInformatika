package com.poslovna.fakturisanje.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poslovna.fakturisanje.models.Company;
import com.poslovna.fakturisanje.repositories.CompanyRepository;

@Service
public class CompanyService {
	
	@Autowired
	private CompanyRepository companyRepository;
	
	public Company add(Company company){
		return companyRepository.save(company);
	}
	
	public Collection<Company> getAll(){
		return companyRepository.findAll();
	}
	
	public Collection<Company> findByIdNot(Integer id){
		return companyRepository.findByIdNot(id);
	}
	
	public Collection<Company> findByNameContaining(String name){
		return companyRepository.findByNameContaining(name);
	}

}
