package rs.ac.uns.ftn.informatika.osa.vezbe05.primer04;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Thread2 extends Thread {

	private Connection connection;
	
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
	public void run() {
		
		Statement stmt = null;
		ResultSet rs = null;
		System.out.println("THREAD 2 - Start");
		
		try {
			
			System.out.println("THREAD 2 - SLEEP FOR FIRST TIME");
			Thread2.sleep(1000);
			System.out.println("THREAD 2 - WAKE UP FOR FIRST TIME");
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		

		try{
			
			stmt = connection.createStatement();			
			rs = stmt.executeQuery("SELECT * FROM nastavnici");
			
			while(rs.next()){
				System.out.println(rs.getString(2) + " " + rs.getString(3));
			}
			

		}catch(SQLException sqlex){
			sqlex.printStackTrace();
		}
		
		try {
			System.out.println("THREAD 2 - SLEEP FOR SECOND TIME");
			Thread2.sleep(1000);
			System.out.println("THREAD 2 - WAKE UP FOR SECOND TIME");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		try{
					
			rs = stmt.executeQuery("SELECT * FROM nastavnici");
			
			while(rs.next()){
				System.out.println(rs.getString(2) + " " + rs.getString(3));
			}
			
			rs.close();
			stmt.close();
			connection.close();
			
			System.out.println("THREAD 2 - Stop");
			
		}catch(SQLException sqlex){
			sqlex.printStackTrace();
		}		
	}
}
