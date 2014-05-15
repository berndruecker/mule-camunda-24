package de.codecentric.wundershop;

import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractMessageTransformer;

public class MailTransformer extends AbstractMessageTransformer {

    @Override
    public Object transformMessage(MuleMessage message, String outputEncoding) throws TransformerException {
	return "done";
    }
}
