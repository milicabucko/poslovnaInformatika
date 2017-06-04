package com.poslovna.fakturisanje.repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poslovna.fakturisanje.models.Company;
import com.poslovna.fakturisanje.models.StopaPDVa;

public interface StopaPDVaRepository extends JpaRepository<StopaPDVa, Integer> {
	
	

}
