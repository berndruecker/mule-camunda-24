package de.codecentric.wundershop;

import java.io.InputStreamReader;
import java.io.Reader;

import org.junit.Before;
import org.mule.tck.junit4.FunctionalTestCase;

/**
 * Create an order.
 */
public class OrderFlowTest extends FunctionalTestCase {
    private String bestellungJson;
    
    protected String getConfigResources() {
	return "src/main/app/wundershop.xml";
    }

    @Before
    public void readJson() throws Exception {
	StringBuilder sb = new StringBuilder();
	try (Reader rd = new InputStreamReader(getClass().getResourceAsStream("bestellung.json"))) {
	    int ch = rd.read();
	    while (ch != -1) {
		sb.append((char)ch);
		ch = rd.read();
	    }
	}
	bestellungJson = sb.toString();
	System.out.println("Bestellung as json:");
	System.out.println(bestellungJson);
    }
}
