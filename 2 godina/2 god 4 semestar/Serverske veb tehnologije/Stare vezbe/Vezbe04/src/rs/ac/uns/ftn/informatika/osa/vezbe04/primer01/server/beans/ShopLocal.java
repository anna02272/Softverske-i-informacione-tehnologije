package rs.ac.uns.ftn.informatika.osa.vezbe04.primer01.server.beans;

import java.util.Collection;
import java.util.Hashtable;

import rs.ac.uns.ftn.informatika.osa.vezbe04.primer01.server.dto.Item;
import rs.ac.uns.ftn.informatika.osa.vezbe04.primer01.server.util.CreditCardType;

/**
 * Isti interfejs ne moze biti i local i remote, zato postoje dva interfejsa.
 */
public interface ShopLocal {

	   public Collection<Item> getItems();

	   public void addItemToShoppingCart(Item item, int count);

	   public Hashtable<Item, Integer> getShoppingCart();

	   public boolean purchase(String creditCardNumber, CreditCardType creditCardType);

	   public void remove();  
}
