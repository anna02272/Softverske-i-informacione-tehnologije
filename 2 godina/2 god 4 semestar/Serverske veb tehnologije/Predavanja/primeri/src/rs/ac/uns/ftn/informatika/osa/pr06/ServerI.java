package rs.ac.uns.ftn.informatika.osa.pr06;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerI extends Remote {
  int count() throws RemoteException;
}
