package rs.ac.uns.ftn.informatika.osa.vezbe04.primer01.server.dto;

import java.io.Serializable;
import java.util.Hashtable;

import rs.ac.uns.ftn.informatika.osa.vezbe04.primer01.server.util.CreditCardType;

public class Order implements Serializable {

	private static final long serialVersionUID = -68236390530584136L;
	
	private String creditCardNumber = null;
	private CreditCardType creditCardType = CreditCardType.VISA;
	private Hashtable<Item, Integer> shoppingCart = null;
	
	public Order() {
		this.shoppingCart = new Hashtable<Item, Integer>();
	}
	
	public Order(Hashtable<Item, Integer> shoppingCart, String creditCardNumber, CreditCardType creditCardType) {
		this.creditCardNumber = creditCardNumber;
		this.creditCardType = creditCardType;
		this.shoppingCart = shoppingCart;

	}
	public String getCreditCardNumber() {
		return creditCardNumber;
	}
	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}
	public Hashtable<Item, Integer> getShoppingCart() {
		return shoppingCart;
	}
	public void setShoppingCart(Hashtable<Item, Integer> shoppingCart) {
		this.shoppingCart = shoppingCart;
	}
	public CreditCardType getCreditCardType() {
		return creditCardType;
	}
	public void setCreditCardType(CreditCardType creditCardType) {
		this.creditCardType = creditCardType;
	}
}
