package rs.ac.uns.ftn.informatika.osa.vezbe03.primer01.server;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

@Stateless(name="HelloBean")
@Local(HelloLocal.class)
@Remote(Hello.class)
public class HelloBean implements Hello {
  
  public String hello(String name) {
    System.out.println("[HelloBean] Called by: " + name);
    return "Hello, " + name;
  }
  
}
