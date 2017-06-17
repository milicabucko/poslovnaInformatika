package com.poslovna.fakturisanje.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poslovna.fakturisanje.models.Korisnik;

public interface UserRepository extends JpaRepository<Korisnik, Integer> {
	
	public Korisnik findByUsername(String username);
	public Korisnik findByUsernameAndPassword(String username, String password);
}
