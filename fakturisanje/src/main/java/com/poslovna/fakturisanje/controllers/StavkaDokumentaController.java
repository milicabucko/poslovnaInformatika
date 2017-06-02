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
import com.poslovna.fakturisanje.models.BusinessPartner;
import com.poslovna.fakturisanje.models.Company;
import com.poslovna.fakturisanje.models.Dokument;
import com.poslovna.fakturisanje.models.StavkaDokumenta;
import com.poslovna.fakturisanje.services.ArtikalService;
import com.poslovna.fakturisanje.services.DokumentService;
import com.poslovna.fakturisanje.services.StavkaDokumentaService;

@RestController
public class StavkaDokumentaController {

	@Autowired
	private StavkaDokumentaService stavkaDokumentaService;
	
	@Autowired
	private DokumentService fakturaService;
	
	@Autowired
	private ArtikalService artikalService;
	
	

	@RequestMapping(
            value    = "/api/stavkaDokumenta/sacuvajStavku/{fakturaId}/{artikalId}",
            method   = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<StavkaDokumenta> sacuvajStavku(@RequestBody StavkaDokumenta stavkaDokumenta, @PathVariable Integer fakturaId, @PathVariable Integer artikalId) {
		Dokument faktura = fakturaService.findOne(fakturaId);
		Artikal artikal = artikalService.findOne(artikalId);
		stavkaDokumenta.setDokument(faktura);
		stavkaDokumenta.setArtikal(artikal);
		StavkaDokumenta stavkaDokumentaa = stavkaDokumentaService.save(stavkaDokumenta);
        return new ResponseEntity<StavkaDokumenta>(stavkaDokumentaa, HttpStatus.OK);
	}
	
	
}
