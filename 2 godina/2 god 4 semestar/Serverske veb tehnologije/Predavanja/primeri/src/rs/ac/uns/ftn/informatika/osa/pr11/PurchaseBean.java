package rs.ac.uns.ftn.informatika.osa.pr11;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

@Stateless
@Remote(Purchase.class)
@Local(PurchaseLocal.class)
public class PurchaseBean implements Purchase {
  
  public boolean processOrder(Order order) {
    boolean paymentOK = payment.processCreditCard(order.getCreditCard());
    return paymentOK;
  }
  
  @EJB
  private PaymentLocal payment;
}
