package de.codecentric.wundershop.transformers;

import org.camunda.bpm.BpmPlatform;
import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractMessageTransformer;

import de.codecentric.wundershop.process.Constants;

public class SendPaymentReceivedToWorkflow extends AbstractMessageTransformer {

  @Override
  public Object transformMessage(MuleMessage message, String outputEncoding) throws TransformerException {
    String orderId = (String) message.getPayload();
    System.out.println("SendPaymentReceivedToWorkflow, id=" + orderId);
    
    BpmPlatform.getDefaultProcessEngine().getRuntimeService().createMessageCorrelation(Constants.MSG_PAYMENT_RECEIVED) //
      .processInstanceBusinessKey(orderId) //
      .correlate();

    return message;
  }
}
