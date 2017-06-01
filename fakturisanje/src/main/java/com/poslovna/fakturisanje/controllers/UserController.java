package com.poslovna.fakturisanje.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.poslovna.fakturisanje.models.Korisnik;
import com.poslovna.fakturisanje.services.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(
			value = "/api/users/findByUsername/{username}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Korisnik> findByUsername(@PathVariable String username){
		Korisnik user = userService.findByUsername(username);
		return new ResponseEntity<Korisnik>(user,HttpStatus.OK);
	}
	

}
