package com.poslovna.fakturisanje.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.poslovna.fakturisanje.models.Artikal;
import com.poslovna.fakturisanje.models.Cenovnik;
import com.poslovna.fakturisanje.models.Dokument;
import com.poslovna.fakturisanje.models.StavkaCenovnika;
import com.poslovna.fakturisanje.models.StavkaDokumenta;
import com.poslovna.fakturisanje.services.ArtikalService;
import com.poslovna.fakturisanje.services.CenovnikService;
import com.poslovna.fakturisanje.services.StavkaCenovnikaService;
import com.poslovna.fakturisanje.services.StavkaDokumentaService;

@RestController
public class StavkaCenovnikaController {
	
	@Autowired
	private StavkaCenovnikaService stavkaCenovnikaService;
	
	@Autowired
	private CenovnikService cenovnikService;
	
	@Autowired 
	private ArtikalService artikalService;
	
	@RequestMapping(
            value    = "/api/stavkaCenovnika/sacuvajStavku/{cenovnikId}/{artikalId}",
            method   = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<StavkaCenovnika> sacuvajStavku(@RequestBody StavkaCenovnika stavkaCenovnika, @PathVariable Integer cenovnikId, @PathVariable Integer artikalId) {
		Cenovnik cenovnik = cenovnikService.findOne(cenovnikId);
		Artikal artikal = artikalService.findOne(artikalId);
		stavkaCenovnika.setCenovnik(cenovnik);
		stavkaCenovnika.setArtikal(artikal);
		StavkaCenovnika dodajstavkaCenovnika = stavkaCenovnikaService.save(stavkaCenovnika);
        return new ResponseEntity<StavkaCenovnika>(dodajstavkaCenovnika, HttpStatus.OK);
	}

}
