package rs.ac.uns.ftn.informatika.osa.pr05;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;

public class HelloClient {
  public static void main(String[] args) {
    try {
      Properties env = new Properties();
      env.put("java.naming.factory.initial", 
          "com.sun.jndi.cosnaming.CNCtxFactory");
      env.put("java.naming.provider.url",
          "iiop://localhost:1050");
      Context ctx = new InitialContext(env);
      Object objRef = ctx.lookup("HelloService");
      
      // posto se pristupa objektu preko RMI-over-IIOP, mora da se uradi
      // poseban cast pronadjenog objekta u RMI interfejs
      HelloI hello = (HelloI)PortableRemoteObject.narrow(objRef, HelloI.class);
      hello.sayHello("Klijent Pera");
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
