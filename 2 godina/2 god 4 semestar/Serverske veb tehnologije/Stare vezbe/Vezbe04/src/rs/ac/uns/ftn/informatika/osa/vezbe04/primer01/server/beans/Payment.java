package rs.ac.uns.ftn.informatika.osa.vezbe04.primer01.server.beans;

import java.math.BigDecimal;

import rs.ac.uns.ftn.informatika.osa.vezbe04.primer01.server.util.CreditCardType;

public interface Payment {

   public boolean buy(String creditCardNumber, CreditCardType creditCardType, BigDecimal price);

}
