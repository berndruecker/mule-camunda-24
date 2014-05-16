package de.codecentric.wundershop.fakeshopservice;

import javax.jws.WebService;

import de.codecentric.wundershop.shopservice.Shopservice;
import de.codecentric.wundershop.shopservice.to.DummyRequest;
import de.codecentric.wundershop.shopservice.to.DummyResponse;

@WebService(endpointInterface = "de.codecentric.wundershop.shopservice.Shopservice", targetNamespace = "http://shop.de/services")
public class FakeShopserviceImplementation implements Shopservice {

    @Override
    public DummyResponse dummyCall(DummyRequest request) {
	DummyResponse resp = new DummyResponse();
	resp.setDummy("response for " + request.getDummy());
	return resp;
    }

}
