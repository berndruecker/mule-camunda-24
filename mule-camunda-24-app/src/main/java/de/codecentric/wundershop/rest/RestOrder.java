package de.codecentric.wundershop.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.mule.api.MuleContext;
import org.mule.api.MuleException;
import org.mule.api.client.MuleClient;
import org.mule.api.context.MuleContextAware;

@Path("order")
public class RestOrder implements MuleContextAware {
    private MuleContext context;

    @Override
    public void setMuleContext(MuleContext context) {
	this.context = context;
    }

    @PUT
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public String order(String json) throws MuleException {
	MuleClient client = context.getClient();
	client.send("vm://vm-order", json, null);

	return "Order received, starting workflow...";
    }
}
