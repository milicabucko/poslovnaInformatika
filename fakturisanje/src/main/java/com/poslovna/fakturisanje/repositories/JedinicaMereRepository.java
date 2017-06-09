package com.poslovna.fakturisanje.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poslovna.fakturisanje.models.JedinicaMere;

public interface JedinicaMereRepository extends JpaRepository<JedinicaMere, Integer>{
	
	<E extends JedinicaMere> List<E> findByCompanyId(Integer id);

}
