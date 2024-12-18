package rs.ac.uns.ftn.informatika.osa.pr10;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

@Stateless
@Local(PaymentLocal.class)
@Remote(Payment.class)
public class PaymentBean implements Payment {

  public boolean processCreditCard(CreditCard card) {
    return true;
  }
}
