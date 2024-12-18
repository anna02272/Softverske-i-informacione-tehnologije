package rs.ac.uns.ftn.informatika.osa.spring.pr12;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PurchaseBean implements Purchase {
  
  public boolean processOrder(Order order) {
    boolean paymentOK = payment.processCreditCard(order.getCreditCard());
    return paymentOK;
  }
  
  @Autowired
  private Payment payment;
}
