package rs.ac.uns.ftn.informatika.osa.vezbe05.primer01;

import java.io.InputStream;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.Properties;

public class TestStatement {

  public static void main(String args[]) {
    try {
      // Ucitavanje parametara za povezivanje na bazu podataka
      ClassLoader cl = TestStatement.class.getClassLoader();
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
      
      // Izvrsavanje upita
      String query = "SELECT ime, prezime FROM nastavnici";
      Statement stmt = conn.createStatement();
      ResultSet rset = stmt.executeQuery(query);
      
      // Prikazivanje rezultata upita
      while (rset.next()) {
        System.out.println(rset.getString(1) + " " + rset.getString(2));
      }
      
      // Zatvaranje veze
      rset.close();
      stmt.close();
      conn.close();
    }
    catch (Exception ex) {    	
      ex.printStackTrace();
    }
  }
}

