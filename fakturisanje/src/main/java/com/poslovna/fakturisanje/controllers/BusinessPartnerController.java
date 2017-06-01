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

import com.poslovna.fakturisanje.models.BusinessPartner;
import com.poslovna.fakturisanje.models.Company;
import com.poslovna.fakturisanje.services.BusinessPartnerService;
import com.poslovna.fakturisanje.services.CompanyService;

@RestController
public class BusinessPartnerController {
	
	@Autowired
	private BusinessPartnerService businessPartnerService;
	
	@Autowired
	private CompanyService companyService;
	
	@RequestMapping(
            value    = "/api/company/findByCompany/{id}",
            method   = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Collection<BusinessPartner>> findAllById(@PathVariable Integer id) {
		Collection<BusinessPartner> allBusinessPartners = businessPartnerService.findByCompany1(id);
        return new ResponseEntity<Collection<BusinessPartner>>(allBusinessPartners, HttpStatus.OK);
    }
	
	@RequestMapping(
            value    = "/api/businessPartner/poslovniPartner/{company1Id}/{company2Id}",
            method   = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<BusinessPartner> kreirajPartnera(@RequestBody BusinessPartner bp ,@PathVariable Integer company1Id, @PathVariable Integer company2Id) {
		Company company1 = companyService.findOne(company1Id);
		bp.setCompany1(company1);
		Company company2 = companyService.findOne(company2Id);
		bp.setCompany2(company2);
		BusinessPartner kreiranjePartnera = businessPartnerService.save(bp);
		
		BusinessPartner bp2 = new BusinessPartner();
		bp2.setCompany1(company2);
		bp2.setCompany2(company1);
		BusinessPartner kreiranjepartnera2 = businessPartnerService.save(bp2);
        return new ResponseEntity<BusinessPartner>(kreiranjePartnera, HttpStatus.OK);
    }
	
	

}
