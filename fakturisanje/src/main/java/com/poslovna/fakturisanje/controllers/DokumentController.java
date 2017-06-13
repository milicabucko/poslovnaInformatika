package com.poslovna.fakturisanje.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Collection;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

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
import com.poslovna.fakturisanje.models.Dokument;
import com.poslovna.fakturisanje.services.CompanyService;
import com.poslovna.fakturisanje.services.DokumentService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@RestController
public class DokumentController {

	@Autowired
	private DokumentService fakturaService;
	
	@Autowired
	private CompanyService companyService;
	
	@RequestMapping(
            value    = "/api/faktura/nadjiSledeciBrojDokumenta",
            method   = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Integer> findBySifra() {
		Integer brojDok = fakturaService.nadjiSlececiBrojDokumenta();
        return new ResponseEntity<Integer>(brojDok, HttpStatus.OK);
    }
	
	@RequestMapping(
            value    = "/api/faktura/sacuvajFakturu/{izdId}/{kupId}",
            method   = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Dokument> sacuvajFakturu(@RequestBody Dokument faktura, @PathVariable Integer izdId, @PathVariable Integer kupId) {
		Company izdavaoc = companyService.findOne(izdId);
		Company kupac = companyService.findOne(kupId);
		faktura.setIzdavaocRacuna(izdavaoc);
		faktura.setKupac(kupac);
		Dokument fakturaa = fakturaService.save(faktura);
        return new ResponseEntity<Dokument>(fakturaa, HttpStatus.OK);
    }
	

	@RequestMapping(
            value    = "/api/faktura/sveFakture",
            method   = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Collection<Dokument>> sveFakture() {
		Collection<Dokument> sveFakture = fakturaService.getAll();
        return new ResponseEntity<Collection<Dokument>>(sveFakture, HttpStatus.OK);
    }
	
	@RequestMapping(
            value    = "/api/faktura/promeniStatusDokumenta/{fakturaId}",
            method   = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Integer> promeniStatusDokumenta(@RequestBody Dokument faktura, @PathVariable Integer fakturaId) {
		Dokument novaFaktura = fakturaService.findOne(fakturaId);
		novaFaktura.setStatusDokumenta(faktura.getStatusDokumenta());
		novaFaktura.setDatumKnjizenja(faktura.getDatumKnjizenja());
		fakturaService.save(novaFaktura);
        return new ResponseEntity<Integer>(novaFaktura.getBrojDokumenta(), HttpStatus.OK);
    }
	
	@RequestMapping(
            value    = "/api/faktura/nadjiPoBrojuDokumenta/{brojDokumenta}",
            method   = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Collection<Dokument>> nadjiPoBrojuDokumenta(@PathVariable Integer brojDokumenta) {
		Collection<Dokument> faktura = fakturaService.findByBrojDokumenta(brojDokumenta);
        return new ResponseEntity<Collection<Dokument>>(faktura, HttpStatus.OK);
    }
	
	@RequestMapping(
            value    = "/api/faktura/izvestaj/{fakturaId}/{dobavljacId}/{kupacId}",
            method   = RequestMethod.GET,
            produces = MediaType.APPLICATION_PDF_VALUE
    )
	public void izvestajFaktura(HttpServletResponse response, @PathVariable Integer fakturaId, @PathVariable Integer dobavljacId,
			@PathVariable Integer kupacId) throws JRException, IOException {
		//System.out.println(fakturaId + " " + dobavljacId + " " + kupacId);
		HashMap hm = null;

		try {
			System.out.println("Start ....");
			String jrxmlFileName = "D:\\MILAN_CETVRTA_GODINA\\PI\\Git-projekat\\poslovnaInformatika\\fakturisanje\\src\\main\\resources\\static\\reports\\faktura.jrxml";
			String jasperFileName = "D:\\MILAN_CETVRTA_GODINA\\PI\\Git-projekat\\poslovnaInformatika\\fakturisanje\\src\\main\\resources\\static\\reports\\faktura.jasper";
			//String jasperFileName = "/reports/magacinskaKartica.jasper";
			 //* fakturisanje/src/main/resources/static/reports
			String pdfFileName = "C:\\Users\\Adam\\Desktop\\faktura-otpremnica.pdf";

			JasperCompileManager.compileReportToFile(jrxmlFileName, jasperFileName);

			// String dbUrl = props.getProperty("jdbc.url");
			String dbUrl = "jdbc:mysql://localhost:3306/fakturisanje";
			// String dbDriver = props.getProperty("jdbc.driver");
			String dbDriver = "com.mysql.jdbc.Driver";
			// String dbUname = props.getProperty("db.username");
			String dbUname = "root";
			// String dbPwd = props.getProperty("db.password");
			String dbPwd = "svitac94";

			// Load the JDBC driver
			Class.forName(dbDriver);
			// Get the connection
			Connection conn = DriverManager.getConnection(dbUrl, dbUname, dbPwd);

			hm = new HashMap();
			hm.put("fakturaId", fakturaId);
			hm.put("dobavljacId", dobavljacId);
			hm.put("kupacId", kupacId);

			// Generate jasper print
			JasperPrint jprint = (JasperPrint) JasperFillManager.fillReport(jasperFileName, hm, conn);
			
			// Export pdf file
			JasperExportManager.exportReportToPdfFile(jprint, pdfFileName);

			System.out.println("Done exporting reports to pdf");

		} catch (Exception e) {
			System.out.print("Exceptiion" + e);
		}
	}
}
