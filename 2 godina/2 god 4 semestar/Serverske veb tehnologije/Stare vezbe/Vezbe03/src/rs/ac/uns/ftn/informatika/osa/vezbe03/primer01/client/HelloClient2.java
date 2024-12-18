package rs.ac.uns.ftn.informatika.osa.vezbe03.primer01.client;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import rs.ac.uns.ftn.informatika.osa.vezbe03.primer01.server.Hello;


public class HelloClient2 {
  
	public static void main(String[] args) {
		try {
			InitialContext ctx = new InitialContext();
			Hello hello = (Hello) ctx.lookup("HelloBeanRemote");
			String response = hello.hello("Pera");
			System.out.println("Response: " + response);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}