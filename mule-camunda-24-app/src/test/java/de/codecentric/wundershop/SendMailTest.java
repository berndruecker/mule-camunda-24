package de.codecentric.wundershop;

import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.mule.api.MuleException;
import org.mule.api.MuleMessage;
import org.mule.api.client.MuleClient;
import org.mule.tck.junit4.FunctionalTestCase;

/**
 * Kein echter Testfall, aber verschickt eine Mail über den SMTP-Connector.
 */
public class SendMailTest extends FunctionalTestCase {
    protected String getConfigResources() {
	return "src/main/app/wundershop.xml";
    }

    @Test
    public void test() throws MuleException {
	MuleClient client = muleContext.getClient();
	Map<String, Object> properties = new HashMap<>();
	properties.put("to", "roger.butenuth@codecentric.de");
	properties.put("from", "wundershop@gmx.de");
	properties.put("subject", "Ein sinnfreies subject");
	client.dispatch("vm://vm-send-mail-in", "Mail mit wenig Inhalt", properties);
	MuleMessage reply = client.request("vm://vm-send-mail-out", 10000);
	assertNotNull(reply);
    }
}
