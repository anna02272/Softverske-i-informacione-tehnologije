package rs.ac.uns.ftn.informatika.osa.vezbe03.primer02.client;

import javax.naming.Context;

import rs.ac.uns.ftn.informatika.osa.vezbe03.primer02.server.Counter;

public class CounterThread extends Thread {
	
	private String name = null;
	private Context ctx = null;
	
	public CounterThread(String name, Context ctx) {
		this.name = name;
		this.ctx = ctx;
	}

	public void run() {
		System.out.println("Thread " + name + " started.");

		try {
			Counter counter = (Counter)ctx.lookup("CounterBeanRemote");
			System.out.println("Thread " + name + " created EJB component.");
			
			System.out.println("Thread " + name + " executed remote method (count: " + counter.count() + ").");
			
			System.out.println("Thread " + name + " executed remote method (count: " + counter.count() + ").");
			
			System.out.println("Thread " + name + " executed remote method (count: " + counter.count() + ").");
			
			System.out.println("Thread " + name + " executed remote method (count: " + counter.count() + ").");
			
			System.out.println("Thread " + name + " executed remote method (count: " + counter.count() + ").");
			
			counter.remove();
			System.out.println("Thread " + name + " removed EJB component.");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		System.out.println("Thread " + name + " stopped.");
	}
}
