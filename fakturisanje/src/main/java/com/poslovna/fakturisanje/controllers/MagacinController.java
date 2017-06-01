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
import com.poslovna.fakturisanje.models.Magacin;
import com.poslovna.fakturisanje.services.CompanyService;
import com.poslovna.fakturisanje.services.MagacinService;

@RestController
public class MagacinController {

	@Autowired
	private MagacinService magacinService;
	
	@Autowired
	private CompanyService companyService;
	
	@RequestMapping(
            value    = "/api/magacin/findBySifra/{sifra}",
            method   = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
	public ResponseEntity<Magacin> findBySifra(@PathVariable String sifra) {
		Magacin magacin = magacinService.findBySifra(sifra);
        return new ResponseEntity<Magacin>(magacin, HttpStatus.OK);
    }
	
	@RequestMapping(
            value    = "/api/magacin/dodajMagacin/{firmaId}",
            method   = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
	public ResponseEntity<Magacin> dodajMagacin(@RequestBody Magacin magacin, @PathVariable Integer firmaId){
		Company firma = companyService.findOne(firmaId);
		magacin.setPreduzece(firma);
		Magacin m = magacinService.save(magacin);
		return new ResponseEntity<Magacin>(m, HttpStatus.OK);
	}
	
	@RequestMapping(
            value    = "/api/magacin/sviMagacini",
            method   = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
	public ResponseEntity<Collection<Magacin>> sviMagacini() {
		Collection<Magacin> magacini = magacinService.findAll();
		return new ResponseEntity<Collection<Magacin>>(magacini, HttpStatus.OK);
	}
	
}
