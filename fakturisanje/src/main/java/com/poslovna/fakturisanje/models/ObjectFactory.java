package com.poslovna.fakturisanje.models;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {

	private final static QName _Odsek_QNAME = new QName("http://www.ftn.uns.ac.rs/fakturisanje/models", "stavkaXML");

    public ObjectFactory() {
    }

    
    public FakturaXML createFakturaXML() {
    	return new FakturaXML();
    }
    
    public StavkaXML createStavkaXML() {
    	return new StavkaXML();
    }
    
    /*public StavkaDokumenta createStavkeDokumenta() {
        return new StavkaDokumenta();
    }

    public Dokument createDokument() {
        return new Dokument();
    }

    public Company createCompany() {
        return new Company();
    }

    public PoslovnaGodina createPoslovnaGodina() {
        return new PoslovnaGodina();
    }*/

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StavkeDokumenta }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ftn.uns.ac.rs/fakturisanje/models", name = "stavkaXML")
    public JAXBElement<StavkaXML> createStavkeXML(StavkaXML value) {
        return new JAXBElement<StavkaXML>(_Odsek_QNAME, StavkaXML.class, null, value);
    }
}
