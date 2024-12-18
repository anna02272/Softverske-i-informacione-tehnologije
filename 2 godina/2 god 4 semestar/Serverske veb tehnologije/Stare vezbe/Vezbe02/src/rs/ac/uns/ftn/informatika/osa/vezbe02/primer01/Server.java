package rs.ac.uns.ftn.informatika.osa.vezbe02.primer01;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Server implements IServer {

	private static final long serialVersionUID = 1L;

	public Server() throws RemoteException {
		UnicastRemoteObject.exportObject(this);
	}

	public int count() throws RemoteException {
		return ++count;
	}

	public static void main(String[] args) {
		try {
			Server server = new Server();
			Naming.rebind("//localhost:1099/ServerObject", server);
			System.out.println("Server started.");			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private int count = 0;
}
