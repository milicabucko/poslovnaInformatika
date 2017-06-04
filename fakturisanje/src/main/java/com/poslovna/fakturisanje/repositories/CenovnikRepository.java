package com.poslovna.fakturisanje.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poslovna.fakturisanje.models.Cenovnik;
import com.poslovna.fakturisanje.models.Company;

public interface CenovnikRepository extends JpaRepository<Cenovnik, Integer> {
	
	public Collection<Cenovnik> findByCompany(Company company);
	
	public Cenovnik findByCompanyAndAktivan(Company company, Boolean aktivan);
	
	public Collection<Cenovnik> findByCompanyAndDatumVazenjaPocetakBetween(Company company, String datumPocetakVazenja, String datumKrajVazenja);

	public Collection<Cenovnik> findByCompanyAndDatumVazenjaKrajBetween(Company company, String datumPocetakVazenja, String datumKrajVazenja);
}
