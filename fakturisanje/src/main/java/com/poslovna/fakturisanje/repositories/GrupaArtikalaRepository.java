package com.poslovna.fakturisanje.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poslovna.fakturisanje.models.Company;
import com.poslovna.fakturisanje.models.GrupaArtikala;

public interface GrupaArtikalaRepository extends JpaRepository<GrupaArtikala, Integer>{
	
	public Collection<GrupaArtikala> findByCompany(Company company);

}
