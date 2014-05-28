package de.codecentric.wundershop.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.mule.api.MuleContext;
import org.mule.api.context.MuleContextAware;

import de.codecentric.wundershop.fakeshopservice.FakeShopserviceImplementation;

@Path("payment")
public class RestPayment implements MuleContextAware {
    private MuleContext context;

    @Override
    public void setMuleContext(MuleContext context) {
	this.context = context;
    }

    @PUT
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public String pay(String id) {
	FakeShopserviceImplementation fakeShop = context.getRegistry().lookupObject("fakeShop");
	fakeShop.markOrderAsPaid(id);
	return "Order with ID \""  + id + "\" is now marked as paid";
    }
}
