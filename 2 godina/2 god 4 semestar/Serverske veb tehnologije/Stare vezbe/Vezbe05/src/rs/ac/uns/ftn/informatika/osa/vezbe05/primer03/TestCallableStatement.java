package rs.ac.uns.ftn.informatika.osa.vezbe05.primer03;

import java.io.InputStream;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;

import java.util.Properties;

public class TestCallableStatement {
  
  public static void main(String args[]) {
    try {
      // Ucitavanje parametara za povezivanje na bazu podataka
      ClassLoader cl = TestCallableStatement.class.getClassLoader();
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
      Connection conn = DriverManager.getConnection("jdbc:mysql://" + hostname + ":" + port + "/" + name, username, password);
      
      // povezivanje novih nastavnika sa predmetima
      CallableStatement stmt = conn.prepareCall("{? = CALL povezi (?, ?, ?)}");
      
      stmt.registerOutParameter(1, Types.INTEGER);
      
      stmt.setString(2, "Petar");
      stmt.setString(3, "Petrovic");
      stmt.setString(4, "Matematika");
      
      stmt.executeUpdate();
      
      System.out.println("Status: " + stmt.getInt(1));
            
      stmt.close();
      conn.close();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
