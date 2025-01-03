package rs.ac.uns.ftn.informatika.osa.pr25.session;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import rs.ac.uns.ftn.informatika.osa.pr25.entity.CreditCard;


@Stateless
@Local(PaymentLocal.class)
@Remote(Payment.class)
public class PaymentBean implements Payment {

  @Interceptors(Logger.class)
  public boolean processCreditCard(CreditCard card) {
    return true;
  }
}
