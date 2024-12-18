package rs.ac.uns.ftn.informatika.osa.spring.pr25.service;

import rs.ac.uns.ftn.informatika.osa.spring.pr25.entity.PurchaseOrder;
import rs.ac.uns.ftn.informatika.osa.spring.pr25.entity.User;

public interface UserServiceInterface {

	User findByUsernameAndPassword(String username, String password);
	
	void add(User user, PurchaseOrder order);
}
