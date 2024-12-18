package rs.ac.uns.ftn.informatika.osa.vezbe04.primer01.client;

import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Random;

import javax.naming.Context;
import javax.naming.NamingException;

import rs.ac.uns.ftn.informatika.osa.vezbe04.primer01.server.beans.Shop;
import rs.ac.uns.ftn.informatika.osa.vezbe04.primer01.server.dto.Item;
import rs.ac.uns.ftn.informatika.osa.vezbe04.primer01.server.util.CreditCardType;

public class ClientThread extends Thread {

	private String creditCardNumber = null;
	private CreditCardType creditCardType = CreditCardType.VISA;
	private int clientId = 0;
	private Context ctx = null;
	
	public ClientThread(String creditCardNumber, CreditCardType creditCardType, int clientId, Context ctx) {
		this.creditCardNumber = creditCardNumber;
		this.creditCardType = creditCardType;
		this.clientId = clientId;
		this.ctx = ctx;
	}

	public void run() {
		try {
			// Get the list from the server
			System.out.println("Client " + clientId + " started.");

			Shop shop = (Shop) ctx.lookup("ShopBeanRemote");
		    
			Collection<Item> items = shop.getItems();

			System.out.println("Client " + clientId + " retreived items.");

			// Order three products
			Random random = new Random();

			int randomNumber = random.nextInt(items.size());
			shop.addItemToShoppingCart((Item) items.toArray()[randomNumber], randomNumber + 1);

			randomNumber = random.nextInt(items.size());
			shop.addItemToShoppingCart((Item) items.toArray()[randomNumber], randomNumber + 1);

			randomNumber = random.nextInt(items.size());
			shop.addItemToShoppingCart((Item) items.toArray()[randomNumber], randomNumber + 1);

			System.out.println("The client " + clientId + " ordered three parts.");

			// Order the shopping cart
			boolean orderResult = shop.purchase(creditCardNumber, creditCardType);

			System.out.println("The order for the client " + clientId + " is " + (orderResult ? "successful" : "unsuccessful") + "!");

			// Get the shopping cart
			Hashtable<Item, Integer> shoppingCart = shop.getShoppingCart();
			Enumeration<Item> keys = shoppingCart.keys();
			System.out.println("The client " + clientId + " ordered following parts:");
			while (keys.hasMoreElements()) {
				Item item = (Item) keys.nextElement();
				System.out.println("Number:" + item.getNumber()
						+ " Name:" + item.getName()
						+ " Price:" + item.getPrice()
						+ " Quantity:" + shoppingCart.get(item));
			}
			
			shop.remove();

		} catch (NamingException e) {
			e.printStackTrace();
		} 
	}
}
