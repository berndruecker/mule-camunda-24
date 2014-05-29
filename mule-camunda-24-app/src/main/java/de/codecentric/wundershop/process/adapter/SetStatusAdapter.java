package de.codecentric.wundershop.process.adapter;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.Expression;
import org.mule.api.MuleException;

import de.codecentric.wundershop.process.ProcessVariableAccessor;
import de.codecentric.wundershop.shopservice.ShopStatus;

public class SetStatusAdapter extends AbstractMuleAdapter {
  
  private Expression status;

  public void callMule(DelegateExecution execution, ProcessVariableAccessor variables) throws MuleException {
    if (status.getValue(execution)==null) {
      throw new RuntimeException("You have to configure the " + this.getClass().getName() + " with the status value in your BPMN process model on activity " + execution.getCurrentActivityName());
    }
    // Check that the enum value is correct
    ShopStatus shopStatus = ShopStatus.valueOf((String)status.getValue(execution));
    
    Object args[] = new Object[] { 
        variables.getBestellung().getId(), //
        shopStatus};
    
    callMuleFlowSync("set-status", args, null);
  }

  public Expression getStatus() {
    return status;
  }

  public void setStatus(Expression status) {
    this.status = status;
  }
  
}
