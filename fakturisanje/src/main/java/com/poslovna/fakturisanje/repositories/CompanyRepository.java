package com.poslovna.fakturisanje.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poslovna.fakturisanje.models.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
	
	public Collection<Company> findByIdNot(Integer id);

}
