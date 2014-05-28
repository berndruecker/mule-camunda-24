package de.codecentric.wundershop.domain;

import java.io.Serializable;

public class Position implements Serializable {

  private static final long serialVersionUID = 1L;
  
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
