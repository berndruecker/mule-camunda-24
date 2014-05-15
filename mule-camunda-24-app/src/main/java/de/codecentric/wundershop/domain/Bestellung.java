package de.codecentric.wundershop.domain;

import java.util.List;

public class Bestellung {
    private List<Position> positionen;
    private Adresse adresse;
    private String bemerkung;
    
    public List<Position> getPositionen() {
        return positionen;
    }
    
    public void setPositionen(List<Position> positionen) {
        this.positionen = positionen;
    }
    
    public Adresse getAdresse() {
        return adresse;
    }
    
    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }
    
    public String getBemerkung() {
        return bemerkung;
    }
    
    public void setBemerkung(String bemerkung) {
        this.bemerkung = bemerkung;
    }
}
