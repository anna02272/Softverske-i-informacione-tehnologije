package rs.ac.uns.ftn.informatika.osa.spring.pr25.service;

import rs.ac.uns.ftn.informatika.osa.spring.pr25.dto.CreditCardDTO;

public interface PaymentServiceInterface {

  public boolean processCreditCard(CreditCardDTO card);
  
}
