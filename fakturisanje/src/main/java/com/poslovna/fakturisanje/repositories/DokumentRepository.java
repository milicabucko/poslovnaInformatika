package com.poslovna.fakturisanje.repositories;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.poslovna.fakturisanje.models.Dokument;

public interface DokumentRepository extends JpaRepository<Dokument, Integer>{
	
	public Collection<Dokument> findByBrojDokumenta(Integer brojDokumenta);
	
	@Query("select coalesce(max(d.brojDokumenta), '0') from Dokument d")
	public Integer getMaxBrojDokumenta();
	
	@Transactional
	@Modifying
	@Query("update Dokument d set d.statusDokumenta = ?1 where d.id = ?2")
	public Integer setStatusDokumentaForFaktura(String status, Integer id);
	
	public Collection<Dokument> findByVrstaDokumenta(String vrstaDokumenta);

	public Collection<Dokument> findByBrojDokumentaAndVrstaDokumenta(Integer brojDokumenta, String vrstaDokumenta);
	

}
