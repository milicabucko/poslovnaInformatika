package com.poslovna.fakturisanje.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poslovna.fakturisanje.models.Company;
import com.poslovna.fakturisanje.models.PoslovnaGodina;

public interface PoslovnaGodinaRepository extends JpaRepository<PoslovnaGodina, Integer> {

	public PoslovnaGodina findByPreduzeceAndBrojGodine(Company company, Integer brojGodine);
	
	public Collection<PoslovnaGodina> findAllByPreduzece(Company company);
}
