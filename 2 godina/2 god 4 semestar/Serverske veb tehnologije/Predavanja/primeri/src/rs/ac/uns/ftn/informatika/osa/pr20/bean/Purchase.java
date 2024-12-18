package rs.ac.uns.ftn.informatika.osa.pr20.bean;

import rs.ac.uns.ftn.informatika.osa.pr20.CreditCard;
import rs.ac.uns.ftn.informatika.osa.pr20.PurchaseOrder;

public interface Purchase {
  
  public boolean processOrder(PurchaseOrder order, CreditCard card);

}
