package de.codecentric.wundershop;

import static org.assertj.core.api.Assertions.fail;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.assertThat;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.*;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.apache.tools.ant.taskdefs.Execute;
import org.camunda.bpm.BpmPlatform;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.impl.cfg.StandaloneInMemProcessEngineConfiguration;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.test.assertions.ProcessEngineTests;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mule.tck.junit4.FunctionalTestCase;

import de.codecentric.wundershop.domain.Bestellung;
import de.codecentric.wundershop.domain.BestellungFactory;
import de.codecentric.wundershop.fakeshopservice.FakeShopserviceImplementation;
import de.codecentric.wundershop.process.Constants;
import de.codecentric.wundershop.process.ProcessVariableAccessor;
import de.codecentric.wundershop.shopservice.to.GetUnprocessedPaymentsResponse;

public class ProcessTest extends FunctionalTestCase {

  @Rule
  public ProcessEngineRule processEngineRule = new ProcessEngineRule( //
      new StandaloneInMemProcessEngineConfiguration() //
          .setJobExecutorActivate(false) //
          .buildProcessEngine());

  protected String getConfigResources() {
    return "src/main/app/fakeshopsystem.xml,src/main/app/wundershop.xml";
  }

  @Before
  public void init() throws Exception {
    MuleStarter.muleContext = muleContext;
    // ProcessEngineTests.init(processEngine);
  }

  @Test
  @Deployment(resources = "mule-order-management.bpmn")
  public void testHappyPath() {

    Bestellung bestellung = new BestellungFactory().createBestellungMitZweiPositionen();

    ProcessVariableAccessor variables = new ProcessVariableAccessor();
    variables.setBestellung(bestellung);
    ProcessInstance pi = processEngineRule.getRuntimeService().startProcessInstanceByKey(Constants.PROCESS_NAME, bestellung.getId(), variables.asHashMap());

    assertThat(pi) //
        .isStarted() //
        .isNotEnded() //
        .isWaitingAtExactly("userTaskConfirmOrder") // doppelt gemoppelt - s.u.
        .task() //
        .hasCandidateGroup("sales") //
        .hasDefinitionKey("userTaskConfirmOrder"); // ...

    // claim task to personal task list
    claim(task(), "bernd");

    // try {
    // claim(task(), "roger");
    // fail("Should not be possible to claim already claimed task");
    // } catch (Exception ex) {
    // } // ignore - TaskAlreadyClaimedException (Task 'x' is already claimed by
    // someone else) is expected!

    complete(task(), withVariables("orderApproved", "true"));

    assertThat(pi) //
        .isNotEnded().isWaitingAtExactly("eventBasedGatewayPayment");

    markAsPayed(bestellung.getId());
    
    // no idea how to trigger the poller - so do the correlation myself
    processEngineRule.getRuntimeService().createMessageCorrelation(Constants.MSG_PAYMENT_RECEIVED) //
      .processInstanceBusinessKey(bestellung.getId()) //
      .correlate();

    assertThat(pi) //
      .task() //
      .hasCandidateGroup("management") //
      .hasDefinitionKey("userTaskProduceArticle"); 
    
    assertThat(pi) //
      .job("TimerProduceArticle");
    
    execute( job() );
    
  }

  private void markAsPayed(String orderId) {
    FakeShopserviceImplementation fakeShop = muleContext.getRegistry().lookupObject("fakeShop");
    fakeShop.markOrderAsPaid(orderId);
  }

}
