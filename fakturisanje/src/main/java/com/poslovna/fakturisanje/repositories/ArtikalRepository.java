package com.poslovna.fakturisanje.repositories;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poslovna.fakturisanje.models.Artikal;

public interface ArtikalRepository extends JpaRepository<Artikal, Integer>{

	public Collection<Artikal> findByNazivContaining(String naziv);

	public Artikal findBySifra(String sifra);
	
	public HashSet<Artikal> findByGrupaArtikalaId(Integer id);
	
	
}
