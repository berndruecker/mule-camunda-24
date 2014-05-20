package de.codecentric.wundershop.fakeshopservice;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.jws.WebService;

import org.apache.log4j.Logger;

import de.codecentric.wundershop.shopservice.Shopservice;
import de.codecentric.wundershop.shopservice.to.GetUnprocessedPaymentsResponse;

@WebService(endpointInterface = "de.codecentric.wundershop.shopservice.Shopservice", targetNamespace = "http://shop.de/services")
public class FakeShopserviceImplementation implements Shopservice {
    private static Logger logger = Logger.getLogger(FakeShopserviceImplementation.class);
    
    private Set<String> paidAndUnprocessedOrders = new LinkedHashSet<>();
    
    @Override
    public void setStatusConfirmed() {
	logger.info("set status confirmed");
    }
    
    @Override
    public void setStatusDeclined(String reason) {
	logger.info("set status declinde, reason: \"" + reason + "\"");
    }
    
    @Override
    public synchronized GetUnprocessedPaymentsResponse getUnprocessedPayments() {
	GetUnprocessedPaymentsResponse result = new GetUnprocessedPaymentsResponse();
	result.setPayments(new ArrayList<>(paidAndUnprocessedOrders));
	return result;
    }

    @Override
    public synchronized String markPaymentProcessed(String id) {
	paidAndUnprocessedOrders.remove(id);
	return id;
    }

    @Override
    public void markOrderAsShipped(String id) {
	logger.info("Order \"" + id + "\" has been shipped");
    }

    /**
     * <em>Not</em> a WebMethod!
     * @param id Order ID
     */
    public synchronized void markOrderAsPaid(String id) {
	paidAndUnprocessedOrders.add(id);
    }
}
