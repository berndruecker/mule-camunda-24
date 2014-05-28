package de.codecentric.wundershop.transformers;

import org.camunda.bpm.BpmPlatform;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractMessageTransformer;

import de.codecentric.wundershop.process.Constants;
import de.codecentric.wundershop.process.ProcessVariableAccessor;

public class CreateWorkflowTransformer extends AbstractMessageTransformer {

  @Override
  public Object transformMessage(MuleMessage message, String outputEncoding) throws TransformerException {
    String orderJSON = (String) message.getPayload();
    
    System.out.println(" ### RECEIVED BESTELLUNG : " + orderJSON);

    ProcessVariableAccessor variables = new ProcessVariableAccessor();
    variables.setBestellungJSON(orderJSON);
    String orderId = variables.getBestellung().getId();
    
    ProcessInstance processInstance = BpmPlatform.getDefaultProcessEngine().getRuntimeService().startProcessInstanceByKey(Constants.PROCESS_NAME, orderId, variables.asHashMap());
    
    // aligned properties with camel component
    // https://github.com/camunda/camunda-bpm-camel/blob/master/camunda-bpm-camel-common/src/main/java/org/camunda/bpm/camel/component/CamundaBpmConstants.java
    message.setOutboundProperty("CamundaBpmProcessInstanceId", processInstance.getId());

    return message;
  }
}
