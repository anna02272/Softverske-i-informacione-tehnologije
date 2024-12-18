package rs.ac.uns.ftn.informatika.osa.spring.pr19.service;

import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.osa.spring.pr19.entity.CreditCard;

@Service
public class PaymentService implements PaymentServiceInterface {

  @Override
	public boolean processCreditCard(CreditCard card) {
    return true;
  }
}
