package com.poslovna.fakturisanje.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poslovna.fakturisanje.models.BusinessPartner;
import com.poslovna.fakturisanje.models.Company;
import com.poslovna.fakturisanje.repositories.BusinessPartnerRepository;
import com.poslovna.fakturisanje.repositories.CompanyRepository;

@Service
public class BusinessPartnerService {
	
	@Autowired 
	private BusinessPartnerRepository businessPartnerRepository;
	
	@Autowired
	private CompanyRepository companyRepository;
	
	public Collection<BusinessPartner> findByCompany(Integer id){
		Company company = companyRepository.findOne(id);
		return businessPartnerRepository.findByCompany(company);
	}

	public BusinessPartner findOne(Integer kupId) {
		return businessPartnerRepository.findOne(kupId);
	}

}
