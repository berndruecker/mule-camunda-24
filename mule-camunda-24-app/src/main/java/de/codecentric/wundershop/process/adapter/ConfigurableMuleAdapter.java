package de.codecentric.wundershop.process.adapter;

import java.util.Map;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.Expression;
import org.codehaus.jackson.map.ObjectMapper;

import de.codecentric.wundershop.process.ProcessVariableAccessor;

public class ConfigurableMuleAdapter extends AbstractMuleAdapter {
  
  private Expression flowName;
  private Expression argument;
  private Expression properties;

  @Override
  public void callMule(DelegateExecution execution, ProcessVariableAccessor variables) throws Exception {
    // TODO: Check parameters
    
    // construct properties
    @SuppressWarnings("unchecked")
    Map<String, Object> propertyMap = new ObjectMapper().readValue( (String)properties.getValue(execution), Map.class);    
    
    // call service
    callMuleFlowSync((String)flowName.getValue(execution), argument.getValue(execution), propertyMap);
  }

  public Expression getFlowName() {
    return flowName;
  }

  public void setFlowName(Expression flowName) {
    this.flowName = flowName;
  }

  public Expression getArgument() {
    return argument;
  }

  public void setArgument(Expression argument) {
    this.argument = argument;
  }

  public Expression getProperties() {
    return properties;
  }

  public void setProperties(Expression properties) {
    this.properties = properties;
  }
  
//  public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException {
//    Map<String, Object> properties = new HashMap<>();
//    properties.put("to", "roger.butenuth@codecentric.de");
//    properties.put("from", "wundershop@gmx.de");
//    properties.put("subject", "Ein sinnfreies subject");
//    
//    System.out.println(new ObjectMapper().writeValueAsString(properties));
//  }

}
