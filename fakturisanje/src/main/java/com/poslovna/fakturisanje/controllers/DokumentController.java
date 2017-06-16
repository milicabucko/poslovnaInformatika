package com.poslovna.fakturisanje.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.poslovna.fakturisanje.models.ArtikalXML;
import com.poslovna.fakturisanje.models.Company;
import com.poslovna.fakturisanje.models.CompanyXML;
import com.poslovna.fakturisanje.models.Dokument;
import com.poslovna.fakturisanje.models.FakturaXML;
import com.poslovna.fakturisanje.models.PoslovnaGodina;
import com.poslovna.fakturisanje.models.StavkaDokumenta;
import com.poslovna.fakturisanje.models.StavkaXML;
import com.poslovna.fakturisanje.services.CompanyService;
import com.poslovna.fakturisanje.services.DokumentService;
import com.poslovna.fakturisanje.services.PoslovnaGodinaService;

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
	
	@Autowired
	private PoslovnaGodinaService poslovnaGodinaService;
	
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
		PoslovnaGodina godina = poslovnaGodinaService.findByPreduzeceAndAktivna(izdavaoc, true);
		
		if(godina == null){
			faktura.setStatusDokumenta("nevazeca");
			return new ResponseEntity<Dokument>(faktura, HttpStatus.OK);
		}
		faktura.setPoslovnaGodina(godina);
		faktura.setIzdavaocRacuna(izdavaoc);
		faktura.setKupac(kupac);
		faktura.setDatumDokumenta(faktura.getDatumDokumenta().substring(0, 10));
		//faktura.setDatumKnjizenja(faktura.getDatumKnjizenja().substring(0, 10));
		faktura.setDatumValute(faktura.getDatumValute().substring(0, 10));
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
            value    = "/api/faktura/sveNarudzbenice",
            method   = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Collection<Dokument>> sveNarudzbenice() {
		Collection<Dokument> sveNarudzbenice = fakturaService.sveNarudzbenice();
        return new ResponseEntity<Collection<Dokument>>(sveNarudzbenice, HttpStatus.OK);
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
            value    = "/api/faktura/obrisiNarudzbenicu/{brojDok}",
            method   = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Integer> obrisiNarudzbenicu(@PathVariable Integer brojDok) {
		ArrayList<Dokument> narudzbenice = (ArrayList<Dokument>) fakturaService.findByBrojDokumentaAndVrstaDokumenta(brojDok);
		Dokument dokument = narudzbenice.get(0);	
		Integer brojDokumenta = dokument.getBrojDokumenta();
		fakturaService.delete(dokument);
        return new ResponseEntity<Integer>(brojDokumenta, HttpStatus.OK);
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
            value    = "/api/faktura/nadjiPoBrojuNaruzbenice/{brojDokumenta}",
            method   = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Collection<Dokument>> nadjiPoBrojuNaruzbenice(@PathVariable Integer brojDokumenta) {
		Collection<Dokument> nar = fakturaService.findByBrojDokumentaAndVrstaDokumenta(brojDokumenta);
        return new ResponseEntity<Collection<Dokument>>(nar, HttpStatus.OK);
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
			//System.out.println("Start ....");
			String jrxmlFileName = "./src/main/resources/static/reports/faktura.jrxml";
			String jasperFileName = "./src/main/resources/static/reports/faktura.jasper";
			String pdfFileName = "./src/main/resources/static/pdfFiles/faktura-otpremnica.pdf";  
		      
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
			//System.out.println("Done exporting reports to pdf");		
			File file = new File("./src/main/resources/static/pdfFiles/faktura-otpremnica.pdf");
			if (file.exists()) {
				//System.out.println("Ima ga!");
				//System.out.println(file.getAbsolutePath());
			}		
			try{
		         Process p = Runtime
		        		 .getRuntime()
		        		 .exec("rundll32 url.dll,FileProtocolHandler " + file.getAbsolutePath());
		         p.waitFor();
		    } catch (Exception ex) {
		    	ex.printStackTrace();
		    }	
		} catch (Exception e) {
			System.out.print("Exceptiion" + e);
		}
	}
	
	@RequestMapping(
            value    = "/api/faktura/exportToXML/{fakturaId}",
            method   = RequestMethod.GET,
            produces = MediaType.APPLICATION_XML_VALUE
    )
	public void exportFakture(HttpServletResponse response, @PathVariable Integer fakturaId) throws FileNotFoundException{
		//System.out.println(fakturaId);
		Dokument faktura = fakturaService.findOne(fakturaId);
		
		
		System.out.println(faktura);
		
		FakturaXML fakturaXML = new FakturaXML();
		
		fakturaXML.setBrojDokumenta(faktura.getBrojDokumenta());
		fakturaXML.setStatusDokumenta(faktura.getStatusDokumenta());
		fakturaXML.setDatumDokumenta(faktura.getDatumDokumenta());
		fakturaXML.setDatumValute(faktura.getDatumValute());
		fakturaXML.setDatumKnjizenja(faktura.getDatumKnjizenja());
		fakturaXML.setUkupnoZaPlacanje(faktura.getUkupnoZaPlacanje());
		
		CompanyXML izdavaocRacuna = new CompanyXML();
		izdavaocRacuna.setName(faktura.getIzdavaocRacuna().getName());
		izdavaocRacuna.setPib(faktura.getIzdavaocRacuna().getPib());
		izdavaocRacuna.setMbr(faktura.getIzdavaocRacuna().getCidnumber());
		izdavaocRacuna.setAccount(faktura.getIzdavaocRacuna().getAccount());
		
		CompanyXML kupac = new CompanyXML();
		kupac.setName(faktura.getKupac().getName());
		kupac.setPib(faktura.getKupac().getPib());
		kupac.setMbr(faktura.getKupac().getCidnumber());
		kupac.setAccount(faktura.getKupac().getAccount());
		
		fakturaXML.setIzdavaocRacuna(izdavaocRacuna);
		fakturaXML.setKupac(kupac);
		
		fakturaXML.setNaziv("testNaziv");
	
	/*	StavkaXML stavkaXML = new StavkaXML();
		stavkaXML.setNaziv("stavka1");
		StavkaXML stavkaXML2 = new StavkaXML();
		stavkaXML2.setNaziv("stavka2");
		StavkaXML stavkaXML3 = new StavkaXML();
		stavkaXML3.setNaziv("stavka3");*/
		
		ArrayList<StavkaXML> sveStavke = new ArrayList<StavkaXML>();
		
		for (StavkaDokumenta stavkaDokumenta : faktura.getStavkeDokumenta()) {
			
			StavkaXML stavkaXML = new StavkaXML();
			stavkaXML.setKolicina(stavkaDokumenta.getKolicina());
			stavkaXML.setCena(stavkaDokumenta.getCena());
			
			ArtikalXML artikalXML = new ArtikalXML();
			artikalXML.setNaziv(stavkaDokumenta.getArtikal().getNaziv());
			artikalXML.setSifra(stavkaDokumenta.getArtikal().getSifra());
			artikalXML.setVrsta(stavkaDokumenta.getArtikal().getVrsta());
			artikalXML.setOpis(stavkaDokumenta.getArtikal().getOpis());
			artikalXML.setJedinicaMere(stavkaDokumenta.getArtikal().getJedinicaMere().getOznakaJedinice());
			stavkaXML.setArtikal(artikalXML);
			
			sveStavke.add(stavkaXML);
			
		}
		
		
		//inicijalizacija klase FakturaXML.Stavke
		FakturaXML.Stavke stavke = new FakturaXML.Stavke();	
		fakturaXML.setStavke(stavke);
		
		for (StavkaXML stavka : sveStavke) {
			fakturaXML.getStavke().getStavka().add(stavka);
		}
		
		try {
			System.out.println("[INFO] Example 2: JAXB unmarshalling/marshalling.\n");
			
			// Definiše se JAXB kontekst (putanja do paketa sa JAXB bean-ovima)
			JAXBContext context = JAXBContext.newInstance("com.poslovna.fakturisanje.models");
			
			// Marshaller je objekat zadužen za konverziju iz objektnog u XML model
			Marshaller marshaller = context.createMarshaller();
			
			// Podešavanje marshaller-a
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			
			// Umesto System.out-a, može se koristiti FileOutputStream
			try {
				marshaller.marshal(fakturaXML, new FileOutputStream(new File("./src/main/resources/static/xmlFiles/fakturaXML.txt")));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			File file = new File("./src/main/resources/static/xmlFiles/fakturaXML.txt");
			if (file.exists()) {
				//System.out.println("Ima ga!");
				//System.out.println(file.getAbsolutePath());
			}		
			try{
		         Process p = Runtime
		        		 .getRuntime()
		        		 .exec("rundll32 url.dll,FileProtocolHandler " + file.getAbsolutePath());
		         p.waitFor();
		    } catch (Exception ex) {
		    	ex.printStackTrace();
		    }	

			
			
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
}
