package de.codecentric.wundershop.fakeshopservice;

import org.apache.log4j.Logger;

/**
 * Example of a transaction handler, similar to open session in view pattern.
 */
public class TransactionHandler {
    private static Logger logger = Logger.getLogger(TransactionHandler.class);
    /**
     * Called before service method is executed.
     */
    public void beginTransaction() {
	logger.info("beginTransaction");
    }
    
    /**
     * Called on successful execution of service method.
     */
    public void commitTransaction() {
	logger.info("commitTransaction");
    }
    
    /**
     * Called when service method has thrown an Exception.
     */
    public void rollbackTransaction() {
	logger.info("rollbackTransaction");
    }
}
