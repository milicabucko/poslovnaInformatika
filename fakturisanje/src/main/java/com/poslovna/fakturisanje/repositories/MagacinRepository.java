package com.poslovna.fakturisanje.repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poslovna.fakturisanje.models.Company;
import com.poslovna.fakturisanje.models.Magacin;

public interface MagacinRepository extends JpaRepository<Magacin, Integer>{

	public Magacin findBySifra(String sifra);
	
	public Collection<Magacin> findAllBySifra(String sifra);
	
	public Collection<Magacin> findAllByPreduzece(Company preduzece);
	
	public List<Magacin> findAll();
	
	public Collection<Magacin> findBySifraContaining(String sifra);

	public Magacin findByPreduzece(Company preduzece);
	
	public void delete(Magacin magacin);
	
}
