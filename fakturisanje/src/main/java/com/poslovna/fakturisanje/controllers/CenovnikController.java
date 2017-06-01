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

import com.poslovna.fakturisanje.models.Cenovnik;
import com.poslovna.fakturisanje.models.Company;
import com.poslovna.fakturisanje.services.CenovnikService;
import com.poslovna.fakturisanje.services.CompanyService;

@RestController
public class CenovnikController {
	
	@Autowired
	private CenovnikService cenovnikService;
	
	@Autowired
	private CompanyService companyService;
	
	@RequestMapping(
			value = "api/cenovnik/kreirajCenovnik/{idFirma}",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cenovnik> dodajCenovnik(@RequestBody Cenovnik cenovnik, @PathVariable Integer idFirma) {
		Company idCompany = companyService.findOne(idFirma);
		cenovnik.setCompany(idCompany);
		Cenovnik dodavanjeCenovnika= cenovnikService.add(cenovnik);
        return new ResponseEntity<Cenovnik>(dodavanjeCenovnika, HttpStatus.OK);
    }
	

}