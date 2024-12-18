package rs.ac.uns.ftn.informatika.osa.pr13;

import javax.naming.Context;
import javax.naming.InitialContext;

public class CountClient {

  public static void main(String[] args) {
	  System.setProperty("java.naming.factory.initial", "org.apache.openejb.client.RemoteInitialContextFactory");
		System.setProperty("java.naming.provider.url", "http://127.0.0.1:8080/tomee/ejb");
	try {
      Context ctx = new InitialContext();
      Count c = (Count)ctx.lookup(
      "rs.ac.uns.ftn.informatika.osa.pr13.CountBeanRemote");
  
      for (int i = 0; i < 10; i++) {
        System.out.println("count: " + c.count());
        Thread.sleep(100);
      }
      
      c.remove();
  
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
