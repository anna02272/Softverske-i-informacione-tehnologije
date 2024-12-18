package rs.ac.uns.ftn.informatika.osa.vezbe03.primer01.client;

import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import rs.ac.uns.ftn.informatika.osa.vezbe03.primer01.server.Hello;


public class HelloClient1 {
  
	public static void main(String[] args) {
		Properties p = new Properties();
		p.put("java.naming.factory.initial", "org.apache.openejb.client.RemoteInitialContextFactory");
		p.put("java.naming.provider.url", "http://127.0.0.1:8080/tomee/ejb");
		
		try {
			InitialContext ctx = new InitialContext(p);
			Hello hello = (Hello) ctx.lookup("HelloBeanRemote");
			String response = hello.hello("Pera");
			System.out.println("Response: " + response);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}