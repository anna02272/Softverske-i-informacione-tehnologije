package rs.ac.uns.ftn.informatika.osa.pr14;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;

public class Db3 {

  public static void main(String args[]) {
    try {
      Class.forName("com.mysql.jdbc.Driver");

      Connection conn = DriverManager.getConnection(
        "jdbc:mysql://localhost/osa1", "osa1", "osa1");

      // Povezivanje novih nastavnika sa predmetima
      CallableStatement stmt = conn.prepareCall("{? = call povezi (?, ?, ?)}");
      stmt.setString(2, "Sima");
      stmt.setString(3, "Simic");
      stmt.setString(4, "Osnovi racunarstva");
      stmt.registerOutParameter(1, Types.INTEGER);
      stmt.executeQuery();
//      System.out.println("Status: " + stmt.getInt(1));

      stmt.close();
      conn.close();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

}

/* Primer PL/SQL funkcije koja se poziva u prethodnom primeru
 * 
CREATE FUNCTION `povezi`(ime_ varchar(30),
                 prezime_ varchar(30),
                 naziv_   varchar(30)) 
                 RETURNS tinyint(1)
begin
declare nasID  integer;
declare predID integer;
  select nastavnik_id into nasID from nastavnici
    where ime = ime_ and prezime = prezime_;
  select predmet_id into predID from predmeti
    where naziv = naziv_;
  insert into predaje (nastavnik_id, predmet_id)
    values (nasId, predID);
  return 1;
end

*/

