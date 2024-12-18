package rs.ac.uns.ftn.informatika.osa.pr07;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class HelloClient {
  
  public static void main(String[] args) {
    System.setProperty("java.naming.factory.initial", "org.apache.openejb.client.RemoteInitialContextFactory");
    System.setProperty("java.naming.provider.url", "http://127.0.0.1:8080/tomee/ejb");
    try {
      Context ctx = new InitialContext();
      Hello hello = (Hello)ctx.lookup(
          "rs.ac.uns.ftn.informatika.osa.pr07.HelloBeanRemote");
      
      String response = hello.hello("Branko");
      
      System.out.println("Response: " + response);
    } catch (NamingException ex) {
      ex.printStackTrace();
    }
  }
}
