package rs.ac.uns.ftn.informatika.osa.vezbe04.primer01.client;

import javax.ejb.EJBException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.validation.ConstraintViolationException;

import rs.ac.uns.ftn.informatika.osa.vezbe04.primer01.server.beans.Admin;

public class InitItems {

	public static void main(String[] args) {
		try {
			Context ctx = new InitialContext();
		    Admin admin = (Admin)ctx.lookup("AdminBeanRemote");
			admin.initItems();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (EJBException e) {
			e.printStackTrace();
			
			if (e.getCause() instanceof ConstraintViolationException) {
				ConstraintViolationException exception = (ConstraintViolationException) e.getCausedByException();
				exception.printStackTrace();
			}
		}
	}
}
