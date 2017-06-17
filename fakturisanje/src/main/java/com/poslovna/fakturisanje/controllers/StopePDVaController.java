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
import com.poslovna.fakturisanje.services.StopaPDVaService;

@RestController
public class StopePDVaController {
	
	@Autowired
	private StopaPDVaService stopaPDVaService;
	
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
			value = "api/stope/kreirajStopu/",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StopaPDVa> addStopu(@RequestBody StopaPDVa stopaPDVa) {
        StopaPDVa novaStopa = new StopaPDVa();
        //VrstaPDVa vrsta = vrstaPDVaRepository.findOne(id);
       // novaStopa.setVrstaPDVa(vrsta);
        novaStopa.setDatumStope(stopaPDVa.getDatumStope());
        novaStopa.setProcenatPDVa(stopaPDVa.getProcenatPDVa());
        //System.out.println(novaStopa.getVrstaPDVa());
        stopaPDVaService.save(novaStopa);
        return new ResponseEntity<StopaPDVa>(novaStopa, HttpStatus.OK);
    }
	
	@RequestMapping(
            value    = "api/stope/nadjiStopu/{id}",
            method   = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<StopaPDVa> findStopu(@PathVariable Integer id) {
		StopaPDVa stopa = stopaPDVaService.findStopu(id);
        return new ResponseEntity<StopaPDVa>(stopa, HttpStatus.OK);
    }
	
	@RequestMapping(
			value = "api/stope/obrisiStopu/{id}",
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<StopaPDVa>> removeStopa(@PathVariable Integer id){
		StopaPDVa stopa = stopaPDVaService.findStopu(id);
		stopaPDVaService.deleteStopu(stopa);
		Collection<StopaPDVa> sveStope = stopaPDVaService.getAll();
		return new ResponseEntity<Collection<StopaPDVa>>(sveStope, HttpStatus.OK);
	}
	
	
	@RequestMapping(
			value = "api/stope/izmeniStopu/",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StopaPDVa> updateStopu(@RequestBody StopaPDVa stopaPDVa) {
        StopaPDVa novaStopa = stopaPDVaService.findStopu(stopaPDVa.getId());
        //VrstaPDVa vrsta = vrstaPDVaRepository.findOne(id);
       // novaStopa.setVrstaPDVa(vrsta);
        novaStopa.setDatumStope(stopaPDVa.getDatumStope());
        novaStopa.setProcenatPDVa(stopaPDVa.getProcenatPDVa());
        //System.out.println(novaStopa.getVrstaPDVa());
        stopaPDVaService.save(novaStopa);
        return new ResponseEntity<StopaPDVa>(novaStopa, HttpStatus.OK);
    }

}
