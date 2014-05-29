package de.codecentric.wundershop.process.adapter;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.mule.api.MuleException;

import de.codecentric.wundershop.process.ProcessVariableAccessor;
import de.codecentric.wundershop.shopservice.ShopStatus;

public class SetStatusConfirmedAdapter extends AbstractMuleAdapter {

  public void callMule(DelegateExecution execution, ProcessVariableAccessor variables) throws MuleException {
    Object args[] = new Object[] { 
        variables.getBestellung().getId(), //
        ShopStatus.CONFIRMED };
    
    callMuleFlowSync("set-status", args, null);
  }
  
}
