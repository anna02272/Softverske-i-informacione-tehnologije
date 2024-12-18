package rs.ac.uns.ftn.informatika.osa.vezbe06.primer02.client;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import rs.ac.uns.ftn.informatika.osa.vezbe06.primer02.server.session.Test;

public class TestClient {

	public static void main(String[] args) {

		try {

			Context ctx = new InitialContext();
			Test test = (Test) ctx.lookup("AutoRemote");
			test.test();

		} catch (NamingException ex) {
			ex.printStackTrace();
		}
	}
}