package com.poslovna.fakturisanje.controllers;

import java.util.Collection;

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
	

	@RequestMapping(
            value    = "/api/faktura/sveFakture",
            method   = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Collection<Faktura>> sveFakture() {
		Collection<Faktura> sveFakture = fakturaService.getAll();
        return new ResponseEntity<Collection<Faktura>>(sveFakture, HttpStatus.OK);
    }
	
	@RequestMapping(
            value    = "/api/faktura/promeniStatusDokumenta/{fakturaId}/{status}",
            method   = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Integer> promeniStatusDokumenta(@PathVariable Integer fakturaId, @PathVariable String status) {
		Integer st = fakturaService.setStatusDokumentaForFaktura(status, fakturaId);
        return new ResponseEntity<Integer>(st, HttpStatus.OK);
    }
	
	@RequestMapping(
            value    = "/api/faktura/nadjiPoBrojuDokumenta/{brojDokumenta}",
            method   = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Collection<Faktura>> nadjiPoBrojuDokumenta(@PathVariable Integer brojDokumenta) {
		Collection<Faktura> faktura = fakturaService.findByBrojDokumenta(brojDokumenta);
        return new ResponseEntity<Collection<Faktura>>(faktura, HttpStatus.OK);
    }
	
	
	
	
}
