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
import com.poslovna.fakturisanje.models.Magacin;
import com.poslovna.fakturisanje.models.MagacinskaKartica;
import com.poslovna.fakturisanje.services.ArtikalService;
import com.poslovna.fakturisanje.services.MagacinService;
import com.poslovna.fakturisanje.services.MagacinskaKarticaService;

@RestController
public class MagacinskaKarticaController {

	@Autowired
	private MagacinskaKarticaService magacinskaKarticaService;
	
	@Autowired
	private MagacinService magacinService;
	
	@Autowired
	private ArtikalService artikalService;
	
	@RequestMapping(
            value    = "/api/magacinskaKartica/nadjiSveKartice/{magacinId}",
            method   = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Collection<MagacinskaKartica>> nadjiSveKartice(@PathVariable Integer magacinId) {
		Magacin magacin = magacinService.findById(magacinId);
		Collection<MagacinskaKartica> kartice = magacinskaKarticaService.nadjiSveMagacinskeKartice(magacin);
        return new ResponseEntity<Collection<MagacinskaKartica>>(kartice, HttpStatus.OK);
    }
	
	@RequestMapping(
            value    = "/api/magacinskaKartica/nadjiKarticuArtikla/{magacinId}/{artikalSifra}",
            method   = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<MagacinskaKartica> nadjiKarticuArtikla(@PathVariable Integer magacinId, @PathVariable String artikalSifra) {
		Magacin magacin = magacinService.findById(magacinId);
		Artikal artikal = artikalService.findBySifra(artikalSifra);
		MagacinskaKartica kartica = magacinskaKarticaService.nadjiMagacinskuKarticuArtikla(magacin, artikal);
        return new ResponseEntity<MagacinskaKartica>(kartica, HttpStatus.OK);
    }
	
	
}
