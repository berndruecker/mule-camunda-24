package de.codecentric.wundershop.domain;

import java.io.Serializable;

public class Adresse implements Serializable {

  private static final long serialVersionUID = 1L;
  
  private String email;
  private String strasseUndHausnummer;
  private String postleitzahl;
  private String ort;

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getStrasseUndHausnummer() {
    return strasseUndHausnummer;
  }

  public void setStrasseUndHausnummer(String strasseUndHausnummer) {
    this.strasseUndHausnummer = strasseUndHausnummer;
  }

  public String getPostleitzahl() {
    return postleitzahl;
  }

  public void setPostleitzahl(String postleitzahl) {
    this.postleitzahl = postleitzahl;
  }

  public String getOrt() {
    return ort;
  }

  public void setOrt(String ort) {
    this.ort = ort;
  }
}
