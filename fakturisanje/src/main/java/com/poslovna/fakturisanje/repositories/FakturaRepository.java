package com.poslovna.fakturisanje.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poslovna.fakturisanje.models.Faktura;

public interface FakturaRepository extends JpaRepository<Faktura, Integer>{
	
	@Query("select coalesce(max(f.brojDokumenta), '0') from Faktura f")
	public Integer getMaxBrojDokumenta();

}
