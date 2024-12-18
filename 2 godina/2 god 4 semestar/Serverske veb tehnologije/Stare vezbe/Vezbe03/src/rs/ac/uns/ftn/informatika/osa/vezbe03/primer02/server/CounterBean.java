package rs.ac.uns.ftn.informatika.osa.vezbe03.primer02.server;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Local;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Remote;
import javax.ejb.Remove;
import javax.ejb.Stateful;

@Stateful
@Local(CounterLocal.class)
@Remote(Counter.class)
public class CounterBean implements Counter {
	
	private int counter;
	
	public int count() {
		return ++counter;
	}
	
	@Remove
	public void remove() {
	  System.out.println("[CounterBean] @Remove");
	}
	  
	@PostConstruct
	public void construct() {
	  System.out.println("[CounterBean] @PostConstruct");
	}
	  
	@PreDestroy
	public void destroy() {
	  System.out.println("[CounterBean] @PreDestroy");
	}
	
	@PostActivate
	public void activate() {
	  System.out.println("[CounterBean] @PostActivate");
	}
	  
	@PrePassivate
	public void passivate() {
	  System.out.println("[CountBean] @PrePassivate");
	}
}
