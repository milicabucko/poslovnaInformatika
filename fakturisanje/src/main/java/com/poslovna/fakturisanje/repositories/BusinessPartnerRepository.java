package com.poslovna.fakturisanje.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poslovna.fakturisanje.models.BusinessPartner;
import com.poslovna.fakturisanje.models.Company;

public interface BusinessPartnerRepository extends JpaRepository<BusinessPartner, Integer> {

	public Collection<BusinessPartner> findByCompany1(Company company1);
}
