package rs.ac.uns.ftn.informatika.osa.vezbe02.primer01;

import java.rmi.Naming;

public class Client {
	public static void main(String[] args) {
		try {
			System.out.println("Connecting to server...");
			IServer server = (IServer) Naming
					.lookup("//localhost:1099/ServerObject");
			System.out.println("Count: " + server.count());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
