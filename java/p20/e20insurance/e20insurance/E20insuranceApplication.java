package p20.e20insurance.e20insurance;
  
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;


/* logging */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


 
@SpringBootApplication
public class E20insuranceApplication { 
 
	public static void main(String[] args) {
 
		SpringApplication.run(E20insuranceApplication.class, args);
	} 

	@EventListener
    public void handleContextRefreshEvent(ContextStartedEvent ctxStartEvt)  
	{
		var message1 = "E20Insurance...";
		var message2 = "Application context started....";   
		final Logger LOGGER = LoggerFactory.getLogger(E20insuranceApplication.class);
		LOGGER.info(message1);
		LOGGER.info(message2);

	}  

}
