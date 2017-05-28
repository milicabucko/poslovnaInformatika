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

import com.poslovna.fakturisanje.models.BusinessPartner;
import com.poslovna.fakturisanje.models.Company;
import com.poslovna.fakturisanje.models.Faktura;
import com.poslovna.fakturisanje.services.BusinessPartnerService;
import com.poslovna.fakturisanje.services.CompanyService;
import com.poslovna.fakturisanje.services.FakturaService;

@RestController
public class FakturaController {

	@Autowired
	private FakturaService fakturaService;
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private BusinessPartnerService businessPartnerService;
	
	@RequestMapping(
            value    = "/api/faktura/nadjiSledeciBrojDokumenta",
            method   = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Integer> findBySifra() {
		Integer brojDok = fakturaService.nadjiSlececiBrojDokumenta();
        return new ResponseEntity<Integer>(brojDok, HttpStatus.OK);
    }
	
	
	@RequestMapping(
            value    = "/api/faktura/sacuvajFakturu/{izdId}/{kupId}",
            method   = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Faktura> sacuvajFakturu(@RequestBody Faktura faktura, @PathVariable Integer izdId, @PathVariable Integer kupId) {
		Company izdavaoc = companyService.findOne(izdId);
		BusinessPartner kupac = businessPartnerService.findOne(kupId);
		faktura.setIzdavaocRacuna(izdavaoc);
		faktura.setKupac(kupac);
		Faktura fakturaa = fakturaService.save(faktura);
        return new ResponseEntity<Faktura>(fakturaa, HttpStatus.OK);
    }
	
	
}
