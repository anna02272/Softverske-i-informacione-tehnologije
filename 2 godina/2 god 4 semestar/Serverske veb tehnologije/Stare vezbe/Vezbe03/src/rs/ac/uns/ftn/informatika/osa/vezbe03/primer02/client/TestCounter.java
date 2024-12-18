package rs.ac.uns.ftn.informatika.osa.vezbe03.primer02.client;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class TestCounter {

    public static void main(String args[]) {
    	
        try {
        	Context ctx = new InitialContext();
    		
    		CounterThread t1 = new CounterThread("Thread1", ctx);
    		CounterThread t2 = new CounterThread("Thread2", ctx);
    		CounterThread t3 = new CounterThread("Thread3", ctx);
        	
    		t1.start();
    		t2.start();
    		t3.start();
    	} catch (NamingException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    }
}