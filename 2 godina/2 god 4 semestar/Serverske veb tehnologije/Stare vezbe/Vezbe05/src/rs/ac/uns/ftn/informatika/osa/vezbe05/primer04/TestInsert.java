package rs.ac.uns.ftn.informatika.osa.vezbe05.primer04;

import java.io.IOException;
import java.io.InputStream;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

import java.util.Properties;

import rs.ac.uns.ftn.informatika.osa.vezbe05.primer04.Thread1;
import rs.ac.uns.ftn.informatika.osa.vezbe05.primer04.Thread2;

public class TestInsert {

	public static void main(String args[]){
		try {
			// Ucitavanje parametara za povezivanje na bazu podataka
		    ClassLoader cl = TestInsert.class.getClassLoader();
		    InputStream is = cl.getResourceAsStream("build.properties"); 

		    Properties p = new Properties();
		    p.load(is);
		    
		    String driver = p.getProperty("db.driver");
		    String hostname = p.getProperty("db.hostname");
		    String port = p.getProperty("db.port");
		    String name = p.getProperty("db.name");
		    String username = p.getProperty("db.username");
		    String password = p.getProperty("db.password");
			
		    // Ucitavanje MySQL drajvera
		    Class.forName(driver);
		      
		    // Povezivanje na bazu podataka
		    Connection conn1 = DriverManager.getConnection("jdbc:mysql://" + hostname + ":" + port + "/" + name, username, password);
		    Connection conn2 = DriverManager.getConnection("jdbc:mysql://" + hostname + ":" + port + "/" + name, username, password);
 
			conn1.setAutoCommit(false);
			
			System.out.println("TEST - START");
			
			Thread1 thread1 = new Thread1();
			Thread2 thread2 = new Thread2();
			
			thread1.setConnection(conn1);
			thread2.setConnection(conn2);
			
			thread1.start();
			thread2.start();
			
			System.out.println("TEST - END");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}