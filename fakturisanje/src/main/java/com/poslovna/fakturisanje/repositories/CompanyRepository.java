package com.poslovna.fakturisanje.repositories;

import java.math.BigInteger;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poslovna.fakturisanje.models.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
	
	public Collection<Company> findByIdNot(Integer id);
	
	public Collection<Company> findByNameContaining(String name);
	
	public Company findByPib(BigInteger pib);
	
	public Company findByCidnumber(BigInteger cidnumber);
	
	public Company findByAccount(String account);
	


}
