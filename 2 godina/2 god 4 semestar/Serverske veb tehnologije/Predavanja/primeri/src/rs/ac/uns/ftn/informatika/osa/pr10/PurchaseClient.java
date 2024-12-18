package rs.ac.uns.ftn.informatika.osa.pr10;

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
          new ArrayList<Integer>());
      
      
      Context ctx = new InitialContext();
      Purchase purchase = (Purchase)ctx.lookup(
          "rs.ac.uns.ftn.informatika.osa.pr10.PurchaseBeanRemote");
      
      boolean status = purchase.processOrder(order);
      
      System.out.println(status ? "Order processed." : "Order rejected.");

    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

}
