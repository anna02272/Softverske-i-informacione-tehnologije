package rs.ac.uns.ftn.informatika.osa.pr19.session;

import rs.ac.uns.ftn.informatika.osa.pr19.entity.CreditCard;
import rs.ac.uns.ftn.informatika.osa.pr19.entity.PurchaseOrder;

public interface Purchase {
  
  public boolean processOrder(PurchaseOrder order, CreditCard card);

}
