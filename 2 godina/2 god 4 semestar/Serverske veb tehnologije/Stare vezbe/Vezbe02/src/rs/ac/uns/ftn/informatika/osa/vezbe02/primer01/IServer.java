package rs.ac.uns.ftn.informatika.osa.vezbe02.primer01;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IServer extends Remote {
	int count() throws RemoteException;
}
