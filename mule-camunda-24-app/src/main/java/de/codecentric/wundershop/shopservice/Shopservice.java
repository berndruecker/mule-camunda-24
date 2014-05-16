package de.codecentric.wundershop.shopservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import de.codecentric.wundershop.shopservice.to.DummyRequest;
import de.codecentric.wundershop.shopservice.to.DummyResponse;

@WebService(targetNamespace = "http://shop.de/services")
public interface Shopservice {
    @WebMethod
    public DummyResponse dummyCall(@WebParam(name = "request") DummyRequest request);

}
