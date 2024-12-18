package rs.ac.uns.ftn.informatika.osa.vezbe05.primer02;

import java.io.InputStream;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import java.util.Properties;

public class TestPreparedStatement {
  
  public static void main(String args[]) {
    try {
      // Ucitavanje parametara za povezivanje na bazu podataka
      ClassLoader cl = TestPreparedStatement.class.getClassLoader();
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
      
      // Dodavanje novih nastavnika
      PreparedStatement stmt = conn.prepareStatement("INSERT INTO nastavnici (nastavnikId, ime, prezime, zvanje) VALUES (?, ?, ?, ?)");
      stmt.setInt(1, 2);
      stmt.setString(2, "Sima");
      stmt.setString(3, "Simic");
      stmt.setString(4, "docent");
      stmt.executeUpdate();
      
      stmt.setInt(1, 3);
      stmt.setString(2, "Vasa");
      stmt.setString(3, "Vasic");
      stmt.setString(4, "vanprof");
      stmt.executeUpdate();
      
      stmt.close();
      conn.close();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
