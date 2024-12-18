package rs.ac.uns.ftn.informatika.osa.spring.pr19.service;

import rs.ac.uns.ftn.informatika.osa.spring.pr19.entity.PurchaseOrder;
import rs.ac.uns.ftn.informatika.osa.spring.pr19.entity.User;

public interface UserServiceInterface {

	User findByUsernameAndPassword(String username, String password);
	
	void add(User user, PurchaseOrder order);
}
