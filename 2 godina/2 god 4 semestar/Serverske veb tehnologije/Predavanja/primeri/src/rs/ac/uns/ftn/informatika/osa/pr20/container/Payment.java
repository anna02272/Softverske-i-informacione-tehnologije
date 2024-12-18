package rs.ac.uns.ftn.informatika.osa.pr20.container;

import rs.ac.uns.ftn.informatika.osa.pr19.entity.CreditCard;

public interface Payment {

  public boolean processCreditCard(CreditCard card) throws MyException;
  
}
