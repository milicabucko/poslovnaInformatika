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
		Company company = companyService.findOne(idFirma);
		cenovnik.setCompany(company);
		
		if(cenovnikService.findByCompanyAndDatumVazenjaPocetakBetween(company, cenovnik.getDatumVazenjaPocetak(), cenovnik.getDatumVazenjaKraj()).size() != 0){
			return new ResponseEntity<Cenovnik>(new Cenovnik(), HttpStatus.OK);
			
		}else if(cenovnikService.findByCompanyAndDatumVazenjaKrajBetween(company, cenovnik.getDatumVazenjaPocetak(), cenovnik.getDatumVazenjaKraj()).size() != 0){
			return new ResponseEntity<Cenovnik>(new Cenovnik(), HttpStatus.OK);
		}
		
		
		Cenovnik dodavanjeCenovnika= cenovnikService.add(cenovnik);
        return new ResponseEntity<Cenovnik>(dodavanjeCenovnika, HttpStatus.OK);
    }
	
	@RequestMapping(
            value    = "/api/cenovnik/findByCompany/{id}",
            method   = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Collection<Cenovnik>> findByCompany(@PathVariable Integer id) {
		Company company = companyService.findOne(id);
		Collection<Cenovnik> sviCenovnici = cenovnikService.findByCompany(company);
        return new ResponseEntity<Collection<Cenovnik>>(sviCenovnici, HttpStatus.OK);
    }
	
	@RequestMapping(
            value    = "/api/cenovnik/nadjiPoslednjiAktivan/{companyPib}/{datum}",
            method   = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Cenovnik> nadjiPoslednjiAktivan(@PathVariable Integer companyPib, @PathVariable String datum) {
		Company company = companyService.findByPib(companyPib);
		Cenovnik cenovnik = cenovnikService.nadjiPoslednjiAktivniCenovnik(company, true);
        return new ResponseEntity<Cenovnik>(cenovnik, HttpStatus.OK);
    }
	
	

}
