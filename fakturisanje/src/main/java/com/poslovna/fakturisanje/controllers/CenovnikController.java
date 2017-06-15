package com.poslovna.fakturisanje.controllers;


import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

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
		cenovnik.setAktivan(false);
		cenovnik.setDatumVazenjaPocetak(cenovnik.getDatumVazenjaPocetak().substring(0, 10));
		
		if(cenovnikService.findByCompanyAndDatumVazenjaPocetak(company, cenovnik.getDatumVazenjaPocetak()).size() != 0 ){
			return new ResponseEntity<Cenovnik>(new Cenovnik(), HttpStatus.OK);
		}
		
		
	/*	if(cenovnikService.findByCompanyAndDatumVazenjaPocetakBetween(company, cenovnik.getDatumVazenjaPocetak(), cenovnik.getDatumVazenjaKraj()).size() != 0){
			
			
		}else if(cenovnikService.findByCompanyAndDatumVazenjaKrajBetween(company, cenovnik.getDatumVazenjaPocetak(), cenovnik.getDatumVazenjaKraj()).size() != 0){
			return new ResponseEntity<Cenovnik>(new Cenovnik(), HttpStatus.OK);
		}*/
		
		
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
    public ResponseEntity<Cenovnik> nadjiPoslednjiAktivan(@PathVariable BigInteger companyPib, @PathVariable String datum) {
		Company company = companyService.findByPib(companyPib);
		Cenovnik cenovnik = cenovnikService.nadjiPoslednjiAktivniCenovnik(company, true);
        return new ResponseEntity<Cenovnik>(cenovnik, HttpStatus.OK);
    }
	
	@RequestMapping(
            value    = "/api/cenovnik/posaljiDatum/{companyPib}/{datum}",
            method   = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Cenovnik> nadjiAktivan(@PathVariable BigInteger companyPib, @PathVariable String datum) {
		Company company = companyService.findByPib(companyPib);
		//datum = datum.substring(0,16);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date datumZaFormat= new Date(datum);
		
		String datumDok = format.format(datumZaFormat);
		System.out.println(datumDok);
		
		Collection<Cenovnik> cenovnici = cenovnikService.findByCompanyAndDatumVazenjaPocetakLessThanOrderByDatumVazenjaPocetakDesc(company, datumDok);
		ArrayList<Cenovnik> cenovniciNiz = (ArrayList<Cenovnik>) cenovnici;
		
		System.out.println(cenovniciNiz);
		
		if(cenovniciNiz.size() != 0){
			return new ResponseEntity<Cenovnik>(cenovniciNiz.get(0), HttpStatus.OK);
		}
        return new ResponseEntity<Cenovnik>(new Cenovnik(), HttpStatus.OK);
    }
	

	
}
	
