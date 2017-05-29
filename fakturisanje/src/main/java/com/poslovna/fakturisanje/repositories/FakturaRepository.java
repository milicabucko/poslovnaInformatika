package com.poslovna.fakturisanje.repositories;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.poslovna.fakturisanje.models.Faktura;

public interface FakturaRepository extends JpaRepository<Faktura, Integer>{
	
	public Collection<Faktura> findByBrojDokumenta(Integer brojDokumenta);
	
	@Query("select coalesce(max(f.brojDokumenta), '0') from Faktura f")
	public Integer getMaxBrojDokumenta();
	
	@Transactional
	@Modifying
	@Query("update Faktura f set f.statusDokumenta = ?1 where f.id = ?2")
	public Integer setStatusDokumentaForFaktura(String status, Integer id);
	

}
