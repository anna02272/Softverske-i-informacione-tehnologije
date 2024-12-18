package rs.ac.uns.ftn.informatika.osa.spring.pr25.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.osa.spring.pr25.entity.PurchaseOrder;
import rs.ac.uns.ftn.informatika.osa.spring.pr25.entity.User;
import rs.ac.uns.ftn.informatika.osa.spring.pr25.repository.UserRepository;

@Service
public class UserService implements UserServiceInterface{

	@Autowired
	UserRepository userRepository;
	
	@Override
	public User findByUsernameAndPassword(String username, String password){
		User user = userRepository.findByUsername(username);
		if(user.getPassword().equals(password))
			return user;
		else
			return null;
	}
	
	@Override
	public void add(User user, PurchaseOrder order){
		user = userRepository.findOne(user.getId());
		user.getOrders().add(order);
		userRepository.save(user);
	}

}
