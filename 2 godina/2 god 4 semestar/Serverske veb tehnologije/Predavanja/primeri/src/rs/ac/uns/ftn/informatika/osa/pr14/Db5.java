package rs.ac.uns.ftn.informatika.osa.pr14;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Db5 {

  public static void main(String[] args) {
    try {
      Class.forName("com.mysql.jdbc.Driver");
      Connection conn = DriverManager.getConnection(
        "jdbc:mysql://localhost/osa1", "osa1", "osa1");
      
      // iskljuci automatski commit posle svake SQL naredbe
      conn.setAutoCommit(false);
      
      // izbor nivoa izolacije transakcija
      //conn.setTransactionIsolation(Connection.TRANSACTION_NONE);               <-- lost update
      //conn.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);   <-- dirty read  
      conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);     //<-- unrepeatable read
      //conn.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);    <-- phantom read
      //conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
      
      // izvrsicemo dve SQL naredbe u jednoj transakciji
      String sql1 = "UPDATE nastavnici SET ime='Zika' WHERE nastavnik_id=1";
      String sql2 = "UPDATE predmeti SET naziv='Sociologija' WHERE predmet_id=1";
      Statement stmt = conn.createStatement();
      stmt.executeUpdate(sql1);
      stmt.executeUpdate(sql2);
      stmt.close();
      
      // tek sada se potvrdjuje transakcija; opoziv transakcije: conn.rollback()
      conn.commit();
      
      conn.close();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
