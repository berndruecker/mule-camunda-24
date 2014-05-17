package de.codecentric.wundershop.domain;

import java.util.List;

public class Bestellung {
    private String id;
    private List<Position> positionen;
    private Adresse adresse;
    private String bemerkung;
    
    /**
     * @return UUID Um Bestellung eindeutig zu identifzieren und Zahlung zuzuordnen.
     */
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
