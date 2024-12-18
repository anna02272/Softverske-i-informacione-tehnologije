package rs.ac.uns.ftn.informatika.osa.spring.pr25.service;

import rs.ac.uns.ftn.informatika.osa.spring.pr25.dto.CreditCardDTO;
import rs.ac.uns.ftn.informatika.osa.spring.pr25.entity.PurchaseOrder;

public interface PurchaseServiceInterface {
  
  public boolean processOrder(PurchaseOrder order, CreditCardDTO card);

}
