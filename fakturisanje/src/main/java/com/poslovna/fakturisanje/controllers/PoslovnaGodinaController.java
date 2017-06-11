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

import com.poslovna.fakturisanje.models.Company;
import com.poslovna.fakturisanje.models.PoslovnaGodina;
import com.poslovna.fakturisanje.services.CompanyService;
import com.poslovna.fakturisanje.services.PoslovnaGodinaService;

@RestController
public class PoslovnaGodinaController {

	@Autowired
	private PoslovnaGodinaService poslovnaGodinaService;
	
	@Autowired
	private CompanyService companyService;
	
	@RequestMapping(
			value = "api/poslovnaGodina/sacuvajGodinu/{idFirma}",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PoslovnaGodina> sacuvajGodinu(@RequestBody PoslovnaGodina godina, @PathVariable Integer idFirma) {
		//System.out.println(idFirma);
		Company company = companyService.findOne(idFirma);
		PoslovnaGodina godinica = poslovnaGodinaService.findByPreduzeceAndBrojGodine(company, godina.getBrojGodine());
		
		if(godinica == null){
			PoslovnaGodina novaGodina = new PoslovnaGodina();
			novaGodina.setAktivna(false);
			novaGodina.setBrojGodine(godina.getBrojGodine());
			novaGodina.setDatumPocetka(godina.getDatumPocetka());
			novaGodina.setDatumZavrsetka(godina.getDatumZavrsetka());
			novaGodina.setPreduzece(company);
			poslovnaGodinaService.sacuvajGodinu(novaGodina);
			return new ResponseEntity<PoslovnaGodina>(novaGodina, HttpStatus.OK);
		}
		return new ResponseEntity<PoslovnaGodina>(godinica, HttpStatus.OK);
	}
}
