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

import com.poslovna.fakturisanje.models.StopaPDVa;
import com.poslovna.fakturisanje.models.VrstaPDVa;
import com.poslovna.fakturisanje.repositories.VrstaPDVaRepository;
import com.poslovna.fakturisanje.services.StopaPDVaService;
import com.poslovna.fakturisanje.services.VrstaPDVaService;

@RestController
public class StopePDVaController {
	
	@Autowired
	private StopaPDVaService stopaPDVaService;
	
	@Autowired
	private VrstaPDVaRepository vrstaPDVaRepository;
	
	@RequestMapping(
            value    = "/api/stope/nadjiSveStope/",
            method   = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Collection<StopaPDVa>> findAllStope() {
		Collection<StopaPDVa> sveStope = stopaPDVaService.getAll();
        return new ResponseEntity<Collection<StopaPDVa>>(sveStope, HttpStatus.OK);
    }
	
	@RequestMapping(
			value = "api/stope/kreirajStopu/{id}",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StopaPDVa> addStopu(@RequestBody StopaPDVa stopaPDVa, @PathVariable Integer id) {
        StopaPDVa novaStopa = new StopaPDVa();
        VrstaPDVa vrsta = vrstaPDVaRepository.findOne(id);
        novaStopa.setVrstaPDVa(vrsta);
        novaStopa.setDatumStope(stopaPDVa.getDatumStope());
        novaStopa.setProcenatPDVa(stopaPDVa.getProcenatPDVa());
        System.out.println(novaStopa.getVrstaPDVa());
        stopaPDVaService.save(novaStopa);
        return new ResponseEntity<StopaPDVa>(novaStopa, HttpStatus.OK);
    }

}
