package rs.ac.uns.ftn.informatika.osa.vezbe04.primer01.server.beans;

import java.util.Collection;

import rs.ac.uns.ftn.informatika.osa.vezbe04.primer01.server.dto.Order;

public interface Admin {

	public void initItems();

	public Collection<Order> getOrders();
}
