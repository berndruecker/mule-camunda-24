package de.codecentric.wundershop;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.InputStreamReader;
import java.io.Reader;

import org.junit.Before;
import org.junit.Test;
import org.mule.api.MuleException;
import org.mule.api.MuleMessage;
import org.mule.api.client.MuleClient;
import org.mule.tck.junit4.FunctionalTestCase;

import de.codecentric.wundershop.domain.Bestellung;

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
    }
}
