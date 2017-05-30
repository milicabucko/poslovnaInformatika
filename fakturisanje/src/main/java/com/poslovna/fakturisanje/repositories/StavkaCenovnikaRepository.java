package com.poslovna.fakturisanje.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poslovna.fakturisanje.models.StavkaCenovnika;

public interface StavkaCenovnikaRepository extends JpaRepository<StavkaCenovnika, Integer>{

}
