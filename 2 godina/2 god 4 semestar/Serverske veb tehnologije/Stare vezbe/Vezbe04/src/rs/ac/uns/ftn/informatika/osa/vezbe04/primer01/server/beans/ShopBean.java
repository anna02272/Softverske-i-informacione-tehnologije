package rs.ac.uns.ftn.informatika.osa.vezbe04.primer01.server.beans;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.interceptor.Interceptors;

import rs.ac.uns.ftn.informatika.osa.vezbe04.primer01.server.dto.Item;
import rs.ac.uns.ftn.informatika.osa.vezbe04.primer01.server.dto.Order;
import rs.ac.uns.ftn.informatika.osa.vezbe04.primer01.server.util.CreditCardType;
import rs.ac.uns.ftn.informatika.osa.vezbe04.primer01.server.util.DataManager;

@Stateful
@Local(ShopLocal.class)
@Remote(Shop.class)
@Interceptors(rs.ac.uns.ftn.informatika.osa.vezbe04.primer01.server.util.Logger.class)
public class ShopBean implements Shop {

	private Hashtable<Item, Integer> shoppingCart = new Hashtable<Item, Integer>();

	@EJB
	private PaymentLocal payment;
	
	@PostConstruct
	public void init(){
		System.out.println("init()");
	}
	
	/**
	 * 
	 */
	public Collection<Item> getItems() {
		DataManager dm = DataManager.getSingletonObject();
		return dm.getItems();
	}

	/**
	 * 
	 */
	public void addItemToShoppingCart(Item item, int count) {
		shoppingCart.put(item, new Integer(count));
	}

	/**
	 * 
	 */
	public Hashtable<Item, Integer> getShoppingCart() {
		return shoppingCart;
	}

	/**
	 * 
	 */
	public boolean purchase(String creditCardNumber, CreditCardType creditCardType) {
		BigDecimal price = new BigDecimal(0);
		Enumeration<Item> items = shoppingCart.keys();
		while (items.hasMoreElements()) {
			Item item = (Item) items.nextElement();
			price = price.add(item.getPrice().multiply(new BigDecimal(shoppingCart.get(item))));
		}
		
		if (payment.buy(creditCardNumber, creditCardType, price)) {
			// Save the order in 'orders.dat' file
			DataManager dm = DataManager.getSingletonObject();
			dm.addOrder(new Order(shoppingCart, creditCardNumber, creditCardType));
			return true;
		} else {
			return false;
		}
	}

	@Remove
	public void remove(){
		shoppingCart = new Hashtable<Item, Integer>();
	}
	
}
