package rs.ac.uns.ftn.informatika.osa.vezbe04.primer01.server.beans;

import java.math.BigDecimal;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import rs.ac.uns.ftn.informatika.osa.vezbe04.primer01.server.util.CreditCardType;
import rs.ac.uns.ftn.informatika.osa.vezbe04.primer01.server.util.CreditCardValidator;

@Stateless
@Local(PaymentLocal.class)
@Remote(Payment.class)
@Interceptors(rs.ac.uns.ftn.informatika.osa.vezbe04.primer01.server.util.Logger.class)
public class PaymentBean implements rs.ac.uns.ftn.informatika.osa.vezbe04.primer01.server.beans.Payment {
	
	/**
	 * 
	 */
	public boolean buy(String creditCardNumber, CreditCardType creditCardType, BigDecimal price) {
		return CreditCardValidator.isValid(creditCardNumber, creditCardType);
	}
}
