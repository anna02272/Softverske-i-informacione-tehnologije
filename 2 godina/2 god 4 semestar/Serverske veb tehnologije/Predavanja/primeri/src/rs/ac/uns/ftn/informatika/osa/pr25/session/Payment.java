package rs.ac.uns.ftn.informatika.osa.pr25.session;

import rs.ac.uns.ftn.informatika.osa.pr25.entity.CreditCard;

public interface Payment {

  public boolean processCreditCard(CreditCard card);
  
}
