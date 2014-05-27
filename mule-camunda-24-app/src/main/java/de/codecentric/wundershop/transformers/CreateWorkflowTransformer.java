package de.codecentric.wundershop.transformers;

import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractMessageTransformer;

import de.codecentric.wundershop.domain.Bestellung;

public class CreateWorkflowTransformer extends AbstractMessageTransformer {

    @Override
    public Object transformMessage(MuleMessage message, String outputEncoding) throws TransformerException {
	Bestellung order = (Bestellung) message.getPayload();
	Object original = message.getOriginalPayload();
	// TODO: Hier camunda workflow starten
	return message;
    }
}
