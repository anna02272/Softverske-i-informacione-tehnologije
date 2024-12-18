package rs.ac.uns.ftn.informatika.osa.pr05;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

public class HelloServer {
  public static void main(String[] args) {
    try {
      HelloImpl helloRef = new HelloImpl();
      Properties env = new Properties();
      env.put("java.naming.factory.initial", 
          "com.sun.jndi.cosnaming.CNCtxFactory");
      env.put("java.naming.provider.url",
          "iiop://localhost:1050");
      Context ctx = new InitialContext(env);
      ctx.rebind("HelloService", helloRef);
      System.out.println("HelloServer started.");
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
