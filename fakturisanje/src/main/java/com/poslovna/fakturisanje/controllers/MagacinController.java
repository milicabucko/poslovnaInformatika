package com.poslovna.fakturisanje.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lowagie.text.pdf.codec.Base64.InputStream;
import com.lowagie.text.pdf.codec.Base64.OutputStream;
import com.poslovna.fakturisanje.models.Company;
import com.poslovna.fakturisanje.models.Magacin;
import com.poslovna.fakturisanje.services.CompanyService;
import com.poslovna.fakturisanje.services.MagacinService;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@RestController
public class MagacinController {

	@Autowired
	private MagacinService magacinService;
	
	@Autowired
	private CompanyService companyService;
	
	@RequestMapping(
            value    = "/api/magacin/findBySifra/{sifra}",
            method   = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
	public ResponseEntity<Magacin> findBySifra(@PathVariable String sifra) {
		Magacin magacin = magacinService.findBySifra(sifra);
        return new ResponseEntity<Magacin>(magacin, HttpStatus.OK);
    }
	
	@RequestMapping(
            value    = "/api/magacin/findBySifraContaining/{sifra}",
            method   = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Collection<Magacin>> findBySifraContaining(@PathVariable String sifra) {
		Collection<Magacin> magacini = magacinService.findBySifraContaining(sifra);
        return new ResponseEntity<Collection<Magacin>>(magacini, HttpStatus.OK);
    }
	
	@RequestMapping(
            value    = "/api/magacin/dodajMagacin/{firmaId}",
            method   = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
	public ResponseEntity<Magacin> dodajMagacin(@RequestBody Magacin magacin, @PathVariable Integer firmaId){
		
		Collection<Magacin> magacinProba = (Collection<Magacin>) magacinService.findAllBySifra(magacin.getSifra());
		
		if(magacinProba != null){
			for (Magacin magacin2 : magacinProba) {
				if(magacin2.getPreduzece().getId().equals(firmaId)){
					magacin2.setSifra("greska");
					return new ResponseEntity<Magacin>(magacin2, HttpStatus.OK);
				}
			}
		}
		
		Company firma = companyService.findOne(firmaId);
		magacin.setPreduzece(firma);
		Magacin m = magacinService.save(magacin);
		return new ResponseEntity<Magacin>(m, HttpStatus.OK);
	}
	
	@RequestMapping(
            value    = "/api/magacin/lager/{sifra}/{firmaId}",
            method   = RequestMethod.GET,
            produces = MediaType.APPLICATION_PDF_VALUE
    )
	public void getReportForWarehouse(HttpServletResponse response, @PathVariable String sifra, @PathVariable Integer firmaId) throws JRException, IOException {
		System.out.println(sifra + " " + firmaId);
		Collection<Magacin> magacinProba = (Collection<Magacin>) magacinService.findAllBySifra(sifra);
		
		if(magacinProba != null){
			for (Magacin magacin2 : magacinProba) {
				if(magacin2.getPreduzece().getId().equals(firmaId)){
					
					String outputFile = "C:\\Users\\Adam\\Desktop\\lager.pdf";
					Map<String, Object> parameters = new HashMap<String, Object>();
					parameters.put("sifra", magacin2.getSifra());
					parameters.put("naziv", magacin2.getNaziv());
					parameters.put("name", magacin2.getPreduzece().getName());
					
					JasperPrint jp = JasperFillManager.fillReport("D:\\lager.jasper", 
							parameters, new JREmptyDataSource());
					File file = new File(outputFile);
					JasperExportManager.exportReportToPdfStream(jp, new FileOutputStream(file));
					
					response.setContentType("application/pdf");
					IOUtils.copy(new FileInputStream(file), response.getOutputStream());
				}
			}
		}
	}
	
	@RequestMapping(
            value    = "/api/magacin/obrisiMagacin/{firmaId}",
            method   = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
	public ResponseEntity<Magacin> obrisiMagacin(@RequestBody Magacin magacin, @PathVariable Integer firmaId){
		Collection<Magacin> magacinProba = (Collection<Magacin>) magacinService.findAllBySifra(magacin.getSifra());
		
		for (Magacin magacin2 : magacinProba) {
			if(magacin2.getPreduzece().getId().equals(firmaId)){
				magacinService.deleteMagacin(magacin2);
				return new ResponseEntity<Magacin>(magacin2, HttpStatus.OK);
			}
		}
		return null;
	}
	
	@RequestMapping(
            value    = "/api/magacin/sviMagacini",
            method   = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
	public ResponseEntity<Collection<Magacin>> sviMagacini() {
		Collection<Magacin> magacini = magacinService.findAll();
		return new ResponseEntity<Collection<Magacin>>(magacini, HttpStatus.OK);
	}
	
	@RequestMapping(
            value    = "/api/magacin/findByPreduzece/{preduzecePib}",
            method   = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
	public ResponseEntity<Magacin> findByPreduzece(@PathVariable BigInteger preduzecePib) {
		Company preduzece = companyService.findByPib(preduzecePib);
		Magacin magacin = magacinService.findByPreduzece(preduzece);
        return new ResponseEntity<Magacin>(magacin, HttpStatus.OK);
    }
	
	
}
