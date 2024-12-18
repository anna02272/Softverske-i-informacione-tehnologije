package rs.ac.uns.ftn.informatika.osa.spring.pr12;

import org.springframework.stereotype.Component;

@Component
public class PaymentBean implements Payment {

  public boolean processCreditCard(CreditCard card) {
    return true;
  }
}
