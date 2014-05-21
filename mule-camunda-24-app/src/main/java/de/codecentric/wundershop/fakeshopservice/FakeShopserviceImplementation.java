package de.codecentric.wundershop.fakeshopservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.jws.WebService;

import org.apache.log4j.Logger;

import de.codecentric.wundershop.shopservice.ShopStatus;
import de.codecentric.wundershop.shopservice.Shopservice;
import de.codecentric.wundershop.shopservice.to.GetUnprocessedPaymentsResponse;

@WebService(endpointInterface = "de.codecentric.wundershop.shopservice.Shopservice", targetNamespace = "http://shop.de/services")
public class FakeShopserviceImplementation implements Shopservice {
    private static Logger logger = Logger.getLogger(FakeShopserviceImplementation.class);
    
    private Set<String> paidAndUnprocessedOrders = new LinkedHashSet<>();
    private Map<String, ShopStatus> orderStatus = new HashMap<>();
    
    
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
    public synchronized void setStatus(String id, ShopStatus status) {
	logger.info("set status of order \"" + id + "\" to " + status);
	orderStatus.put(id, status);
    }

    /**
     * <em>Not</em> a WebMethod!
     * @param id Order ID
     */
    public synchronized void markOrderAsPaid(String id) {
	paidAndUnprocessedOrders.add(id);
    }
    
    /**
     * <em>Not</em> a WebMethod!
     * @param id Order ID
     * @return Status of order.
     */
    public synchronized ShopStatus getStatus(String id) {
	return orderStatus.get(id);
    }
}
