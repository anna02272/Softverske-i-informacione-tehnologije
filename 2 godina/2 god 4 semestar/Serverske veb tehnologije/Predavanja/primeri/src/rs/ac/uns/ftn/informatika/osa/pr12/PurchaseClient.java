package rs.ac.uns.ftn.informatika.osa.pr12;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;

public class PurchaseClient {

  public static void main(String[] args) {
	  System.setProperty("java.naming.factory.initial", "org.apache.openejb.client.RemoteInitialContextFactory");
		System.setProperty("java.naming.provider.url", "http://127.0.0.1:8080/tomee/ejb");
	try {
      CreditCard card = new CreditCard(12, 2010, "John Doe", "411111111111");
      Order order = new Order(card, new Date(), new ArrayList<String>(), 
          new ArrayList<Integer>(), new BigDecimal(200));
      
      
      Context ctx = new InitialContext();
      Purchase purchase = (Purchase)ctx.lookup(
          "rs.ac.uns.ftn.informatika.osa.pr12.PurchaseBeanRemote");
      
      boolean status = purchase.processOrder(order);
      
      System.out.println(status ? "Order processed." : "Order rejected.");

    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

}
