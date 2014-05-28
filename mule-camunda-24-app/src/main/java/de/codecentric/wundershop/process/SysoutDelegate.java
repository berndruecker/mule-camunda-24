package de.codecentric.wundershop.process;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class SysoutDelegate implements JavaDelegate {

	public void execute(DelegateExecution execution) throws Exception {
		System.out.println("Execution " + execution.getId() + " passed by " + execution.getCurrentActivityName());
	}

}
