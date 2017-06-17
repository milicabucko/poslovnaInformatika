package com.poslovna.fakturisanje.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poslovna.fakturisanje.models.Korisnik;
import com.poslovna.fakturisanje.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public Korisnik findByUsername(String username){
		return userRepository.findByUsername(username);
	}
	
	public Korisnik findByUsernameAndPassword(String username, String password){
		return userRepository.findByUsernameAndPassword(username, password);
	}

}
