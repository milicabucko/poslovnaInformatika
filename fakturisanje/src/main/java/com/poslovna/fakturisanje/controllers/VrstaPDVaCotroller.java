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
import com.poslovna.fakturisanje.services.VrstaPDVaService;

@RestController
public class VrstaPDVaCotroller {
	
	@Autowired
	private VrstaPDVaService vrstaPDVaService;
	
	@RequestMapping(
            value    = "/api/vrste/nadjiSveVrste/",
            method   = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Collection<VrstaPDVa>> findAllStope() {
		Collection<VrstaPDVa> sveVrste = vrstaPDVaService.getAll();
        return new ResponseEntity<Collection<VrstaPDVa>>(sveVrste, HttpStatus.OK);
    }
	
	@RequestMapping(
			value = "api/vrste/kreirajVrstu/",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<VrstaPDVa> addStopu(@RequestBody VrstaPDVa vrstaPDVa) {
        VrstaPDVa vrsta = new VrstaPDVa();
        vrsta.setNazivVrstePDVa(vrstaPDVa.getNazivVrstePDVa());
        vrstaPDVaService.save(vrsta);
        return new ResponseEntity<VrstaPDVa>(vrsta, HttpStatus.OK);
    }

}
