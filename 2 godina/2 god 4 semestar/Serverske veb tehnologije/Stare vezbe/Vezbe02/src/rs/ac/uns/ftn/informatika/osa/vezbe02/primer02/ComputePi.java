package rs.ac.uns.ftn.informatika.osa.vezbe02.primer02;

import java.math.BigDecimal;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;

public class ComputePi {

	public static void main(String[] args) {
		System.setSecurityManager(new RMISecurityManager());
		try {
			Compute comp = (Compute) Naming.lookup("//localhost:1099/Compute");
			Pi task = new Pi(100);
			BigDecimal pi = (BigDecimal) comp.executeTask(task);
			System.out.println(pi);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
