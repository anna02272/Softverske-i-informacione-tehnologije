package rs.ac.uns.ftn.informatika.osa.pr02;

import java.math.BigDecimal;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;

public class ComputePi {

  public static void main(String[] args) {
    setRmiCodebase();
    System.setSecurityManager(new RMISecurityManager());
    try {
      Compute comp = (Compute)Naming.lookup("//localhost:1099/Compute");
      Pi task = new Pi(100);
      BigDecimal pi = (BigDecimal)comp.executeTask(task);
      System.out.println(pi);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  /**
   * RMI codebase se postavlja na Client.jar jer ce taj fajl trebati serveru
   * prilikom poziva metode kada se kao parametar prenosi instanca podtipa.
   */
  private static void setRmiCodebase() {
    String codebase = System.getProperty("java.rmi.server.codebase");
    if (codebase != null)
      return;
    
    String testFile = "/" + ComputePi.class.getName().replace('.', '/') + ".class";
    String url = ComputePi.class.getResource(testFile).toString();
    if (url.startsWith("jar:")) {
      int pos = url.indexOf(".jar");
      codebase = "file://" + url.substring(9, pos + 4);
    } else if (url.startsWith("file:")) {
      int pos = url.indexOf("rs");
      codebase = "file://" + url.substring(5, pos);
    } else // WTF
      return;
    System.err.println("Setting RMI codebase to " + codebase);
    System.setProperty("java.rmi.server.codebase", codebase);
  }
}
