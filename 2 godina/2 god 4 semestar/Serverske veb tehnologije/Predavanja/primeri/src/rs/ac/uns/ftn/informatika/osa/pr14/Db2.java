package rs.ac.uns.ftn.informatika.osa.pr14;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Db2 {

  public static void main(String[] args) {
    try {
      Class.forName("com.mysql.jdbc.Driver");

      Connection conn = DriverManager.getConnection(
          "jdbc:mysql://localhost/osa1", "osa1", "osa1");

      // Dodavanje novih nastavnika
      PreparedStatement stmt = conn.prepareStatement(
          "insert into nastavnici (nastavnik_id, ime, prezime, zvanje) values (?, ?, ?, ?)");
      stmt.setInt(1, 4);
      stmt.setString(2, "Sima");
      stmt.setString(3, "Simic");
      stmt.setString(4, "docent");
      stmt.executeUpdate();

      stmt.setInt(1, 5);
      stmt.setString(2, "Vasa");
      stmt.setString(3, "Vasic");
      stmt.setString(4, "docent");
      stmt.executeUpdate();

      stmt.close();
      conn.close();
    } catch (Exception ex) {
      ex.printStackTrace();
    }

  }

}
