package rs.ac.uns.ftn.informatika.osa.pr20.container;

import rs.ac.uns.ftn.informatika.osa.pr19.entity.CreditCard;
import rs.ac.uns.ftn.informatika.osa.pr19.entity.PurchaseOrder;

public interface Purchase {
  
  public boolean processOrder(PurchaseOrder order, CreditCard card);

}
