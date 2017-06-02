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

import com.poslovna.fakturisanje.models.AnalitikaMagacinskeKartice;
import com.poslovna.fakturisanje.models.Artikal;
import com.poslovna.fakturisanje.models.Company;
import com.poslovna.fakturisanje.models.Magacin;
import com.poslovna.fakturisanje.models.MagacinskaKartica;
import com.poslovna.fakturisanje.models.StavkaDokumenta;
import com.poslovna.fakturisanje.services.AnalitikaMagacinskeKarticeService;
import com.poslovna.fakturisanje.services.ArtikalService;
import com.poslovna.fakturisanje.services.CompanyService;
import com.poslovna.fakturisanje.services.MagacinService;
import com.poslovna.fakturisanje.services.MagacinskaKarticaService;
import com.poslovna.fakturisanje.services.StavkaDokumentaService;

@RestController
public class AnalitikaMagacinskeKarticeController {

	@Autowired
	private AnalitikaMagacinskeKarticeService amkService;
	
	@Autowired
	private MagacinskaKarticaService mkService;
	
	@Autowired
	private StavkaDokumentaService stavkaDokumentaService;
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private MagacinService magacinService;
	
	@Autowired
	private ArtikalService artikalService;
	
	
	@RequestMapping(
			value = "/api/amk/addAMK/{artikalId}/{pib}/{stavkaId}",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AnalitikaMagacinskeKartice> addAMK(@RequestBody AnalitikaMagacinskeKartice amk, @PathVariable Integer artikalId, @PathVariable Integer pib, @PathVariable Integer stavkaId) {
		Company company = companyService.findByPib(pib);
		Magacin magacin  = magacinService.findByPreduzece(company);
		Artikal artikal = artikalService.findOne(artikalId);
		MagacinskaKartica magacinskaKartica = mkService.nadjiMagacinskuKarticuArtikla(magacin, artikal);
		amk.setMagacinskaKartica(magacinskaKartica);
		StavkaDokumenta stavkaDokumenta = stavkaDokumentaService.findOne(stavkaId);
		amk.setStavkaDokumenta(stavkaDokumenta);
		AnalitikaMagacinskeKartice amk1 = amkService.save(amk);
        return new ResponseEntity<AnalitikaMagacinskeKartice>(amk1, HttpStatus.OK);
    }
	
}
