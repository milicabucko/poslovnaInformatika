package com.poslovna.fakturisanje.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poslovna.fakturisanje.models.Magacin;

public interface MagacinRepository extends JpaRepository<Magacin, Integer>{

	public Magacin findBySifra(String sifra);
	
}
