package rs.ac.uns.ftn.informatika.osa.pr10;

import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

@Stateless
@Remote(Purchase.class)
@Local(PurchaseLocal.class)
public class PurchaseBean implements Purchase {
  
  public boolean processOrder(Order order) {
	  boolean paymentOK = payment.processCreditCard(order.getCreditCard());
    return paymentOK;
  }
  
  @PostConstruct
  public void construct() {
    try {
    	Properties p = new Properties();
    	p.put("java.naming.factory.initial", "org.apache.openejb.client.LocalInitialContextFactory");
      Context c = new InitialContext(p);
      payment = (PaymentLocal)c.lookup(
          "rs.ac.uns.ftn.informatika.osa.pr10.PaymentBeanLocal");
    } catch (NamingException ex) {
      ex.printStackTrace();
    }
    
  }
  
  private PaymentLocal payment;
}
