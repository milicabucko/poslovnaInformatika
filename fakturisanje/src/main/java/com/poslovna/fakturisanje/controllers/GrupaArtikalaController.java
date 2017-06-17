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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.poslovna.fakturisanje.models.Cenovnik;
import com.poslovna.fakturisanje.models.Company;
import com.poslovna.fakturisanje.models.GrupaArtikala;
import com.poslovna.fakturisanje.models.VrstaPDVa;
import com.poslovna.fakturisanje.repositories.CompanyRepository;
import com.poslovna.fakturisanje.repositories.GrupaArtikalaRepository;
import com.poslovna.fakturisanje.repositories.VrstaPDVaRepository;
import com.poslovna.fakturisanje.services.GrupaAtrikalaService;

@RestController
public class GrupaArtikalaController {
	
	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private GrupaAtrikalaService grupaAtrikalaService;
	
	@Autowired
	private GrupaArtikalaRepository grupaArtikalaRepository;
	
	@Autowired
	private VrstaPDVaRepository vrstaPDVaRepository;
	
	@RequestMapping(
			value = "api/grupe/kreirajGrupu/",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GrupaArtikala> addGrupu(@RequestBody GrupaArtikala grupaArtikala, @RequestParam("id") Integer id, @RequestParam("vrstaId") Integer vrstaId) {
        GrupaArtikala grupa = new GrupaArtikala();
        Company firma = companyRepository.findOne(id);
        grupa.setCompany(firma);
        grupa.setNazivGrupe(grupaArtikala.getNazivGrupe());
        VrstaPDVa vrstaPDVa = vrstaPDVaRepository.findOne(vrstaId);
        grupa.setVrstaPDVa(vrstaPDVa);
        grupaAtrikalaService.add(grupa);
        return new ResponseEntity<GrupaArtikala>(grupa, HttpStatus.OK);
    }
	
	@RequestMapping(
            value    = "api/grupe/nadjiSveGrupe/{id}",
            method   = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Collection<GrupaArtikala>> findByCompany(@PathVariable Integer id) {
		Company company = companyRepository.findOne(id);
		Collection<GrupaArtikala> sveGrupe = grupaAtrikalaService.findByCompany(company);
        return new ResponseEntity<Collection<GrupaArtikala>>(sveGrupe, HttpStatus.OK);
    }
	
	@RequestMapping(
            value    = "api/grupe/obrisiGrupu/{id}",
            method   = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Collection<GrupaArtikala>> deleteGrupu(@PathVariable Integer id) {
		GrupaArtikala grupa = grupaAtrikalaService.findOne(id);
		grupaAtrikalaService.remove(grupa);
		//Collection<GrupaArtikala> sveGrupe = grupaAtrikalaService.findByCompany(company);
		Collection<GrupaArtikala> sveGrupe = grupaArtikalaRepository.findAll();
        return new ResponseEntity<Collection<GrupaArtikala>>(sveGrupe, HttpStatus.OK);
    }
	
	@RequestMapping(
            value    = "api/grupe/nadjiGrupu/{id}",
            method   = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<GrupaArtikala> findOneGrupu(@PathVariable Integer id) {
		GrupaArtikala grupa = grupaAtrikalaService.findOne(id);
        return new ResponseEntity<GrupaArtikala> (grupa, HttpStatus.OK);
    }
	
	@RequestMapping(
			value = "api/grupe/promeniGrupu/{vrstaId}",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GrupaArtikala> updateGrupu(@RequestBody GrupaArtikala grupaArtikala, @PathVariable Integer vrstaId) {
        GrupaArtikala grupa = grupaAtrikalaService.findOne(grupaArtikala.getId());
        grupa.setNazivGrupe(grupaArtikala.getNazivGrupe());
        VrstaPDVa vrstaPDVa = vrstaPDVaRepository.findOne(vrstaId);
        grupa.setVrstaPDVa(vrstaPDVa);
        grupaAtrikalaService.add(grupa);
        return new ResponseEntity<GrupaArtikala>(grupa, HttpStatus.OK);
    }
	
	

}
