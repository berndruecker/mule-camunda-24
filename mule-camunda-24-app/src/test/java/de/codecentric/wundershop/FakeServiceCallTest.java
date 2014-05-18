package de.codecentric.wundershop;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.mule.api.MuleException;
import org.mule.api.MuleMessage;
import org.mule.api.client.MuleClient;
import org.mule.tck.junit4.FunctionalTestCase;

import de.codecentric.wundershop.fakeshopservice.FakeShopserviceImplementation;
import de.codecentric.wundershop.shopservice.to.GetUnprocessedPaymentsResponse;

public class FakeServiceCallTest extends FunctionalTestCase {
    
    protected String getConfigResources() {
	return "src/main/app/fakeshopsystem.xml";
    }
    
    @Test
    public void testGetUnprocessedPayments() throws MuleException {
	GetUnprocessedPaymentsResponse response = (GetUnprocessedPaymentsResponse) call("getUnprocessedPayments", null);
        List<String> empty = response.getPayments();
	assertTrue(empty.isEmpty());
    }
    
    @Test
    public void testMarkPaymentProcessed() throws MuleException {
	FakeShopserviceImplementation fakeShop = muleContext.getRegistry().lookupObject("fakeShop");
        fakeShop.markOrderAsPaid("first");
        fakeShop.markOrderAsPaid("second");
	GetUnprocessedPaymentsResponse response = (GetUnprocessedPaymentsResponse) call("getUnprocessedPayments", null);
        List<String> list = response.getPayments();
	assertEquals(2, list.size());
	assertEquals("first", list.get(0));
	assertEquals("second", list.get(1));
	call("markPaymentProcessed", "first");
	response = (GetUnprocessedPaymentsResponse) call("getUnprocessedPayments", null);
        list = response.getPayments();
	assertEquals(1, list.size());
	assertEquals("second", list.get(0));
    }
    
    private Object call(String operation, Object argument) throws MuleException {
	Map<String, Object> props = new HashMap<>();
	props.put("operation", operation);
	MuleClient client = muleContext.getClient();
	MuleMessage reply = client.send("vm://fakeservicecaller", argument, props);
	return reply.getPayload();
    }
    
    @Test
    public void testMarkOrderAsShipped() throws MuleException {
	call("markOrderAsShipped", "first");
	// There should be a log message...
    }
}
