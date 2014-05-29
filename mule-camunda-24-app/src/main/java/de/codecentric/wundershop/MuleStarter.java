package de.codecentric.wundershop;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.Logger;
import org.mule.api.MuleContext;
import org.mule.api.MuleException;
import org.mule.config.spring.SpringXmlConfigurationBuilder;
import org.mule.context.DefaultMuleContextFactory;

@WebListener
public class MuleStarter implements ServletContextListener {
  
  private Logger logger = Logger.getLogger(MuleStarter.class);
  public static MuleContext muleContext;

  @Override
  public void contextInitialized(ServletContextEvent event) {
    DefaultMuleContextFactory muleContextFactory = new DefaultMuleContextFactory();
    try {
      logger.info("Starting Mule...");
      String[] flows = new String[] { "wundershop.xml", "fakeshopsystem.xml" };
      SpringXmlConfigurationBuilder configBuilder = new SpringXmlConfigurationBuilder(flows);
      muleContext = muleContextFactory.createMuleContext(configBuilder);
      muleContext.start();
      logger.info("Mule started");
    } catch (MuleException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void contextDestroyed(ServletContextEvent event) {
    try {
      logger.info("Stopping Mule...");
      muleContext.stop();
      muleContext.getWorkManager().dispose();
      muleContext.dispose();
      logger.info("Mule stopped");
    } catch (MuleException e) {
      throw new RuntimeException(e);
    } finally {
      muleContext = null;
    }
  }
}
