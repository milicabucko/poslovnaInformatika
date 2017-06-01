package com.poslovna.fakturisanje.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poslovna.fakturisanje.models.Cenovnik;

public interface CenovnikRepository extends JpaRepository<Cenovnik, Integer> {
	
	public Collection<Cenovnik> findByCompany(Integer company);

}
