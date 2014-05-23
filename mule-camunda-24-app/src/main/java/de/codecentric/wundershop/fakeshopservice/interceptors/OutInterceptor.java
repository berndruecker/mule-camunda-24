package de.codecentric.wundershop.fakeshopservice.interceptors;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.Phase;
import org.springframework.beans.factory.annotation.Autowired;

import de.codecentric.wundershop.fakeshopservice.TransactionHandler;

/**
 * See http://cxf.apache.org/docs/interceptors.html for more details about interceptors.
 */
public class OutInterceptor extends AbstractSoapInterceptor {
    @Autowired
    private TransactionHandler txHandler;

    public OutInterceptor() {
	super(Phase.PREPARE_SEND);
    }
    
    @Override
    public void handleMessage(SoapMessage message) throws Fault {
	// Called after service method is executed.
	txHandler.commitTransaction();
    }
    
    @Override
    public void handleFault(SoapMessage message) throws Fault {
	// NOT called on Exception in service method.
    }
}
