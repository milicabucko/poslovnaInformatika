package com.poslovna.fakturisanje.controllers;

import java.math.BigInteger;
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
import com.poslovna.fakturisanje.services.CompanyService;

@RestController
public class CompanyController {
	
	@Autowired
	private CompanyService companyService;
	
	@RequestMapping(
			value = "/api/company/addCompany",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Company> addCompany(@RequestBody Company company) {
		
		Company proveraCompany = new Company();
		
		if(companyService.findByPib(company.getPib())!= null){
			proveraCompany.setPib(BigInteger.valueOf(-1)); 
		}
		else {
			proveraCompany.setPib(BigInteger.valueOf(-2));
		}
		
		if(companyService.findByCidnumber(company.getCidnumber()) != null){
			proveraCompany.setCidnumber(BigInteger.valueOf(-1));
		}
		else {
			proveraCompany.setCidnumber(BigInteger.valueOf(-2));
		}
		
		if(companyService.findByAccount(company.getAccount()) != null) {
			proveraCompany.setAccount("-1");
		}
		else {
			proveraCompany.setAccount("-2");
		}
		
		if (proveraCompany.getPib() == BigInteger.valueOf(-1) || proveraCompany.getCidnumber() == BigInteger.valueOf(-1) || proveraCompany.getAccount().equals("-1")) {
			 return new ResponseEntity<Company>(proveraCompany, HttpStatus.OK);
		}
		
        Company addingCompany= companyService.add(company);
        return new ResponseEntity<Company>(addingCompany, HttpStatus.OK);
    }
	
	@RequestMapping(
            value    = "/api/company/getAllCompanies",
            method   = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Collection<Company>> getCompanies() {
		Collection<Company> allCompanies = companyService.getAll();
        return new ResponseEntity<Collection<Company>>(allCompanies, HttpStatus.OK);
    }
	
	@RequestMapping(
            value    = "/api/company/findByIdNot/{id}",
            method   = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Collection<Company>> findAllByIdNot(@PathVariable Integer id) {
		Collection<Company> allCompanies = companyService.findByIdNot(id);
        return new ResponseEntity<Collection<Company>>(allCompanies, HttpStatus.OK);
    }
	
	@RequestMapping(
            value    = "/api/company/findByNameContaining/{name}",
            method   = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Collection<Company>> findByNameContaining(@PathVariable String name) {
		Collection<Company> allCompanies = companyService.findByNameContaining(name);
        return new ResponseEntity<Collection<Company>>(allCompanies, HttpStatus.OK);
    }
	
	@RequestMapping(
            value    = "/api/company/findById/{id}",
            method   = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Company> findById(@PathVariable Integer id) {
		Company company = companyService.findOne(id);
        return new ResponseEntity<Company>(company, HttpStatus.OK);
    }
	
	

}
