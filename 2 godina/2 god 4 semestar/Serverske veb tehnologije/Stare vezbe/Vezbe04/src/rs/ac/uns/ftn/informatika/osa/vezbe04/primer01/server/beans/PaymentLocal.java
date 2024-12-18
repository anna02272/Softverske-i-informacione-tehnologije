package rs.ac.uns.ftn.informatika.osa.vezbe04.primer01.server.beans;

import java.math.BigDecimal;

import rs.ac.uns.ftn.informatika.osa.vezbe04.primer01.server.util.CreditCardType;

/**
 * Isti interfejs ne moze biti i local i remote, zato postoje dva interfejsa.
 */
public interface PaymentLocal {
	
	public boolean buy(String creditCardNumber, CreditCardType creditCardType, BigDecimal price);
	
}
