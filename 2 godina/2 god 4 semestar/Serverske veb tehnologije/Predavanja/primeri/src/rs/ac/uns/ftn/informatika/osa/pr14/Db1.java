package rs.ac.uns.ftn.informatika.osa.pr14;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Db1 {

  public static void main(String[] args) {
    try {
      // ucitavanje JDBC drajvera
      Class.forName("com.mysql.jdbc.Driver");
      //ili ovako: new com.mysql.jdbc.Driver();

      // otvaranje konekcije
      Connection conn = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/osa1", "osa1", "osa1");
       //"jdbc:oracle:thin:@localhost:ORCL", "osa1", "osa1");

      // Slanje upita
      String query = "SELECT ime, prezime FROM nastavnici";
      Statement stmt = conn.createStatement();
      ResultSet rset = stmt.executeQuery(query);

      // Citanje rezultata upita
      while (rset.next()) {
        System.out.println(rset.getString("prezime") + " " + rset.getString("ime"));
        //rset.getString(1);
        //rset.getInt(2);
        //rset.getBoolean(3);
        //rset.getDate(4);
        //rset.getFloat(5);
        //...
      }

      // oslobadjanje resursa
      rset.close();
      stmt.close();

      // zatvaranje konekcije
      conn.close();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

}
