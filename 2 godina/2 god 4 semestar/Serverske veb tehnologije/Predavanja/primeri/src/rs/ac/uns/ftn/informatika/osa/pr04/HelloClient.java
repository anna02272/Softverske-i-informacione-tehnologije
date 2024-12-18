package rs.ac.uns.ftn.informatika.osa.pr04;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

public class HelloClient {
  public static void main(String[] args) {
    try {
      String[] params = {"-ORBInitialHost", "localhost", "-ORBInitialPort", "1050"};
      ORB orb = ORB.init(params, null);
      org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
      NamingContextExt ctx = NamingContextExtHelper.narrow(objRef);
      
      Hello hello = HelloHelper.narrow(ctx.resolve_str("Hello"));
      
      System.out.println(hello.sayHello("Branko"));
      
      hello.shutdown();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
