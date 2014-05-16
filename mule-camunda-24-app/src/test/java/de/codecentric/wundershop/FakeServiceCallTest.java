package de.codecentric.wundershop;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.mule.api.MuleException;
import org.mule.api.MuleMessage;
import org.mule.api.client.MuleClient;
import org.mule.tck.junit4.FunctionalTestCase;

import de.codecentric.wundershop.shopservice.to.DummyRequest;
import de.codecentric.wundershop.shopservice.to.DummyResponse;

public class FakeServiceCallTest extends FunctionalTestCase {
    
    protected String getConfigResources() {
	return "src/main/app/wundershop.xml";
    }

    @Test
    public void test() throws MuleException {
	MuleClient client = muleContext.getClient();
	DummyRequest req = new DummyRequest();
	req.setDummy("lalala");
	MuleMessage reply = client.send("vm://fakeservicecaller", req, null);
	assertNotNull(reply);
	DummyResponse res =  (DummyResponse) reply.getPayload();
	assertNotNull(res);
	assertEquals("response for lalala", res.getDummy());
    }
}
