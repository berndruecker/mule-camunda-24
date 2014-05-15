package de.codecentric.wundershop.domain;

import java.util.ArrayList;
import java.util.List;

public class BestellungFactory {
    public Bestellung createBestellungMitZweiPositionen() {
	Bestellung b = new Bestellung();
	Adresse a = new AdresseFactory().createAdresse();
	b.setAdresse(a);
	b.setBemerkung("Keine schlaue Bemerkung");
	List<Position> ps = new ArrayList<>();
	PositionFactory pf = new PositionFactory();
	ps.add(pf.createHexPosition());
	ps.add(pf.createUnmoegliches());
	b.setPositionen(ps);
	return b;
    }
}
