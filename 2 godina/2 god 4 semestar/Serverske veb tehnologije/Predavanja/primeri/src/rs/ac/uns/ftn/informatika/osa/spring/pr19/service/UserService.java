package rs.ac.uns.ftn.informatika.osa.spring.pr19.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.osa.spring.pr19.entity.PurchaseOrder;
import rs.ac.uns.ftn.informatika.osa.spring.pr19.entity.User;
import rs.ac.uns.ftn.informatika.osa.spring.pr19.repository.UserRepository;

@Service
public class UserService implements UserServiceInterface{

	@Autowired
	UserRepository userRepository;
	
	@Override
	public User findByUsernameAndPassword(String username, String password){
		User user = userRepository.findByUsername(username);
		if(user!=null && user.getPassword()!=null && user.getPassword().equals(password))
			return user;
		else
			return null;
	}
	
	@Override
	public void add(User user, PurchaseOrder order){
		user = userRepository.findOne(user.getId());
		order.setUser(user);
		user.getOrders().add(order);
		userRepository.save(user);
	}

}
