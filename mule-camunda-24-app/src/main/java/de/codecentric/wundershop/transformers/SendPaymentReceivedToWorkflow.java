package de.codecentric.wundershop.transformers;

import org.apache.log4j.Logger;
import org.camunda.bpm.BpmPlatform;
import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractMessageTransformer;

import de.codecentric.wundershop.process.Constants;

public class SendPaymentReceivedToWorkflow extends AbstractMessageTransformer {
    private static Logger logger = Logger.getLogger(SendPaymentReceivedToWorkflow.class);

  @Override
  public Object transformMessage(MuleMessage message, String outputEncoding) throws TransformerException {
    String orderId = (String) message.getPayload();
    logger.info("SendPaymentReceivedToWorkflow, id=" + orderId);
    
    BpmPlatform.getDefaultProcessEngine().getRuntimeService().createMessageCorrelation(Constants.MSG_PAYMENT_RECEIVED) //
      .processInstanceBusinessKey(orderId) //
      .correlate();

    return message;
  }
}
