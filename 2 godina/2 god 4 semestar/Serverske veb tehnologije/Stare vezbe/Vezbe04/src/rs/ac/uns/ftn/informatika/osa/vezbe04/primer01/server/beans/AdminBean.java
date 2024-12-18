package rs.ac.uns.ftn.informatika.osa.vezbe04.primer01.server.beans;

import java.util.Collection;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import rs.ac.uns.ftn.informatika.osa.vezbe04.primer01.server.dto.Order;
import rs.ac.uns.ftn.informatika.osa.vezbe04.primer01.server.util.DataManager;

@Stateless
@Local(AdminLocal.class)
@Remote(Admin.class)
@Interceptors(rs.ac.uns.ftn.informatika.osa.vezbe04.primer01.server.util.Logger.class)
public class AdminBean implements Admin {

	public void initItems() {
		DataManager dm = DataManager.getSingletonObject();
		dm.initItems();
	}
	
	public Collection<Order> getOrders(){
		DataManager dm = DataManager.getSingletonObject();
		return dm.getOrders();
	}
	
}
