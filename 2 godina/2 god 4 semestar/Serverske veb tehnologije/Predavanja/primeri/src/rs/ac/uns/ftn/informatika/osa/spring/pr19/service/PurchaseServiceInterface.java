package rs.ac.uns.ftn.informatika.osa.spring.pr19.service;

import rs.ac.uns.ftn.informatika.osa.spring.pr19.entity.CreditCard;
import rs.ac.uns.ftn.informatika.osa.spring.pr19.entity.PurchaseOrder;

public interface PurchaseServiceInterface {
  
  public boolean processOrder(PurchaseOrder order, CreditCard card);

}
