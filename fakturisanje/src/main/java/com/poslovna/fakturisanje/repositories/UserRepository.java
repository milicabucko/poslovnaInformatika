package com.poslovna.fakturisanje.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poslovna.fakturisanje.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	public User findByUsername(String username);
}
