package rs.ac.uns.ftn.informatika.osa.pr05;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * RMI-over-IIOP
 * RMI interfejs se ne menja.
 */
public interface HelloI extends Remote {
  public void sayHello(String from) throws RemoteException; 
}
