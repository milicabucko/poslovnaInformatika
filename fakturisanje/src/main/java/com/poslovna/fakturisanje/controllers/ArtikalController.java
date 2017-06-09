package com.poslovna.fakturisanje.controllers;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.poslovna.fakturisanje.models.Artikal;
import com.poslovna.fakturisanje.models.GrupaArtikala;
import com.poslovna.fakturisanje.models.JedinicaMere;
import com.poslovna.fakturisanje.repositories.GrupaArtikalaRepository;
import com.poslovna.fakturisanje.repositories.JedinicaMereRepository;
import com.poslovna.fakturisanje.services.ArtikalService;

@RestController
public class ArtikalController {
	
	@Autowired
	private ArtikalService artikalService;
	
	@Autowired
	private GrupaArtikalaRepository grupaArtikalaRepository;
	
	@Autowired
	private JedinicaMereRepository jedinicaMereRepository;
	
	
	@RequestMapping(
            value    = "/api/artikal/findBySifra/{sifra}",
            method   = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Artikal> findBySifra(@PathVariable String sifra) {
		Artikal artikal = artikalService.findBySifra(sifra);
        return new ResponseEntity<Artikal>(artikal, HttpStatus.OK);
    }
	
	@RequestMapping(
            value    = "api/artikal/findByGrupaId/{id}",
            method   = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<HashSet<Artikal>> findByGrupaId(@PathVariable Integer id) {
		HashSet<Artikal> artikli = artikalService.findByGrupaArtikala(id);
        return new ResponseEntity<HashSet<Artikal>>(artikli, HttpStatus.OK);
    }
	
	@RequestMapping(
			value = "api/artikal/kreirajArtikal/",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Artikal> addArtikal(@RequestBody Artikal artikal, @RequestParam("idGrupe") Integer idGrupe, @RequestParam("idJedinice") Integer idJedinice) {
		GrupaArtikala grupa = grupaArtikalaRepository.findOne(idGrupe);
		JedinicaMere jm = jedinicaMereRepository.findOne(idJedinice);
		Artikal a = new Artikal();
		a.setNaziv(artikal.getNaziv());
		a.setOpis(artikal.getOpis());
		a.setSifra(artikal.getSifra());
		a.setVrsta(artikal.getVrsta());
		a.setGrupaArtikala(grupa);
		a.setJedinicaMere(jm);
		artikalService.saveArtikal(a);
        return new ResponseEntity<Artikal>(a, HttpStatus.OK);
    }

}
