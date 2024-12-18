package rs.ac.uns.ftn.informatika.osa.vezbe02.primer02;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ComputeEngine extends UnicastRemoteObject implements Compute {

	private static final long serialVersionUID = -8216433455936844248L;

	public ComputeEngine() throws RemoteException {
		super();
	}

	public Object executeTask(Task t) {
		return t.execute();
	}

	public static void main(String[] args) {
		System.setSecurityManager(new RMISecurityManager());
		try {
			Compute engine = new ComputeEngine();
			Naming.rebind("//localhost:1099/Compute", engine);
			System.out.println("ComputeEngine started.");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
