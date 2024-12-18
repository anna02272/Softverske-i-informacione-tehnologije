package rs.ac.uns.ftn.informatika.osa.spring.pr15;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication  // same as @Configuration @EnableAutoConfiguration @ComponentScan
public class UserJDBCApplication implements CommandLineRunner 
{
	@Autowired
	private UserJDBC userJDBC; 
	
	public static void main( String[] args )
    {
    	SpringApplication.run(UserJDBCApplication.class, args);
    	
    	
    }
    
    @Override
	public void run(String... args) {
    	  User zika = new User(1, "Zika", "Zikic");
	  	  User pera = new User(2, "Pera", "Peric");
	  	  User laza = new User(3, "Laza", "Lazic");
	  	  
	  	  userJDBC.insert(zika);
	  	  userJDBC.insert(pera);
	  	  userJDBC.insert(laza);
	  	  
	  	  pera = userJDBC.load(2);
	  	  pera.setPassword("***");
	  	  
	  	  userJDBC.update(pera);
	}
    

}

