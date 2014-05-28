package de.codecentric.wundershop.process;

import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.engine.delegate.VariableScope;
import org.codehaus.jackson.map.ObjectMapper;

import de.codecentric.wundershop.domain.Bestellung;

public class ProcessVariableAccessor {

  protected VariableScope variableScope;
  protected Map<String, Object> variableMap;

  public enum VARIABLES {
    Bestellung
  }

  public ProcessVariableAccessor() {
    // fallback if this is used outside of CDI without BusinessProcess
    this.variableMap = new HashMap<String, Object>();
  }
 
  public ProcessVariableAccessor(VariableScope variableScope) {
    this.variableScope = variableScope;
  }

  public ProcessVariableAccessor(Map<String, Object> varMap) {
    this.variableMap = varMap;
  }

  @Override
  public String toString() {
    return "Variables [booking=" + getBestellung() + "]";
  }

  public String getBestellungJSON() {
    return (String) getVariable(VARIABLES.Bestellung.toString());
  }

  public Bestellung getBestellung() {
    try {
      return new ObjectMapper().readValue(getBestellungJSON(), Bestellung.class);
    } catch (Exception e) {
      throw new RuntimeException("could not map JSON to POJO: " + getBestellungJSON(), e);
    }
  }

  public void setBestellungJSON(String bestellungJSON) {
    setVariable(VARIABLES.Bestellung.toString(), bestellungJSON);
  }

  public void setBestellung(Bestellung bestellung) {
    try {
      setBestellungJSON(new ObjectMapper().writeValueAsString(bestellung));
    } catch (Exception e) {
      throw new RuntimeException("could not map POJO to JSON", e);
    }
  }

  /**
   * HELPER - could be asbtract super class
   */

  public Map<String, Object> asHashMap() {
    if (variableScope != null) {
      return variableScope.getVariables();
    } else if (variableMap != null) {
      return variableMap;
    }
    throw new RuntimeException("should never happen");
  }

  protected Object getVariable(String name) {
    if (variableScope != null) {
      return variableScope.getVariable(name);
    } else if (variableMap != null) {
      return variableMap.get(name);
    }
    throw new RuntimeException("should never happen");
  }

  protected void setVariable(String name, Object value) {
    if (variableScope != null) {
      variableScope.setVariable(name, value);
    } else if (variableMap != null) {
      variableMap.put(name, value);
    } else {
      throw new RuntimeException("should never happen");
    }
  }

}
