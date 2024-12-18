package rs.ac.uns.ftn.informatika.osa.vezbe04.primer01.client;

import java.util.Collection;
import java.util.Enumeration;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import rs.ac.uns.ftn.informatika.osa.vezbe04.primer01.server.beans.Admin;
import rs.ac.uns.ftn.informatika.osa.vezbe04.primer01.server.dto.Item;
import rs.ac.uns.ftn.informatika.osa.vezbe04.primer01.server.dto.Order;

public class GetOrders {
	public static void main(String[] args) {
		try {
			Context ctx = new InitialContext();
		    Admin admin = (Admin)ctx.lookup("AdminBeanRemote");
		    
			Collection<Order> orders = admin.getOrders();
			for (Order order: orders) {
				System.out.println("The client with the credit card number " + order.getCreditCardNumber() + " ordered following items:");
				
				Enumeration<Item> keys = order.getShoppingCart().keys();
				Item item = null;
				while (keys.hasMoreElements()) {
					item = (Item) keys.nextElement();
					System.out.println("Number:" + item.getNumber()
							+ " Name:" + item.getName()
							+ " Price:" + item.getPrice()
							+ " Quantity:" + order.getShoppingCart().get(item));
				}
			}
		} catch (NamingException e) {
			e.printStackTrace();
		} 
	}
}
