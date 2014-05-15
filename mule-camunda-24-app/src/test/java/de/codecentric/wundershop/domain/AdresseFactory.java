package de.codecentric.wundershop.domain;

public class AdresseFactory {

    public Adresse createAdresse() {
	Adresse a = new Adresse();
	a.setEmail("besteller@sonstwo.de");
	a.setOrt("Sonstwo");
	a.setPostleitzahl("12345");
	a.setStrasseUndHausnummer("Nirgendwoweg 42");
	return a;
    }

}
