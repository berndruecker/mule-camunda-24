package de.codecentric.wundershop.domain;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mule.api.MuleException;
import org.mule.api.MuleMessage;
import org.mule.api.client.MuleClient;
import org.mule.tck.junit4.FunctionalTestCase;

/**
 * Kleiner Testfall, damit man das Json nicht manuell schreiben muss.
 */
public class JsonCreationTest extends FunctionalTestCase {

    protected String getConfigResources() {
	return "src/main/app/wundershop.xml";
    }

    @Test
    public void test() throws MuleException {
	MuleClient client = muleContext.getClient();
	BestellungFactory bf = new BestellungFactory();
	Bestellung value = bf.createBestellungMitZweiPositionen();
	MuleMessage reply = client.send("vm://jsoncreate", value, null, 5000);
	assertNotNull(reply);
	String json = (String) reply.getPayload();
	assertNotNull(json);
	System.out.println(json);
    }
}
