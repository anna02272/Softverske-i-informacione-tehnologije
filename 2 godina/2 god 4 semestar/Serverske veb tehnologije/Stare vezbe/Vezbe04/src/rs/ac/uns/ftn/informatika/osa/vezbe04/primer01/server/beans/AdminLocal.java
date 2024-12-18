package rs.ac.uns.ftn.informatika.osa.vezbe04.primer01.server.beans;

import java.util.Collection;

import rs.ac.uns.ftn.informatika.osa.vezbe04.primer01.server.dto.Order;

/**
 * Isti interfejs ne moze biti i local i remote, zato postoje dva interfejsa.
 */
public interface AdminLocal {

	public void initItems();

	public Collection<Order> getOrders();
}