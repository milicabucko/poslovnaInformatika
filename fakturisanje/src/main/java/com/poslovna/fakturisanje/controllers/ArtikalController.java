package com.poslovna.fakturisanje.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.poslovna.fakturisanje.models.Artikal;
import com.poslovna.fakturisanje.services.ArtikalService;

@RestController
public class ArtikalController {
	
	@Autowired
	private ArtikalService artikalService;
	
	@RequestMapping(
            value    = "/api/artikal/findBySifra/{sifra}",
            method   = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Artikal> findBySifra(@PathVariable String sifra) {
		Artikal artikal = artikalService.findBySifra(sifra);
        return new ResponseEntity<Artikal>(artikal, HttpStatus.OK);
    }

}
