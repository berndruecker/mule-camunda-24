package de.codecentric.wundershop.domain;

public class PositionFactory {

    public Position createHexPosition() {
	Position p = new Position();
	p.setAnzahl(1);
	p.setArtikelnummer("42");
	p.setBezeichnung("Einfache Hexerei");
	return p;
    }

    public Position createUnmoegliches() {
	Position p = new Position();
	p.setAnzahl(1);
	p.setArtikelnummer("43");
	p.setBezeichnung("Kleine Unmöglichkeit");
	return p;
    }


    public Position createSimplesWunder() {
	Position p = new Position();
	p.setAnzahl(1);
	p.setArtikelnummer("44");
	p.setBezeichnung("Simples Wunder");
	return p;
    }

}
