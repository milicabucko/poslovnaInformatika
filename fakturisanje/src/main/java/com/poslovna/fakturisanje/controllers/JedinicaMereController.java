package com.poslovna.fakturisanje.controllers;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.poslovna.fakturisanje.models.Artikal;
import com.poslovna.fakturisanje.models.Company;
import com.poslovna.fakturisanje.models.GrupaArtikala;
import com.poslovna.fakturisanje.models.JedinicaMere;
import com.poslovna.fakturisanje.repositories.CompanyRepository;
import com.poslovna.fakturisanje.repositories.JedinicaMereRepository;
import com.poslovna.fakturisanje.services.ArtikalService;
import com.poslovna.fakturisanje.services.GrupaAtrikalaService;

@RestController
public class JedinicaMereController {
	
	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private GrupaAtrikalaService grupaArtikalaService;
	
	@Autowired
	private ArtikalService artikalService;
	
	@Autowired
	private JedinicaMereRepository jedinicaMereRepository;
	
	@RequestMapping(
            value    = "api/jedinice/nadjiSveJedinice/{id}",
            method   = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Collection<JedinicaMere>> getByCompany(@PathVariable Integer id) {
		Company company = companyRepository.findOne(id);
		Collection<GrupaArtikala> sveGrupe = grupaArtikalaService.findByCompany(company);
		HashSet<Artikal> sviArtikli = new HashSet<>();
		sveGrupe.forEach(grupa -> sviArtikli.addAll(artikalService.findByGrupaArtikala(grupa.getId())));
		Set<JedinicaMere> sveJedinice = new HashSet<JedinicaMere>();
		sviArtikli.forEach(artikal -> sveJedinice.add(artikal.getJedinicaMere()));
        return new ResponseEntity<Collection<JedinicaMere>>(sveJedinice, HttpStatus.OK);
    }
	
	@RequestMapping(
			value = "api/jedinice/kreirajJedinicu/",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JedinicaMere> addGrupu(@RequestBody JedinicaMere jedinicaMere) {
        JedinicaMere jm = new JedinicaMere();
        jm.setNazivJedinice(jedinicaMere.getNazivJedinice());
        jm.setOznakaJedinice(jedinicaMere.getOznakaJedinice());
        jedinicaMereRepository.save(jm);
        return new ResponseEntity<JedinicaMere>(jm, HttpStatus.OK);
    }

}
