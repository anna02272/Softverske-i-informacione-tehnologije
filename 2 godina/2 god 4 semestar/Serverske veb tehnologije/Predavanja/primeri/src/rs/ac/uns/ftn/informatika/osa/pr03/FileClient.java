package rs.ac.uns.ftn.informatika.osa.pr03;

import java.io.File;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

public class FileClient {
  public static void main(String[] args) {
    try {
      // definisemo promenljive okruzenja
      Properties env = new Properties();
      env.put("java.naming.factory.initial",
        "com.sun.jndi.fscontext.FSContextFactory");
      env.put("java.naming.provider.url",
        "file:///C:/");

      // kreiramo inicijalni kontekst
      Context ctx = new InitialContext(env);
      // lookup metoda ce vratiti instancu klase File
      // ako se radi o datoteci; ukoliko je u pitanju
      // direktorijum, vratice novu Context instancu
      // koju koristimo na isti nacin kao i inicijalni 
      // kontekst
      File file = (File)ctx.lookup("temp.txt");

      // sa dobijenom instancom rukujemo kao da je
      // kreirana na klasican nacin
      System.out.println("Filename: " +
        file.getAbsolutePath());
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
