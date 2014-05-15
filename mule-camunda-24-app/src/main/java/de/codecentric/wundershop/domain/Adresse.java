package de.codecentric.wundershop.domain;

public class Adresse {
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
