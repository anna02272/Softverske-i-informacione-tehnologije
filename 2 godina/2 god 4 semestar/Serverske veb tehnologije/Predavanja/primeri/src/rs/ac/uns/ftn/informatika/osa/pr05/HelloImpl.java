package rs.ac.uns.ftn.informatika.osa.pr05;

import java.rmi.RemoteException;

import javax.rmi.PortableRemoteObject;

/**
 * RMI-over-IIOP
 * Implementacija RMI interfejsa sada mora da nasledi PortableRemoteObject
 * umesto UnicastRemoteObject 
 */
public class HelloImpl extends PortableRemoteObject implements HelloI {

  public HelloImpl() throws RemoteException {
  }
  
  public void sayHello(String from) throws RemoteException {
    System.out.println("Hello from " + from + "!");
  }
}
