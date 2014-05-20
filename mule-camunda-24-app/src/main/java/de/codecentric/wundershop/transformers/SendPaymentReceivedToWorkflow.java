package de.codecentric.wundershop.transformers;

import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractMessageTransformer;

public class SendPaymentReceivedToWorkflow extends AbstractMessageTransformer {

    @Override
    public Object transformMessage(MuleMessage message, String outputEncoding) throws TransformerException {
	String id = (String) message.getPayload();
	System.out.println("SendPaymentReceivedToWorkflow, id=" + id);
	// TODO: Hier an camunda melden, dass Order mit id bezahlt ist.
	return message;
    }
}
