package com.poslovna.fakturisanje.models;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {

	private final static QName _Odsek_QNAME = new QName("http://www.ftn.uns.ac.rs/fakturisanje/models", "stavkeDokumenta");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: rs.ac.uns.ftn.jaxb.example2
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link OdsekType }
     * 
     */
    public StavkaDokumenta createStavkeDokumenta() {
        return new StavkaDokumenta();
    }

    /**
     * Create an instance of {@link Fakultet }
     * 
     */
    public Dokument createDokument() {
        return new Dokument();
    }

    /**
     * Create an instance of {@link Student }
     * 
     */
    /*public Student createStudent() {
        return new Student();
    }

    *//**
     * Create an instance of {@link PolozenIspit }
     * 
     *//*
    public PolozenIspit createPolozenIspit() {
        return new PolozenIspit();
    }

    *//**
     * Create an instance of {@link OdsekType.Studenti }
     * 
     *//*
    public OdsekType.Studenti createOdsekTypeStudenti() {
        return new OdsekType.Studenti();
    }*/

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OdsekType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ftn.uns.ac.rs/fakturisanje/models", name = "stavkeDokumenta")
    public JAXBElement<StavkaDokumenta> createOdsek(StavkaDokumenta value) {
        return new JAXBElement<StavkaDokumenta>(_Odsek_QNAME, StavkaDokumenta.class, null, value);
    }
}
