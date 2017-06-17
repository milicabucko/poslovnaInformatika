package com.poslovna.fakturisanje.controllers;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
import com.poslovna.fakturisanje.models.JedinicaMere;
import com.poslovna.fakturisanje.repositories.CompanyRepository;
import com.poslovna.fakturisanje.repositories.JedinicaMereRepository;

@RestController
public class JedinicaMereController {
	
	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private JedinicaMereRepository jedinicaMereRepository;
	
	@RequestMapping(
            value    = "api/jedinice/nadjiSveJedinice/{id}",
            method   = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Collection<JedinicaMere>> getByCompany(@PathVariable Integer id) {
		//Company company = companyRepository.findOne(id);
		//Collection<GrupaArtikala> sveGrupe = grupaArtikalaService.findByCompany(company);
		//HashSet<Artikal> sviArtikli = new HashSet<>();
		//sveGrupe.forEach(grupa -> sviArtikli.addAll(artikalService.findByGrupaArtikala(grupa.getId())));
		List<JedinicaMere> sveJedinice = jedinicaMereRepository.findByCompanyId(id);
		//Set<JedinicaMere> jedinice = new HashSet<>(sveJedinice);
		Set<JedinicaMere> proba = sveJedinice.stream().collect(Collectors.toSet());
		//sviArtikli.forEach(artikal -> sveJedinice.add(artikal.getJedinicaMere()));
        return new ResponseEntity<Collection<JedinicaMere>>(proba, HttpStatus.OK);
    }
	
	@RequestMapping(
			value = "api/jedinice/kreirajJedinicu/{id}",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JedinicaMere> addJedinicu(@RequestBody JedinicaMere jedinicaMere, @PathVariable Integer id) {
        JedinicaMere jm = new JedinicaMere();
        Company company = companyRepository.findOne(id);
        jm.setNazivJedinice(jedinicaMere.getNazivJedinice());
        jm.setOznakaJedinice(jedinicaMere.getOznakaJedinice());
        jm.setCompany(company);
        jedinicaMereRepository.save(jm);
        return new ResponseEntity<JedinicaMere>(jm, HttpStatus.OK);
    }
	
	@RequestMapping(
            value    = "api/jedinice/obrisiJedinicu/{id}",
            method   = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Collection<JedinicaMere>> deleteJedinicu(@PathVariable Integer id) {
		JedinicaMere jedinica = jedinicaMereRepository.findOne(id);
		Company c = jedinica.getCompany();
		jedinicaMereRepository.delete(jedinica);
		List<JedinicaMere> sveJedinice = jedinicaMereRepository.findByCompanyId(c.getId());
		Set<JedinicaMere> ostaleJedinice = sveJedinice.stream().collect(Collectors.toSet());
        return new ResponseEntity<Collection<JedinicaMere>>(ostaleJedinice, HttpStatus.OK);
    }
	
	@RequestMapping(
            value    = "api/jedinice/nadjiJednu/{id}",
            method   = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
	public ResponseEntity<JedinicaMere> findJedinicu(@PathVariable Integer id){
		JedinicaMere jedinica = jedinicaMereRepository.findOne(id);
		return new ResponseEntity<JedinicaMere>(jedinica, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "api/jedinice/promeniJedinicu/",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JedinicaMere> updateJedinicu(@RequestBody JedinicaMere jedinicaMere) {
        JedinicaMere jm = jedinicaMereRepository.findOne(jedinicaMere.getId());
        jm.setNazivJedinice(jedinicaMere.getNazivJedinice());
        jm.setOznakaJedinice(jedinicaMere.getOznakaJedinice());
        jedinicaMereRepository.save(jm);
        return new ResponseEntity<JedinicaMere>(jm, HttpStatus.OK);
    }

}
