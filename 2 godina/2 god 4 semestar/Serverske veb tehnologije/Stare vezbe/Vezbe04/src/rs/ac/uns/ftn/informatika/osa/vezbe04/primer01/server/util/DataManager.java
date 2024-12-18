package rs.ac.uns.ftn.informatika.osa.vezbe04.primer01.server.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import rs.ac.uns.ftn.informatika.osa.vezbe04.primer01.server.dto.Item;
import rs.ac.uns.ftn.informatika.osa.vezbe04.primer01.server.dto.Order;

public class DataManager {

	private static DataManager dm = new DataManager();

	private DataManager() {
		
	}

	public static DataManager getSingletonObject() {
		return dm;
	}
	
	public synchronized void initItems() {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(
					new BufferedOutputStream(
							new FileOutputStream("items.dat")));
			
			//There will be 10 instances of the AutomobilePart class
			oos.writeObject(new Integer(10));
			
			Item item = new Item(1,"Kvacilo",new BigDecimal(9000.00));
			oos.writeObject(item);
			
			item = new Item(2,"Pakne",new BigDecimal(2000.00));
			oos.writeObject(item);		
			
			item = new Item(3,"Termostat",new BigDecimal(6000.00));
			oos.writeObject(item);
			
			item = new Item(4,"Bobina",new BigDecimal(7000.00));
			oos.writeObject(item);
			
			item = new Item(5,"Filter ulja",new BigDecimal(600.00));
			oos.writeObject(item);
			
			item = new Item(6,"Filter vazduha",new BigDecimal(1000.00));
			oos.writeObject(item);
			
			item = new Item(7,"Unutrasnja guma",new BigDecimal(800.00));
			oos.writeObject(item);
			
			item = new Item(8,"Lambda sonda",new BigDecimal(10000.00));
			oos.writeObject(item);
			
			item = new Item(9,"Pneumatik",new BigDecimal(7000.00));
			oos.writeObject(item);
			
			item = new Item(10,"Sijalica",new BigDecimal(100.00));
			oos.writeObject(item);
			
			oos.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized Collection<Item> getItems() {
		Collection<Item> items = new ArrayList<Item>();
		Item item = null;
		Integer count = null;
		
		try {
			ObjectInputStream ois = 
					new ObjectInputStream(
							new BufferedInputStream(
									new FileInputStream("items.dat")));
			
			count = (Integer) ois.readObject();
			for (int i = 0; i < count.intValue(); i++) {
				item = (Item) ois.readObject();
				items.add(item);
			}
			
			ois.close();
		}  catch (Exception e) {
			e.printStackTrace();
		}
		
		return items;
	}
	
	public synchronized Collection<Order> getOrders(){
		Collection<Order> orders = new ArrayList<Order>();
		
		try {
			ObjectInputStream ois = new ObjectInputStream(
					new BufferedInputStream(
							new FileInputStream("orders.dat")));
			
			Integer count = (Integer) ois.readObject();
			for (int i = 0; i < count.intValue(); i++) {
				Order order = (Order) ois.readObject();
				orders.add(order);
			}
			
			ois.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (EOFException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return orders;
	}
	
	public synchronized void addOrder(Order newOrder) {
		Collection<Order> orders = new ArrayList<Order>();
		
		try {
			if (new File("orders.dat").exists()) {
				ObjectInputStream ois = new ObjectInputStream(
						new BufferedInputStream(
								new FileInputStream("orders.dat")));
				
				Integer count = (Integer) ois.readObject();
				for (int i = 0; i < count.intValue(); i++) {
					Order order = (Order) ois.readObject();
					orders.add(order);
				}
				
				ois.close();
			}
			
			orders.add(newOrder);
			
			ObjectOutputStream oos = new ObjectOutputStream(
					new BufferedOutputStream(
							new FileOutputStream("orders.dat")));
			
			oos.writeObject(new Integer(orders.size()));
			
			for (Order order: orders) {
				oos.writeObject(order);
			}
			
			oos.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (EOFException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
