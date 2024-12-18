package rs.ac.uns.ftn.informatika.osa.vezbe05.primer04;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Thread1 extends Thread {

	private Connection connection;

	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
	public void run() {
		System.out.println("THREAD 1 - Start");

		// Pocinjemo sa upisom novog nastavnika
		try {
			Statement stmt = connection.createStatement();
			stmt.execute("INSERT INTO nastavnici VALUES (4,'Milan','Milanovic','vanprof')");

			try {
				System.out.println("THREAD 1 - SLEEP");
				Thread1.sleep(1500);
				System.out.println("THREAD 1 - WAKE UP");

			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			connection.commit();
			stmt.close();

			connection.close();
			System.out.println("THREAD 1 - Stop");

		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
			try {
				connection.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
