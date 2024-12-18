package rs.ac.uns.ftn.informatika.osa.pr25.session;

import rs.ac.uns.ftn.informatika.osa.pr25.entity.CreditCard;
import rs.ac.uns.ftn.informatika.osa.pr25.entity.PurchaseOrder;

public interface Purchase {
  
  public boolean processOrder(PurchaseOrder order, CreditCard card);

}
