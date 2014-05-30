package de.codecentric.wundershop.process.adapter;

import java.util.Map;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.mule.api.MuleException;
import org.mule.api.MuleMessage;
import org.mule.api.client.MuleClient;

import de.codecentric.wundershop.MuleStarter;
import de.codecentric.wundershop.process.ProcessVariableAccessor;

public abstract class AbstractMuleAdapter implements JavaDelegate {

  @Override
  public void execute(DelegateExecution execution) throws Exception {
    ProcessVariableAccessor variables = new ProcessVariableAccessor(execution);

    callMule(execution, variables);     
  }

  public abstract void callMule(DelegateExecution execution, ProcessVariableAccessor variables) throws Exception;

  public Object callMuleFlowSync(String vmPath, Object argument, Map<String, Object> properties) throws MuleException {
    MuleClient client = MuleStarter.muleContext.getClient();
    MuleMessage reply = client.send("vm://" + vmPath, argument, properties);
    return reply.getPayload();
  }

}
