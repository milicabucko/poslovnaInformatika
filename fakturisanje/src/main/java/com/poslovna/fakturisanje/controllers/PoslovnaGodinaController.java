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
			novaGodina.setDatumPocetka(godina.getDatumPocetka().substring(0, 10));
			novaGodina.setDatumZavrsetka(godina.getDatumZavrsetka().substring(0, 10));
			novaGodina.setPreduzece(company);
			poslovnaGodinaService.sacuvajGodinu(novaGodina);
			return new ResponseEntity<PoslovnaGodina>(novaGodina, HttpStatus.OK);
		}else{
			godinica.setBrojGodine(0000);
			return new ResponseEntity<PoslovnaGodina>(godinica, HttpStatus.OK);
		}
	}
	
	@RequestMapping(
			value = "api/poslovnaGodina/sveGodine",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<PoslovnaGodina>> sveGodine() {
		return new ResponseEntity<Collection<PoslovnaGodina>>(poslovnaGodinaService.findAll(), HttpStatus.OK); 
	}
	
	@RequestMapping(
			value = "api/poslovnaGodina/promenaStatusa/{idFirma}/{brojGodine}",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PoslovnaGodina> sacuvajGodinu(@PathVariable Integer idFirma, @PathVariable Integer brojGodine){
		Company company = companyService.findOne(idFirma);
		PoslovnaGodina godina = poslovnaGodinaService.findByPreduzeceAndBrojGodine(company, brojGodine);
		Collection<PoslovnaGodina> godine = poslovnaGodinaService.findAllByPreduzece(company);
		
		int brojAktivnih = 0;
		for (PoslovnaGodina poslovnaGodina : godine) {
			if(poslovnaGodina.getAktivna() == true){
				brojAktivnih++;
			}
		}
		if(brojAktivnih == 0){
			godina.setAktivna(true);
			poslovnaGodinaService.sacuvajGodinu(godina);
			return new ResponseEntity<PoslovnaGodina>(godina, HttpStatus.OK);
		}else if((brojAktivnih == 1) && (godina.getAktivna() == true)){
			godina.setAktivna(false);
			poslovnaGodinaService.sacuvajGodinu(godina);
			return new ResponseEntity<PoslovnaGodina>(godina, HttpStatus.OK);
		}else{
			godina.setBrojGodine(0000);
			return new ResponseEntity<PoslovnaGodina>(godina, HttpStatus.OK);
		}
	}
}
