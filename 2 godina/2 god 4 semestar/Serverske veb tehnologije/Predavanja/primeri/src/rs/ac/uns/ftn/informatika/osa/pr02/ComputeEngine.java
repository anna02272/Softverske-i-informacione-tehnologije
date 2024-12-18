package rs.ac.uns.ftn.informatika.osa.pr02;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ComputeEngine extends UnicastRemoteObject implements Compute {

  public ComputeEngine() throws RemoteException {
    super();
  }
  
  public Object executeTask(Task t) {
    return t.execute();
  }
  
  public static void main(String[] args) {
    setRmiCodebase();
    System.setSecurityManager(new RMISecurityManager());
    try {
      Compute engine = new ComputeEngine();
      Naming.rebind("//localhost:1099/Compute", engine);
      System.out.println("ComputeEngine started.");
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  /**
   * Vidi objasnjenje u prethodnom primeru.
   */
  private static void setRmiCodebase() {
    String codebase = System.getProperty("java.rmi.server.codebase");
    if (codebase != null)
      return;
    
    String testFile = "/" + ComputeEngine.class.getName().replace('.', '/') + ".class";
    String url = ComputeEngine.class.getResource(testFile).toString();
    if (url.startsWith("jar:")) {
      int pos = url.indexOf(".jar");
      codebase = "file://" + url.substring(9, pos + 4);
    } else if (url.startsWith("file:")) {
      int pos = url.indexOf("rs");
      codebase = "file://" + url.substring(5, pos);
    } else // WTF
      return;
    System.err.println("Setting RMI codebase to " + codebase);
    System.out.println(codebase);
    System.setProperty("java.rmi.server.codebase", codebase);
  }
  
}
