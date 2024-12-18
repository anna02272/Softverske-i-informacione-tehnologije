package rs.ac.uns.ftn.informatika.osa.spring.pr19.service;

import rs.ac.uns.ftn.informatika.osa.spring.pr19.entity.CreditCard;

public interface PaymentServiceInterface {

  public boolean processCreditCard(CreditCard card);
  
}
