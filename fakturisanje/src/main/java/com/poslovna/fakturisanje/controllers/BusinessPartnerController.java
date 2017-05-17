package com.poslovna.fakturisanje.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.poslovna.fakturisanje.models.BusinessPartner;
import com.poslovna.fakturisanje.services.BusinessPartnerService;

@RestController
public class BusinessPartnerController {
	
	@Autowired
	private BusinessPartnerService businessPartnerService;
	
	@RequestMapping(
            value    = "/api/company/findByCompany/{id}",
            method   = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Collection<BusinessPartner>> findAllById(@PathVariable Integer id) {
		Collection<BusinessPartner> allBusinessPartners = businessPartnerService.findByCompany(id);
        return new ResponseEntity<Collection<BusinessPartner>>(allBusinessPartners, HttpStatus.OK);
    }

}
