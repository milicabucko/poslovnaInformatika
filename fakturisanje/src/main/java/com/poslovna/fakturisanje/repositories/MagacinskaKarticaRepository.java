package com.poslovna.fakturisanje.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poslovna.fakturisanje.models.Artikal;
import com.poslovna.fakturisanje.models.Magacin;
import com.poslovna.fakturisanje.models.MagacinskaKartica;

public interface MagacinskaKarticaRepository extends JpaRepository<MagacinskaKartica, Integer>{

	public Collection<MagacinskaKartica> findByMagacin(Magacin magacin);
	
	public MagacinskaKartica findByMagacinAndArtikal(Magacin magacin, Artikal artikal);
	
}
