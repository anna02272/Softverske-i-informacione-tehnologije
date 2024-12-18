package rs.ac.uns.ftn.informatika.osa.spring.pr18;

import org.springframework.stereotype.Service;

@Service
public class PaymentBean implements Payment {

  public boolean processCreditCard(CreditCard card) {
    return true;
  }
}
