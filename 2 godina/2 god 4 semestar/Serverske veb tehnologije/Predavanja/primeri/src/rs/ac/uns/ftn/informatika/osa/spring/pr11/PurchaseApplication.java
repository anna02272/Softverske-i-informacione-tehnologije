package rs.ac.uns.ftn.informatika.osa.spring.pr11;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication  // same as @Configuration @EnableAutoConfiguration @ComponentScan
public class PurchaseApplication implements CommandLineRunner 
{
   @Autowired
   private Purchase purchase; 
	
	public static void main( String[] args )
    {
    	SpringApplication.run(PurchaseApplication.class, args);

    	
    }
    
    @Override
	public void run(String... args) {
    	try {
    	      CreditCard card = new CreditCard(12, 2010, "John Doe", "411111111111");
    	      Order order = new Order(card, new Date(), new ArrayList<String>(), 
    	          new ArrayList<Integer>(), new BigDecimal(200));
    	      
    	     boolean status = purchase.processOrder(order);
    	      
    	      System.out.println(status ? "Order processed." : "Order rejected.");

    	    } catch (Exception ex) {
    	      ex.printStackTrace();
    	    }
	}
}
