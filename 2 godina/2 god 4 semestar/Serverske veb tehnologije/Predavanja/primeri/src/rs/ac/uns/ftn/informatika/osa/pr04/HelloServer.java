package rs.ac.uns.ftn.informatika.osa.pr04;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

public class HelloServer {
  public static void main(String[] args) {
    try {
      String[] params = {"-ORBInitialHost", "localhost", "-ORBInitialPort", "1050"};
      ORB orb = ORB.init(params, null);
      POA rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
      rootPOA.the_POAManager().activate();
      
      HelloImpl helloImpl = new HelloImpl();
      helloImpl.setORB(orb);
      
      org.omg.CORBA.Object ref = rootPOA.servant_to_reference(helloImpl);
      Hello hello = HelloHelper.narrow(ref);
      
      org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
      NamingContextExt ctx = NamingContextExtHelper.narrow(objRef);
      
      NameComponent[] path = ctx.to_name("Hello");
      ctx.rebind(path, hello);
      
      System.out.println("HelloServer ready.");
      
      orb.run();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
