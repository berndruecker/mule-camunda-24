package de.codecentric.wundershop.domain;

public class Position {
    private String artikelnummer;
    private String bezeichnung;
    private int anzahl;
    
    public String getArtikelnummer() {
        return artikelnummer;
    }
    
    public void setArtikelnummer(String artikelnummer) {
        this.artikelnummer = artikelnummer;
    }
    
    public String getBezeichnung() {
        return bezeichnung;
    }
    
    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }
    
    public int getAnzahl() {
        return anzahl;
    }

    public void setAnzahl(int anzahl) {
        this.anzahl = anzahl;
    }
}
