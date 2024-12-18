package rs.ac.uns.ftn.informatika.osa.vezbe04.primer01.client;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import rs.ac.uns.ftn.informatika.osa.vezbe04.primer01.server.util.CreditCardType;

public class TestClient {

	public static void main(String[] args) {
	    
	    try {
	    	Context ctx = new InitialContext();
			
			// Visa
			ClientThread clientThread1 = new ClientThread("4929639723248899", CreditCardType.VISA, 1, ctx);
			ClientThread clientThread2 = new ClientThread("4916626593653014", CreditCardType.VISA, 2, ctx);
			ClientThread clientThread3 = new ClientThread("4716536950740407", CreditCardType.VISA, 3, ctx);
			
			// Mastercard
			ClientThread clientThread5 = new ClientThread("5121689198236260", CreditCardType.MASTER_CARD, 5, ctx);
			
			clientThread1.start();
			clientThread2.start();
			clientThread3.start();
			clientThread5.start();
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
}
