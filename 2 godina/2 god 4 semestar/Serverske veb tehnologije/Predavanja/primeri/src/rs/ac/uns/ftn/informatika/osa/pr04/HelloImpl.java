package rs.ac.uns.ftn.informatika.osa.pr04;

import org.omg.CORBA.ORB;

public class HelloImpl extends HelloPOA {

  public String sayHello(String name) {
    return "Hello, " + name;
  }

  public void shutdown() {
    orb.shutdown(false);
  }

  public void setORB(ORB orb) {
    this.orb = orb;
  }

  private ORB orb;
}
